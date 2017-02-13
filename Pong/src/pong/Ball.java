package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	double x,y,yVel, xVel;
	
	public Ball(){
		x = 350;
		y = 250;
		xVel = 0;
		yVel = 1;
	}
	
	
	public int getX(){
		return (int) x;
	}
	
	public int getY(){
		
		return (int) y;
	}
	
	
	public void move(){
		x +=xVel;
		y += yVel;
		
		if(y < 10){
			yVel = -yVel;
		}
		if(y > 690){
			yVel = -yVel;
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillOval((int) x-10, (int) y-10, 20, 20);
	}

}
