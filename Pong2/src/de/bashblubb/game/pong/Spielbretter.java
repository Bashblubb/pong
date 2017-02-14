package de.bashblubb.game.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Spielbretter implements SpielelementIF {
	int y1, y1Geschwindigkeit;
	int y2, y2Geschwindigkeit;
	boolean hochGedr�cktP1 = false, runterGedr�cktP1 = false;
	boolean hochGedr�cktP2 = false, runterGedr�cktP2 = false;
	final int maxHoeheOben = 0, maxHoeheUnten = 620;
	final double VERLANGSAMEN = 0.9;
	
	public Spielbretter() {		
		// beide Spieler starten auf der gleichen H�he
		y1 = 210;
		y2 = 210;
		// am Anfang steht das Spielbrett still
		y1Geschwindigkeit = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, y1, 20, 80);	
		g.fillRect(680, y2, 20, 80);
	}
	
	public void setHochKnopfP1(boolean hochKnopf){
		hochGedr�cktP1 = hochKnopf;		
	}
	
	public void setRunterKnopfP1(boolean runterKnopf){
		runterGedr�cktP1 = runterKnopf;
	}
	
	public void setHochKnopfP2(boolean hochKnopf){
		hochGedr�cktP2 = hochKnopf;		
	}
	
	public void setRunterKnopfP2(boolean runterKnopf){
		runterGedr�cktP2 = runterKnopf;
	}
	
	public void bewegung(){
		//Position der Bewegung seit letzter Aktualisierung bestimmen
		y1Geschwindigkeit = bewegeSpielbrett(y1Geschwindigkeit, true);
		y2Geschwindigkeit = bewegeSpielbrett(y2Geschwindigkeit, false);
		
		//Maximalgeschwindigkeiten bestimmen
		y1Geschwindigkeit = validateMaxGeschwindigkeit(y1Geschwindigkeit);
		y2Geschwindigkeit = validateMaxGeschwindigkeit(y2Geschwindigkeit);	
						
		//das Spielbrett wird um die zur�ckgelegte Strecke bewegt
		y1 += y1Geschwindigkeit;
		y2 += y2Geschwindigkeit;	
		
		//setze Spielbrett zur�ck wenn Spielr�nder erreicht
		y1 = validateMaxPosition(y1);
		y2 = validateMaxPosition(y2);	
	}
	
	public int validateMaxPosition(int y){
		if(y <= maxHoeheOben){
			y = maxHoeheOben;
		}else if(y >= maxHoeheUnten){
			y = maxHoeheUnten;
		}
		return y;
	}
	
	public int validateMaxGeschwindigkeit(int geschwindigkeit){
		if(geschwindigkeit > 10){
			geschwindigkeit = 10;
		}else if(geschwindigkeit < -10){
			geschwindigkeit = -10;
		}
		return geschwindigkeit;
	}
	
	public int bewegeSpielbrett(int geschwindigkeit, boolean player1){
		if(player1){
			if(hochGedr�cktP1){			
				//Spielbrett bewegt sich 20 Pixel nach oben
				geschwindigkeit -= 2;
			}else if(runterGedr�cktP1){
				//Spielbrett bewegt sich 20 Pixel nach unten
				geschwindigkeit +=2;
			}else if(!hochGedr�cktP1 && !runterGedr�cktP1){
				//wenn kein Knopf gedr�ckt von Spieler1 wird das Spielbrett in jeder Iteration verlangsamt (erm�glicht fl�ssige Bewegung)
				geschwindigkeit *= VERLANGSAMEN;			
			}
		}else{
			if(hochGedr�cktP2){			
				//Spielbrett bewegt sich 20 Pixel nach oben
				geschwindigkeit -= 2;
			}else if(runterGedr�cktP2){
				//Spielbrett bewegt sich 20 Pixel nach unten
				geschwindigkeit +=2;
			}else if(!hochGedr�cktP2 && !runterGedr�cktP2){
				//wenn kein Knopf gedr�ckt von Spieler1 wird das Spielbrett in jeder Iteration verlangsamt (erm�glicht fl�ssiges Abbremsen)
				geschwindigkeit *= VERLANGSAMEN;			
			}
		}		
		return geschwindigkeit;
	}
}
