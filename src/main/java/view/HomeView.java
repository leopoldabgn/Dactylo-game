package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class HomeView extends JPanel{
  private BorderLayout homeLayout = new BorderLayout();
  private Color backgroundColor = new Color(0, 95, 115);


  private JPanel gameTitle() {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Dactylo Game");
    panel.setLayout(new BorderLayout());
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    label.setForeground(new Color(255, 255, 255));
    panel.add(label);
    panel.setBackground(backgroundColor);
    return panel;
  }

  private JPanel authorsName() {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("by @parismollo & @leopoldabgn");
    panel.setLayout(new BorderLayout());
    label.setHorizontalAlignment(SwingConstants.RIGHT);
    label.setFont(new Font("Arial", Font.BOLD, 15));
    label.setForeground(new Color(255, 255, 255));
    panel.add(label);
    panel.setBackground(backgroundColor);
    return panel;
  }

  private JPanel centerBox() {
    JPanel panel = new JPanel();
    // set size, color and layout
    //panel.setPreferredSize(new Dimension(300, 300));
    panel.setLayout(new BorderLayout());
    panel.setBackground(new Color(255, 255, 255));
    Border blackline = BorderFactory.createLineBorder(Color.black, 5, true);
    panel.setBorder(blackline);
    panel.setBorder(new EmptyBorder(50, 50, 50, 50));
    // add text input
    // add game input
    // add button
    return panel;
  }

  public HomeView() {
    this.setLayout(this.homeLayout);
    this.setBackground(this.backgroundColor);
    this.add(gameTitle(), BorderLayout.PAGE_START);
    this.add(authorsName(), BorderLayout.PAGE_END);
    // center box (player name, game mode, play button)
    JPanel pan = new JPanel();
    pan.add(centerBox());
    this.add(pan, BorderLayout.CENTER);

  }
}
