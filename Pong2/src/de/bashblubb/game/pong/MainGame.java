package de.bashblubb.game.pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainGame extends Applet implements Runnable, KeyListener, ActionListener {
	Thread thread;
	Spielbretter sb;
	Ball b;
	int width = 700, height = 700;
	boolean startGame = false;
	int scoreP1 = 0, scoreP2 = 0;
	JFrame meinFrame;     
    JButton butNeustart; 
    JButton butExit; 
    JLabel txtScoreP1; 
    JLabel txtScoreP2; 
	
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
			startGame = true;
			sb.setHochKnopfP2(true);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			startGame = true;
			sb.setRunterKnopfP2(true);
		}else if(e.getKeyCode() == KeyEvent.VK_W){
			startGame = true;
			sb.setHochKnopfP1(true);
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			startGame = true;
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
			if(startGame){
				b.bewegung();
			}			
			//wenn der Ball ans Spielfeldende kommt ist das Spiel vorbei bzw. die Schleife wird unterbrochen
			if(b.spielende(sb.getY1(), sb.getY2(), sb.getX1(), sb.getX2())){
				startGame = false;
				spielZuEndeGui();
				thread.interrupt();
				break;				
			}
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
	
	public void spielZuEndeGui() {
		meinFrame = new JFrame("Game Over");
		butNeustart = new JButton("Nochmal");
		butExit = new JButton("Exit");
		txtScoreP1 = new JLabel(String.valueOf(scoreP1));
		txtScoreP2 = new JLabel(String.valueOf(scoreP2));
		meinFrame.setLayout(null);
		meinFrame.setSize(400, 200);
		meinFrame.setVisible(true);
		butNeustart.setSize(200, 50);
		butExit.setBounds(200, 100, 100, 50);
		butNeustart.setBounds(50, 100, 100, 50);
		txtScoreP1.setBounds(50, 20, 200, 50);
		txtScoreP1.setBounds(150, 20, 200, 50);
		meinFrame.add(butNeustart);
		meinFrame.add(butExit);
		meinFrame.add(txtScoreP1);
		meinFrame.add(txtScoreP2);
		butExit.addActionListener(this);
		butNeustart.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == butExit){
			System.exit(0);
		}else if(e.getSource() == butNeustart){	
			init();
			meinFrame.dispose();
		}		
	}
}
