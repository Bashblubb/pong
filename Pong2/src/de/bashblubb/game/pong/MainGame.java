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
	boolean firstIteration = true;
	
	//Superklasse: Applet
	@Override
	public void init() {
		//this bezieht sich auf Applet
		this.resize(width, height);
		//Spielelemente instanzieren
		sb = new Spielbretter();	
		b = new Ball();
		//fügt der Applet Anwendung einen Keylistener hinzu
		//bei addKeyListener kann nur this definiert werden, weil MainGame selbst auch ein KeyListener durch die Implementierung des IF ist
		this.addKeyListener(this);	
		thread = new Thread(this);
		//start ruft einmal die Methode run (siehe unten) auf für den definierten neuen Thread
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

	// Beschreibt was bei gedrückten Keys ausgeführt werden soll (von IF: KeyListener)
	@Override
	public void keyPressed(KeyEvent e) {		
		//Spieler 1 spielt mit den Knöpfen hoch und runter, Spieler2 mit W und S
		if(e.getKeyCode() == KeyEvent.VK_UP){
			sb.setHochKnopfP2(true);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			sb.setRunterKnopfP2(true);
		}else if(e.getKeyCode() == KeyEvent.VK_W){
			sb.setHochKnopfP1(true);
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			sb.setRunterKnopfP1(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			sb.setHochKnopfP2(false);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			sb.setRunterKnopfP2(false);
		}else if(e.getKeyCode() == KeyEvent.VK_W){
			sb.setHochKnopfP1(false);
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			sb.setRunterKnopfP1(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// Beschreibt ausführbaren Code den die Threads parallel ausführen sollen (von IF: Runnable)
	@Override
	public void run() {
		
		//Endlosschleife lässt das Spiel laufen
		for(;;){
			sb.bewegung();	
			b.bewegung();
			b.kollision(sb.getY1(), sb.getY2(), sb.getX1(), sb.getX2());
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
