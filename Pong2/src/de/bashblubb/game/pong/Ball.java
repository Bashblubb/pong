package de.bashblubb.game.pong;

import java.awt.Color;
import java.awt.Graphics;
public class Ball implements SpielelementIF{
	int y, yGeschwindigkeit, x, xGeschwindigkeit;
	int width = 20, height = 20;
	final int maxHoeheOben = 0, maxHoeheUnten = 680;
	final int maxBreiteLinks = 0, maxBreiteRechts = 680;
	int sby1 = 0;	
	
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
	
	public void kollision(int sby1, int sby2, int sbx1, int sbx2){		
		//if(spielende)
		x = spielende(x);
		//else{
		for (int i = 0; i <= 40; i++) {
			if (x == 20) {
				if (y + i == sby1 || y - i == sby1) {
					System.out.println("KOLLISION!");
				}
			}else if (x == 680)
				if (y + i == sby2 || y - i == sby2) {
					System.out.println("KOLLISION!");
				}
		}
		// }
	}
	
	public int spielende(int x){
		if(x <= maxBreiteLinks){
			x = maxBreiteLinks;
			//zu Testzwecken prallt der Ball an den Außenwänden ab, später müsste hier das Spiel zuende sein
			xGeschwindigkeit = -xGeschwindigkeit;
			//return true;
		}else if (x >= maxBreiteRechts ){
			x = maxBreiteRechts;
			//zu Testzwecken prallt der Ball an den Außenwänden ab, später müsste hier das Spiel zuende sein
			xGeschwindigkeit = -xGeschwindigkeit;
			//return true;
		}
		return x;
		//return false;
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
	
	public boolean kollisionSpielbrett(int y){
		return true;
	}
}
