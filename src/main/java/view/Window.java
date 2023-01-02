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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ChallengeGame;
import model.Game;
import model.MultiplayerGame;
import model.NormalGame;
import model.Player;


public final class Window extends JFrame {
	// Palette reference: https://coolors.co/palette/001219-005f73-0a9396-94d2bd-e9d8a6-ee9b00-ca6702-bb3e03-ae2012-9b2226

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

		getContentPane().setBackground(HomeView.backgroundColor);
		
		setHomeView();

		//setStatsView();

		// setNormalMode();

		// setKeyListener();

		setNormalModeKeyListener();

		this.setVisible(true);
	}

	public void setHomeView() {
		// On remet à zero gameView
		gameView = null;
		this.getContentPane().removeAll();
		this.getContentPane().add(new HomeView(this));
		revalidate();
		repaint();
	}


	public void setGameView(Game game) {
		// [TODO]: to finish
		switch (game.getType()) {
			case NORMAL:
				setNormalMode(game);
				break;
			case CHALLENGE:
				setChallengeMode(game);
				break;
			default:
				setNormalMode(game);
				break;
		}
	}

	public void setStatsView(Game game) {
		// On remet à zero gameView
		gameView = null;
		this.getContentPane().removeAll();
		this.getContentPane().add(new StatsView(this, game));
		// setNormalModeKeyListener();
		revalidate();
		repaint();
	}

	public void setNormalMode(Game game) {
		this.getContentPane().removeAll();
		gameView = new GameView(this, game);
		this.getContentPane().add(gameView);
		// setNormalModeKeyListener();
		revalidate();
		repaint();
		requestFocus();
	}


	public void setChallengeMode(Game game) {
		this.getContentPane().removeAll();		
		gameView = new GameView(this, game);
		this.getContentPane().add(gameView);
		// setNormalModeKeyListener();
		revalidate();
		repaint();
		requestFocus();
	}

	public void setNormalModeKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(!isMode("NORMAL") && !isMode("GAME"))
					return;
				// On lance le timer si besoin
			
				if(!gameView.isRunning())
					gameView.startTimer();
			
				WordView actualWord = gameView.getActualWord();
				Player player = gameView.getActualPlayer();
				switch(e.getKeyChar()) {
					case KeyEvent.VK_SPACE:
						// ICI: - on vérifie si le mot est bien tapé (pas besoin car déjà fait dans Word.WordStats)
						// - Si oui on récupère la liste des caractères utiles de ce mot
						// - Enfin, on ajoute cette liste à celle dans Player: player.addGoodChars(<maliste>)
						player.concatToGoodChars(actualWord.getWordStats().getGoodChars());
						// On valide le mot actuel.
						actualWord.validate();
						wordsFullyvalidated(actualWord); 
						player.updateLife(actualWord.getWordStats().nbErrors(), false);
						gameView.getInfosBox().setLifes(player.getLifes());
						gameView.getInfosBox().setLevel(gameView.getGame().getLevel());
						// Utils.log("Player lifes: "+ player.getLifes());
						// Utils.log("Erros:"+actualWord.getWordStats().nbErrors());
						// On passe au suivant et on modifie les stats
						gameView.nextWord();
						// Si besoin, on retire un certain nombre de mots déjà écrit pour laisser plus de place dans la zone de texte
						gameView.removeUselessWords();
						break;
					case KeyEvent.VK_BACK_SPACE:
						actualWord.erasedActualChar(); // Important de le faire avant le removeLetter()
						actualWord.removeLetter();
						break;
					default:
						if(Character.isLetter(e.getKeyChar())) {
							actualWord.pushLetter(e.getKeyChar());
							
							actualWord.setTimeActualChar(gameView.getTime());
							// Pour moi, on ne doit compter que les lettres ?
							player.keyPressed(); // On augmente le compteur de touches
						}
						break;
				}
				
				// PEUT ETRE ICI :
				// wordView.set...

				// Pour une raison obscure, on doit rafraichir deux fois l'affichage
				// pour que le texte se mette à jour...
				for(int i=0;i<2;i++) {
					gameView.getTextArea().revalidate();
					revalidate();
					repaint();
				}
			}

			private void wordsFullyvalidated(WordView actualWord) {
				if (actualWord.getWordStats().nbGoodChars() == actualWord.getWordStats().getWordSize()) {
					gameView.getInfosBox().addWord();
					gameView.getGame().updateLevel();
					if(actualWord.getWord().isSpecial()) {
						gameView.getGame().getActualPlayer().updateLife(actualWord.getWord().getWordStats().getWordSize(), true);
					}
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
  
