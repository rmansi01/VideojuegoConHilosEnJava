import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import asserts.Asserts;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Rectangle;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private GameEngine game;
	public JButton btnPlay, btnAbout, btnClose;
	private JLabel lblRaulMansilla;
	private Asserts asserts;
	private GameLoop gl;
	private JButton btnPause;
	private JLabel lblPuntos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		asserts = new Asserts();
		asserts.loadAsserts();
		asserts.mainScreenMusic.loop();;
		setResizable(false);
		setTitle("Examen PSP Raul Mansilla Cruz - S2DAM - Evaluacion 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBounds(0, 0, 820, 670);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		game = new GameEngine();
		game.setBounds(0, 0, 800, 588);
		game.setBorder(new EmptyBorder(5,5,5,5));
		game.setOpaque(true);
		game.setVisible(true);
		
		contentPane.setLayout(null);
		contentPane.add(game);

		
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(0, 587, 800, 34);
		contentPane.add(btnPanel);
		btnPanel.setLayout(null);
		btnPanel.setBackground(Color.GREEN);
		
		lblRaulMansilla = new JLabel("\u00A9 2019 Ra\u00FAl Mansilla Cruz - EXAMEN PSP - S2DAM - IES Azarquiel");
		lblRaulMansilla.setFont(new Font("Consolas", Font.BOLD, 14));
		lblRaulMansilla.setForeground(Color.WHITE);
		lblRaulMansilla.setBounds(10, 11, 563, 14);
		btnPanel.add(lblRaulMansilla);
		
		btnPause = new JButton("Pause");
		btnPause.setVisible(false);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asserts.pauseSound.play();	
				game.setPaused(!game.isPaused());
					
					
			}
		});
		btnPause.setBackground(Color.MAGENTA);
		btnPause.setForeground(Color.WHITE);
		btnPause.setFont(new Font("Consolas", Font.PLAIN, 17));
		btnPause.setBounds(701, 7, 89, 23);
		btnPanel.add(btnPause);
		
		
		btnPlay = new JButton("Play");
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setFont(new Font("Consolas", Font.BOLD, 20));
		btnPlay.setBackground(Color.MAGENTA);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asserts.mainScreenMusic.stop();
				gameStart();
				
			}
		});
		game.setLayout(null);
		btnPlay.setBounds(217, 186, 344, 79);
		game.add(btnPlay);
		btnAbout = new JButton("Instrucciones\r\n");
		btnAbout.setForeground(Color.WHITE);
		btnAbout.setFont(new Font("Consolas", Font.BOLD, 20));
		btnAbout.setBackground(Color.MAGENTA);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		game.setLayout(null);
		btnAbout.setBounds(217, 298, 344, 79);
		game.add(btnAbout);
		btnClose = new JButton("Cerrar");
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Consolas", Font.BOLD, 20));
		btnClose.setBackground(Color.MAGENTA);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asserts.mainScreenMusic.stop();
				salir();
			}
		});
		game.setLayout(null);
		btnClose.setBounds(217, 409, 344, 79);
		game.add(btnClose);
		
		JLabel lblLogo = new JLabel(" ");
		lblLogo.setBounds(217, 11, 344, 164);
		lblLogo.setIcon(new ImageIcon(asserts.logoMario));
		game.add(lblLogo);
		lblLogo.setVisible(true);
		
		
		
	}
	public void about() {
		JOptionPane.showMessageDialog(this.getParent(),"Juego desarrollado por Raul Mansilla Cruz"
				+ "\nMueve la barra inferior con los cursores del teclado para hacer que la bola rebote para dar a los Marios"
				+ "\nCada Mario, vale 10 puntos.");
	}
	
	public void gameStart() {// Metodo terminado y funcionando
		gl = new GameLoop(game);
		gl.start();
		game.setFocusable(true);
		btnPlay.setVisible(false);
		btnAbout.setVisible(false);
		btnClose.setVisible(false);
		asserts.hereWeGo.play();
		btnPause.setVisible(true);

		
	}
	
	public void updatePuntos() {
		lblPuntos.setText("Puntos: " + game.getContador());
	}
	
	

	public void salir() { //Metodo terminado y funcionando
		asserts.bye.play();
		dispose();
	}
}
