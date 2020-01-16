import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Mario {
	private int x;
	private int y;
	private static final int VECESPINTADOMARIO = 50;
	
	public static int getVecespintadomario() {
		return VECESPINTADOMARIO;
	}

	private int speed;
	private int direccion;
	private int frame;
	private Image mario;
	private boolean colision;

	public Mario() {
		super();
		colision = false;
		x = (int) (Math.random() * 800) + 1;
		y = (int) (Math.random() * 300) + 1;
		direccion = 0;
		frame = 0;
		ImageIcon ii = new ImageIcon("asserts/SpriteMario.png");
		mario = ii.getImage();
		right();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public void draw(Graphics g) {
		if (!colision) {
			g.drawImage(mario, getX(), getY(), getX() +30, getY() + 30, frame * 26, direccion * 29, (1 + frame) * 26,
					(1 + direccion) * 29, null);
			System.out.println(getX());
			if (frame == 6) {
				frame = 0;
			}
		} 
	}

	public boolean isColision() {
		return colision;
	}

	public void setColision(boolean colision) {
		this.colision = colision;
	}

	public void update() {
			x += speed;
			frame++;
	}

	public void left() {
		speed =-5;
		direccion = 1;
		System.out.println("left");
	}

	public void right() {
		speed = 5;
		direccion = 0;
		System.out.println("right");
	}

}
