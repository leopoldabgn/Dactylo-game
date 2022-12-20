package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game;
import model.GameMode;
import model.MultiplayerMode;
import model.NormalMode;

public class Window extends JFrame {
	// Palette reference: https://coolors.co/palette/001219-005f73-0a9396-94d2bd-e9d8a6-ee9b00-ca6702-bb3e03-ae2012-9b2226

	private WordView wordView; // TEMPORARY
	private JPanel lastPanel;
	private GameView gameView;

	public Window(int w, int h) {
		this.setMinimumSize(new Dimension(w, h));
		this.setTitle("Dactylo Game");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(w, h));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// wordView = new WordView(new Word("Bonjour")); // TEMPORARY
		// getContentPane().add(wordView); // TEMPORARY

		setNormalMode();

		// setKeyListener();

		this.setVisible(true);
	}

	public void setNormalMode() {
		this.getContentPane().removeAll();
		gameView = new GameView(this, new NormalMode("src/main/resources/sample.txt"));
		this.getContentPane().add(gameView);
		lastPanel = gameView;
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
				WordView actualWord = gameView.getActualWord();
				switch(e.getKeyChar()) {
					case KeyEvent.VK_SPACE:
						// On valide le mot actuel.
						// On passe au suivant et on modifie les stats
						gameView.nextWord();
						return;
					case KeyEvent.VK_BACK_SPACE:
						wordView.removeLetter();
						break;
					default:
						if(Character.isLetter(e.getKeyChar())) {
							actualWord.pushLetter(e.getKeyChar());
						}
						break;
				}
				// I try to revalidate, repaint actualWord, gameView but nothing works...
				revalidate();
				repaint();
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
				return game instanceof NormalMode;
			case "GAME":
				return game instanceof GameMode;
			case "MULTIPLAYER":
				return game instanceof MultiplayerMode;
			default:
				return false;
		}
	}

	public void quit() {
		this.dispose();
		System.exit(0);
	}

} 
  
