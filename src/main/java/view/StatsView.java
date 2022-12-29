package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controllers.StatController;
import model.Game;
import model.Player;

// COLOR PALETTE: https://coolors.co/palette/264653-2a9d8f-e9c46a-f4a261-e76f51

public final class StatsView extends JPanel {
    private Window window;
    private Game game;
    private StatController statController = new StatController();

    public StatsView(Window win, Game game) {
        this.window = win;
        this.game = game;
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        
        this.add(getNorthPan(), BorderLayout.NORTH);
        this.add(getPlayerStats(), BorderLayout.CENTER);
        this.add(getSouthPan(), BorderLayout.SOUTH);
    }


    private JPanel getPlayerStats() {
      JPanel playerInfo = new JPanel();
      playerInfo.setLayout(new GridLayout());
      playerInfo.setOpaque(false);
      int margin = 30;
      playerInfo.setBorder(new EmptyBorder(margin, margin, margin, margin));
      playerInfo.add(getStatsBox());
      return playerInfo;
    }

    private JPanel getStatsBox() { 
      JPanel statsBox = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          g.setColor(new Color(42, 157, 143));
          g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
          g.setColor(Color.BLACK);
          
          Graphics2D g2d = (Graphics2D)g;
          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);        
          g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
          g2d.setStroke(new BasicStroke(5));
          g2d.drawRoundRect(2, 2, getWidth() - 6, getHeight() - 6, 20, 20);
        }
    };
      statsBox.setOpaque(false);
      statsBox.setBorder(new EmptyBorder(10, 15, 10, 15));
      statsBox.setLayout(new GridLayout(3, 1));
      statsBox.setBackground(HomeView.backgroundColor);
      
      addStats(statsBox);
      
      JPanel whiteBox = new WhiteBox();
      whiteBox.add(statsBox);
      
      JPanel wrapper = new JPanel();
      wrapper.setLayout(new GridLayout());
      wrapper.setOpaque(false);
      
      int margin = 30;
      wrapper.setBorder(new EmptyBorder(margin, margin, margin, margin));
      wrapper.add(whiteBox);
      
      return wrapper;
    }


    private void addStats(JPanel statsBox) {
      Player player = this.game.getActualPlayer();
      statsBox.add(Window.getPanel(0, new GridBagLayout(), getStatPanel("Frequency:", player.getPlayerStats().getFrequency()+"ms")));
      statsBox.add(Window.getPanel(0, new GridBagLayout(), getStatPanel("WPM:", player.getPlayerStats().getWords_per_minute()+"")));
      statsBox.add(Window.getPanel(0, new GridBagLayout(), getStatPanel("Precision:", player.getPlayerStats().getPrecision()+"%")));
    }

    private JPanel getNorthPan() {
        JPanel north = new JPanel();
        north.setOpaque(false);
        north.setLayout(new BorderLayout());
        north.setBorder(new EmptyBorder(0, 0, 14, 0));

        JLabel title = HomeView.getTitle("Results", 30);
        title.setVerticalAlignment(JLabel.CENTER);

        JLabel gameMode = Window.getJLabel("Game mode: " + game.getType(), 18, Color.WHITE);
        gameMode.setVerticalAlignment(JLabel.CENTER);
        JLabel playerName = Window.getJLabel("Player: " + game.getPlayers().get(0).getName(), 18, Color.WHITE);
        playerName.setVerticalAlignment(JLabel.CENTER);
        
      
        north.add(title, BorderLayout.CENTER);
        north.add(playerName, BorderLayout.WEST);
        north.add(gameMode, BorderLayout.EAST);

        return north;
    }

    private JPanel getSouthPan() {
        JPanel south = new JPanel();
        south.setOpaque(false);
        south.setLayout(new BorderLayout());

        south.add(getButtons(), BorderLayout.WEST);
        south.add(HomeView.getAuthors("@parismollo & @leopoldabgn", 15), BorderLayout.EAST);

        return south;
    }

    private JPanel getButtons() {
      JPanel panel = new JPanel();
      panel.setOpaque(false);
      GridLayout gl = new GridLayout(1, 2);
      gl.setHgap(5);
      panel.setLayout(gl);

      JButton playAgain = createButton("Play Again");
      playAgain.addActionListener((event) -> this.statController.playPressed(this.window, this.game));

      JButton menuButton = createButton("Menu");
      menuButton.addActionListener((event) -> this.statController.menuPressed(this.window, this.game));

      panel.add(playAgain);
      panel.add(menuButton);
      return panel;
    }


    private JButton createButton(String title) {
      JButton playAgain = new JButton(title);
      playAgain.setFont(Window.getNewFont(15));
      playAgain.setForeground(new Color(255, 255, 255));
      playAgain.setBackground(new Color(0, 0, 0));
      playAgain.setFocusPainted(false);
      playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
      return playAgain;
    }

    private JPanel getStatPanel(String title, String value) {
      JPanel panel = new JPanel();
      panel.setOpaque(false);
      panel.add(Window.getJLabel(title, 15, Color.BLACK));
      panel.add(Window.getJLabel(value, 20, new Color(233, 196, 106)));
      return panel;
    }
}
