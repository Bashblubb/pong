package de.bashblubb.game.pong;

import java.awt.Color;
import java.awt.Graphics;



public class Ball implements SpielelementIF{
	int y, yGeschwindigkeit;
	int width = 20, height = 20;
	final int maxHoeheOben = 0, maxHoeheUnten = 680;

	
	public Ball() {
		y = 350;
		yGeschwindigkeit = 20;
	}

	@Override
	public void bewegung() {	
		//Ball bewegt sich immer mit konstanter Geschwindigkeit
		y += yGeschwindigkeit;	
		y = maxHoeheBall(y);
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(350, y, width, height);	
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
