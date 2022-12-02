package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends JFrame {
	public Window(int w, int h) {
		this.setMinimumSize(new Dimension(w, h));
		this.setTitle("Dactlyo Game");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(w, h));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void quit() {
		this.dispose();
		System.exit(0);
	}

} 
  
