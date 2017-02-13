package pong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle {
	//Vel = Velocity
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;
	final double GRAVITY = 0.94;
	
	
	public HumanPaddle() {
		upAccel = false;
		downAccel = false;
		y = 210; 
		yVel = 0;		
		//Startpositionen der Spielbretter
		if(player == 1){
			x = 20;
		}else{
			x = 660;
		}		
	}

	public void draw(Graphics g) {
		//malt die Spielbretter
		g.setColor(Color.white);
		//Die Positonen des Spielbrettes (X/Y) sind variabel, so kann man sie bewegen
		g.fillRect(x, (int) y, 20, 80);
	}
	
	public void setUpAccel(boolean input) {
		upAccel = input;
	}

	public void setDownAccel(boolean input) {
		downAccel = input;
	}


	public void move() {
		//Bestimmt wie weit sich das Spielbrett bewegt wenn man den jeweiligen Knopf drückt
		if(upAccel){
			yVel -= 2;
		}else if(downAccel){
			yVel +=2;
		}else if (!downAccel && !upAccel){
			//immer wenn der Knopf losgelassen wird, soll sich das Spielbrett langsamer bewegen
			//GRAVITIY muss einen Wert < 1 haben, damit bei jeder Multiplikation der Gesamtwert kleiner wird
			yVel *= GRAVITY;
		}
		//Grenzt die Maximal-/Minimalgeschwindigkeiten ein
		if(yVel >=5 ){
			yVel = 5;	
		}else if(yVel <= -5){
			yVel = -5;
		}		
		//Verhindert das Spielbrett den Rand überschreitet
		if(y<0){
			y = 0;		}		
		if(y>620){
			y = 620;
		}
		//Addiert zu Y den Wert der Geschwindigkeit hinzu, damit bewegt sich das Spielbrett
		y += yVel;
	}

	public int getY() {
		return (int) y;
	}

}
