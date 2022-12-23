package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import model.ChallengeGame;
import model.Game;
import model.Game.GameType;
import model.GameFactory;
import model.MultiplayerGame;
import model.NormalGame;
import model.Player;

public final class Window extends JFrame {
	// Palette reference: https://coolors.co/palette/001219-005f73-0a9396-94d2bd-e9d8a6-ee9b00-ca6702-bb3e03-ae2012-9b2226

	private WordView wordView; // TEMPORARY
	private GameView gameView;

	public Window(int w, int h) {
		this.setMinimumSize(new Dimension(w, h));
		this.setTitle("Dactylo Game");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(w, h));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On ajoute une marge sur les bordures du container principal
		// On aura donc la marge sur toutes les pages
		int padding = 10;
		((JPanel)getContentPane()).setBorder(new EmptyBorder(padding, padding, padding, padding));

		// @paris, Il faut voir si on laisse ça ? Important car sinon on a des bords blancs sur le coté
		getContentPane().setBackground(HomeView.backgroundColor);

		// wordView = new WordView(new Word("Bonjour")); // TEMPORARY
		
		//setHomeView();

		setNormalMode();

		// setKeyListener();

		this.setVisible(true);
	}

	public void setHomeView() {
		this.getContentPane().removeAll();
		// On remet à zero gameView et le keyListener
		gameView = null;
		this.addKeyListener(null);

		this.getContentPane().add(new HomeView());
		revalidate();
		repaint();
	}

	public void setNormalMode() {
		this.getContentPane().removeAll();

		// TEMPORARY: Pour le moment je mets un fake player
		/////////////
		var players = new ArrayList<>(Arrays.asList(new Player("leopold", 0)));
		var game = GameFactory.getGame(GameType.NORMAL, "src/main/resources/sample.txt", players);
		/////////////

		gameView = new GameView(this, game);
		this.getContentPane().add(gameView);
		setNormalModeKeyListener();
		revalidate();
		repaint();
	}

	public void setNormalModeKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(!isMode("NORMAL"))
					return;
				// On lance le timer si besoin
				if(!gameView.isRunning())
					gameView.startTimer();

				WordView actualWord = gameView.getActualWord();
				switch(e.getKeyChar()) {
					case KeyEvent.VK_SPACE:
						// On valide le mot actuel.
						// On passe au suivant et on modifie les stats
						gameView.nextWord();
						break;
					case KeyEvent.VK_BACK_SPACE:
						actualWord.removeLetter();
						break;
					default:
						if(Character.isLetter(e.getKeyChar())) {
							actualWord.pushLetter(e.getKeyChar());
						}
						break;
				}
				
				// Pour une raison obscure, on doit rafraichir deux fois l'affichage
				// pour que le texte se mette à jour...
				for(int i=0;i<2;i++) {
					gameView.getTextArea().revalidate();
					revalidate();
					repaint();
				}
			}
		});
	}

	
	/** 
	 * Permet de récuperer le mode de jeu en cours
	 * @param mode
	 * @return boolean
	 */
	public boolean isMode(String mode) {
		if(gameView == null)
			return false;
		Game game = gameView.getGame();
		switch(mode.toUpperCase()) {
			case "NORMAL":
				return game instanceof NormalGame;
			case "GAME":
				return game instanceof ChallengeGame;
			case "MULTIPLAYER":
				return game instanceof MultiplayerGame;
			default:
				return false;
		}
	}

	public void quit() {
		this.dispose();
		System.exit(0);
	}

	public static Font getNewFont(int size) {
		float f = (float)size;
		try {
				Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/MartianMono-Medium.ttf")).deriveFont(f);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(customFont);
				return customFont;
		} catch (Exception e) {
				Font customFont = new Font(Font.SERIF, Font.PLAIN,  10);
				return customFont;
		} 
	}

	public static JLabel getJLabel(String text, int size, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(getNewFont(size));
		label.setForeground(color);
		return label;
	}

	public static JPanel getPanel(int border, LayoutManager layout, Component... panelsToAdd) {
		JPanel pan = new JPanel();
		pan.setOpaque(false);
		pan.setLayout(layout);
		pan.setBorder(new EmptyBorder(border, border, border, border));

		for(Component p : panelsToAdd)
			pan.add(p);

		return pan;
	}

} 
  
