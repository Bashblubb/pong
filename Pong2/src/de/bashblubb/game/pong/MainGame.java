package de.bashblubb.game.pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainGame extends Applet implements Runnable, KeyListener {
	Thread thread;
	Spielbretter sb;
	Ball b;
	int width = 700, height = 700;
	
	//Superklasse: Applet
	@Override
	public void init() {
		//this bezieht sich auf Applet
		this.resize(width, height);
		//Spielelemente instanzieren
		sb = new Spielbretter();	
		b = new Ball();
		//f�gt der Applet Anwendung einen Keylistener hinzu
		//bei addKeyListener kann nur this definiert werden, weil MainGame selbst auch ein KeyListener durch die Implementierung des IF ist
		this.addKeyListener(this);	
		thread = new Thread(this);
		//start ruft einmal die Methode run (siehe unten) auf f�r den definierten neuen Thread
		thread.start();		
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, width, height);
		sb.draw(g);
		b.draw(g);
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
	}

	// Beschreibt was bei gedr�ckten Keys ausgef�hrt werden soll (von IF: KeyListener)
	@Override
	public void keyPressed(KeyEvent e) {		
		//Spieler 1 spielt mit den Kn�pfen hoch und runter, Spieler2 mit W und S
		if(e.getKeyCode() == KeyEvent.VK_UP){
			sb.setHochKnopfP1(true);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			sb.setRunterKnopfP1(true);
		}else if(e.getKeyCode() == KeyEvent.VK_W){
			sb.setHochKnopfP2(true);
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			sb.setRunterKnopfP2(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			sb.setHochKnopfP1(false);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			sb.setRunterKnopfP1(false);
		}else if(e.getKeyCode() == KeyEvent.VK_W){
			sb.setHochKnopfP2(false);
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			sb.setRunterKnopfP2(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// Beschreibt ausf�hrbaren Code den die Threads parallel ausf�hren sollen (von IF: Runnable)
	@Override
	public void run() {
		//Endlosschleife l�sst das Spiel laufen
		for(;;){
			sb.bewegung();	
			b.bewegung();
			//ruft die Paint-Methode auf
			repaint();
			//wartet jedesmal 10 Milisekunden bis Spielfeld neu gezeichnet wird
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
