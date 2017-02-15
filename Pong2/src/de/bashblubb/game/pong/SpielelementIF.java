package de.bashblubb.game.pong;

import java.awt.Graphics;

public interface SpielelementIF {		
	public void bewegung();	
	public void draw(Graphics g);
	public int getY1();
	public int getY2();
	public int getX1();
	public int getX2();
}
