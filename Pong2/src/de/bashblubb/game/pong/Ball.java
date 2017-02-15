package de.bashblubb.game.pong;

import java.awt.Color;
import java.awt.Graphics;
public class Ball implements SpielelementIF{
	int y, yGeschwindigkeit, x, xGeschwindigkeit;
	int width = 20, height = 20;
	final int maxHoeheOben = 0, maxHoeheUnten = 680;
	final int maxBreiteLinks = 0, maxBreiteRechts = 680;
	int sby1 = 0;	
	int scoreP1 = 0, scoreP2 = 0;
	
	public Ball() {
		x = 350;
		y = 350;
		yGeschwindigkeit = 1;
		xGeschwindigkeit = 2;
	}
	
	public int getY1(){
		return y;
	}
	
	public int getY2(){
		//Ball hat nur 1 Y-Wert
		return 0;
	}
	
	public int getX1(){
		return x;
	}
	
	public int getX2(){
		//Ball hat nur 1 X-Wert
		return 0;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);	
	}	

	@Override
	public void bewegung() {	
		//Ball bewegt sich immer mit konstanter Geschwindigkeit
		y += yGeschwindigkeit;	
		x += -xGeschwindigkeit;
		y = maxHoeheBall(y);				
	}
	
	public boolean spielende(int sby1, int sby2, int sbx1, int sbx2) {
		if (valSpielende(x)) {
			return true;
		} else {
			if (x == 20) {
				if (y <= sby1 + 65 && y >= sby1 - 30) {
					xGeschwindigkeit = -xGeschwindigkeit;
				}
			} else if (x == 660)
				if (y <= sby2 + 65 && y >= sby2 - 30) {
					xGeschwindigkeit = -xGeschwindigkeit;
				}
			return false;
		}
	}
	
	public boolean valSpielende(int x){
		if(x <= maxBreiteLinks){	
			scoreP1++;
			return true;	
			
		}else if (x >= maxBreiteRechts ){	
			scoreP2++;
			return true;
		
		}else{
			return false;
		}		
	}
		
	public int maxHoeheBall(int y){
		if(y <= maxHoeheOben){
			y = maxHoeheOben;
			yGeschwindigkeit = -yGeschwindigkeit;
		}else if(y>=maxHoeheUnten){
			y = maxHoeheUnten;
			yGeschwindigkeit = -yGeschwindigkeit;
		}		
		return y;
	}
	
	public int getScoreP1(){
		return scoreP1;
	}
	
	public int getScoreP2(){
		return scoreP2;
	}
}
