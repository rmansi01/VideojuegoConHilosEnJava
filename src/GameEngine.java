import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import asserts.Asserts;

public class GameEngine extends JPanel {

	/**
	 * Paco Pulido 16-10-2019
	 */
	private static final long serialVersionUID = 1L;
	
	boolean isRunning = true;
	int sizeX = 800, sizeY = 600;
	private Asserts asserts;
	private BufferedImage bufferedImage;
	private InputHandler input;
	private int contador;
	// private JLabel lblPuntos;
	protected Barra barra;
	private Bola bola;
	private Mario[] mario;
//	private PauseBtn btnPause;
	private boolean isPaused = false;

	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		new GameLoop(game).start();
	}

	public GameEngine() {
		super();
		setVisible(true);
		setSize(800, 600);
		setLayout(null);
		setBackground(Color.CYAN);
		asserts = new Asserts();
		asserts.loadAsserts();
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		barra = new Barra();
		bola = new Bola();
		// mario = new Mario();
		mario = new Mario[20];

		
		asserts.backgroundMusic.loop();
		makeMarios();

	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	void update() {
		if (!isPaused) {
			controlesTeclado();
			controlColisionBarra();
			controlColisionBordes();
			getColisiones(bola);
			bola.update();
			// mario.update();
			for (int i = 0; i < mario.length; i++) {
				mario[i].update();
			}
		}

	}

	private void makeMarios() {
		for (int i = 0; i < mario.length; i++) {
			mario[i] = new Mario();
		}

	}

	void draw() {
		if (!isPaused) {
			Graphics g = getGraphics();
			Graphics bbg = bufferedImage.getGraphics();
//		btnPause.draw(bbg);
			bbg.drawImage(asserts.background, 0, 0, getWidth(), getHeight(), this);

			setOpaque(false);
			super.paint(bbg);
			// Fondo
			bbg.setColor(Color.CYAN);
			bbg.fillRect(0, 0, getWidth(), getHeight());
			if (contador == 200) {
				finDelJuego();
			}
			// draws sprites
			barra.draw(bbg);
			bola.draw(bbg);
			for (int i = 0; i < mario.length; i++) {
				mario[i].draw(bbg);
			}

			g.drawImage(bufferedImage, 0, 0, this);
		}
	}
	

	public void controlesTeclado() { // Metodo terminado y funcionando
		// handle inputs
		if (input.isKeyDown(KeyEvent.VK_RIGHT) && barra.getxBarra() < this.getWidth() - 100) {
			barra.right();
			System.out.println(barra.getxBarra() + "," + barra.getYBarra());

		}
		if (input.isKeyDown(KeyEvent.VK_LEFT) && barra.getxBarra() > 0) {
			barra.left();
			System.out.println(barra.getxBarra() + "," + barra.getYBarra());
		}
	}

	public void controlColisionBarra() { // Control de colisiones con la barra funciona
		// Comprobamos si la bola esta en contacto con la barra
		if (bola.getyBola() + 10 == barra.getYBarra()) {
			if (bola.getxBola() >= barra.getxBarra() && bola.getxBola() + 10 <= barra.getxBarra() + 100) {
				System.out.println("COLISION (" + bola.getxBola() + ", " + bola.getyBola() + ")");
				updateVelocidad();
			}
		}
	}

	public void getColisiones(Bola bola) {
		Rectangle recBola = new Rectangle(bola.getxBola(), bola.getyBola(), 10, 10);
		Rectangle[] recMario = new Rectangle[20];
		for (int i = 0; i < recMario.length; i++) {
			recMario[i] = (Rectangle) new Rectangle(mario[i].getX(), mario[i].getY(), 30, 30);
			if (!mario[i].isColision()) {
				if (colision(recBola, recMario[i])) {
					mario[i].setColision(true);
				}
			}
		}

	}

	public boolean colision(Rectangle bola, Rectangle mario) {
		if (mario.intersects(bola)) {
			asserts.coin.play();
			contador += 10;
			return true;
		}
		return false;

	}

	public void updateVelocidad() {
		if (bola.getxBola() + 5 <= barra.getxBarra() + 20) {
			bola.setSpeedX(-5);
			bola.setSpeedY(-2.87F);
		} else if (bola.getxBola() + 5 <= barra.getxBarra() + 40) {
			bola.setSpeedX(-2.87F);
			bola.setSpeedY(-5);
		} else if (bola.getxBola() + 5 <= barra.getxBarra() + 60) {
			bola.setSpeedX(0);
			bola.setSpeedY(-5);
		} else if (bola.getxBola() + 5 <= barra.getxBarra() + 80) {
			bola.setSpeedX(2.87F);
			bola.setSpeedY(-5);
		} else {
			bola.setSpeedX(5);
			bola.setSpeedY(-2.87F);
		}
	}

	public void controlColisionBordes() { // Control de colision con los bordes: Terminado y funcionando
		// Comprobamos si choca con algun borde
		if (bola.getxBola() + 10 >= sizeX || bola.getxBola() <= 0) {
			bola.setSpeedX(bola.getSpeedX() * (-1));

		}
		if (bola.getyBola() <= 0) {
			bola.setSpeedY(bola.getSpeedY() * (-1));

		}
		if (bola.getyBola() == sizeY) {
			isRunning = false;
			finDelJuego();

		}
		for (int i = 0; i < mario.length; i++) {
			if (mario[i].getX() + 30 >= sizeX) {
				mario[i].left();
			}
			if (mario[i].getX() <= 0) {
				mario[i].right();
			}
		}

	}


	public void finDelJuego() {
		asserts.backgroundMusic.stop();
		if (contador < 200) {
			asserts.lost.play();
			JOptionPane.showMessageDialog(this.getParent(), "Fin del juego. Has conseguido " + contador + " puntos.");
			System.exit(0);
		} else {
			asserts.levelClear.play();
			JOptionPane.showMessageDialog(this.getParent(),
					"¡¡¡Enhorabuena!!!" + "\nHas eliminado a todos los Marios.");
			System.exit(0);
		}
	}

	public Barra getBarra() {
		return barra;
	}

	public void setBarra(Barra barra) {
		this.barra = barra;
	}

	public Bola getBola() {
		return bola;
	}

	public void setBola(Bola bola) {
		this.bola = bola;
	}

	public int getContador() {
		return contador;
	}
}
