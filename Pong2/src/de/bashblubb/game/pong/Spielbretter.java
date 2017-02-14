package de.bashblubb.game.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Spielbretter implements SpielelementIF {
	int y1, y1Geschwindigkeit;
	int y2, y2Geschwindigkeit;
	boolean hochGedrücktP1 = false, runterGedrücktP1 = false;
	boolean hochGedrücktP2 = false, runterGedrücktP2 = false;
	final int maxHoeheOben = 0, maxHoeheUnten = 620;
	final double VERLANGSAMEN = 0.9;
	
	public Spielbretter() {		
		// beide Spieler starten auf der gleichen Höhe
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
		hochGedrücktP1 = hochKnopf;		
	}
	
	public void setRunterKnopfP1(boolean runterKnopf){
		runterGedrücktP1 = runterKnopf;
	}
	
	public void setHochKnopfP2(boolean hochKnopf){
		hochGedrücktP2 = hochKnopf;		
	}
	
	public void setRunterKnopfP2(boolean runterKnopf){
		runterGedrücktP2 = runterKnopf;
	}
	
	public void bewegung(){
		//Position der Bewegung seit letzter Aktualisierung bestimmen
		y1Geschwindigkeit = bewegeSpielbrett(y1Geschwindigkeit, true);
		y2Geschwindigkeit = bewegeSpielbrett(y2Geschwindigkeit, false);
		
		//Maximalgeschwindigkeiten bestimmen
		y1Geschwindigkeit = validateMaxGeschwindigkeit(y1Geschwindigkeit);
		y2Geschwindigkeit = validateMaxGeschwindigkeit(y2Geschwindigkeit);	
						
		//das Spielbrett wird um die zurückgelegte Strecke bewegt
		y1 += y1Geschwindigkeit;
		y2 += y2Geschwindigkeit;	
		
		//setze Spielbrett zurück wenn Spielränder erreicht
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
			if(hochGedrücktP1){			
				//Spielbrett bewegt sich 20 Pixel nach oben
				geschwindigkeit -= 2;
			}else if(runterGedrücktP1){
				//Spielbrett bewegt sich 20 Pixel nach unten
				geschwindigkeit +=2;
			}else if(!hochGedrücktP1 && !runterGedrücktP1){
				//wenn kein Knopf gedrückt von Spieler1 wird das Spielbrett in jeder Iteration verlangsamt (ermöglicht flüssige Bewegung)
				geschwindigkeit *= VERLANGSAMEN;			
			}
		}else{
			if(hochGedrücktP2){			
				//Spielbrett bewegt sich 20 Pixel nach oben
				geschwindigkeit -= 2;
			}else if(runterGedrücktP2){
				//Spielbrett bewegt sich 20 Pixel nach unten
				geschwindigkeit +=2;
			}else if(!hochGedrücktP2 && !runterGedrücktP2){
				//wenn kein Knopf gedrückt von Spieler1 wird das Spielbrett in jeder Iteration verlangsamt (ermöglicht flüssiges Abbremsen)
				geschwindigkeit *= VERLANGSAMEN;			
			}
		}		
		return geschwindigkeit;
	}
}
