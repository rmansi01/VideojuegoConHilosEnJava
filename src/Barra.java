import java.awt.Color;
import java.awt.Graphics;

public class Barra {
	private int xBarra=350, yBarra = 570;
	

	public int getxBarra() {
		return xBarra;
	}

	public void setxBarra(int xBarra) {
		this.xBarra = xBarra;
	}
	
	public void left(){
		xBarra-=10;
	}
	
	public void right(){
		xBarra+=10;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(xBarra, yBarra, 100, 10);
	}
	
	public int getYBarra() {
		return this.yBarra;
	}

}
