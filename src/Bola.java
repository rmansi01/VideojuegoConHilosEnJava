import java.awt.Color;
import java.awt.Graphics;

public class Bola {
	private int xBola=50, yBola=50;
	private float speedX=5, speedY=5;
	
	public void update() {
		xBola+=speedX;
		
		yBola+=speedY;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(xBola, yBola, 10, 10);
		System.out.println("Posicion: " + xBola + ","+yBola);
		System.out.println("Velocidad: " + speedX + ", " + speedY);
	}

	public float getSpeedX() {
		return speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public int getxBola() {
		return xBola;
	}

	public int getyBola() {
		return yBola;
	}


	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}


	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	

}
