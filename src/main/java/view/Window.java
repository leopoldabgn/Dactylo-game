package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import model.Word;

public class Window extends JFrame {

	private WordView wordView; // TEMPORARY

	public Window(int w, int h) {
		this.setMinimumSize(new Dimension(w, h));
		this.setTitle("Dactylo Game");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(w, h));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		wordView = new WordView(new Word("Bonjour")); // TEMPORARY

		getContentPane().add(wordView); // TEMPORARY

		setKeyListener();

		this.setVisible(true);
	}

	public void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				switch(e.getKeyChar()) {
					case KeyEvent.VK_SPACE: // TEMPORARY
						return;
					case KeyEvent.VK_BACK_SPACE:
						wordView.removeLetter();
						break;
					default:
						if(Character.isLetter(e.getKeyChar()))
							wordView.pushLetter(e.getKeyChar());
						break;
				}
				wordView.setColoredText();
			}

		});
	}

	public void quit() {
		this.dispose();
		System.exit(0);
	}

} 
  
