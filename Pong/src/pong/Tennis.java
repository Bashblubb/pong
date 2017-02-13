package pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
	
	final int WIDTH = 700, HEIGHT = 700;
	Thread thread;
	HumanPaddle p1;
	Ball b1;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);		
		this.addKeyListener(this);		
		p1 = new HumanPaddle();
		b1 = new Ball();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
		b1.draw(g);
	}

	public void update(Graphics g) {
		paint(g);
	}
	
	// das lässt das Spiel laufen
	public void run() {
		// Endlossschleife
		for (;;) {
			//bewegt das Spielbrett und den Ball bei jeder Iteration
			p1.move();
			b1.move();
			// jedes Mal wird das Spielfeld repainted
			repaint();
			// danach sleep für 10 MS
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		//Hoch/Runter Key abfangen wenn gedrückt
		if(e.getKeyCode() == KeyEvent.VK_UP){
			//setzt Spielbrett in Bewegung
			p1.setUpAccel(true);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(true);
		}		
	}

	public void keyReleased(KeyEvent e) {
		//Hoch/Runter Key abfangen wenn losgelassen
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
	}

	public void keyTyped(KeyEvent e) {		
	}
}
