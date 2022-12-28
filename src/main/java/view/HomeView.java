package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controllers.HomeController;
import java.awt.*;

public final class HomeView extends JPanel{
  // Panel layout
  private BorderLayout viewLayout = new BorderLayout();
  // Panel background color: for reference see https://coolors.co/palette/001219-005f73-0a9396-94d2bd-e9d8a6-ee9b00-ca6702-bb3e03-ae2012-9b2226
  public static final Color backgroundColor = new Color(0, 95, 115);
  public static final Color backgroundColor2 = new Color(235, 73, 45);
  // Form Components
  private JTextField playerNameInput;
  private JRadioButton radioButtonNormal;
  private JRadioButton radioButtonChallenge;
  private JRadioButton radioButtonMP;
  // HomeController
  private HomeController homeController = new HomeController();

  // Windown reference
  private Window window;
  

  /**
   * Create HomeView
   */
  public HomeView(Window win) {
    // Update Layout and Background color
    this.setLayout(viewLayout);
    this.setBackground(HomeView.backgroundColor);
    // Add components to Panel
    this.add(getTitle("Dactylo Game", 30), BorderLayout.PAGE_START);
    this.add(getLoginBox(), BorderLayout.CENTER);
    this.add(getAuthors("@parismollo & @leopoldabgn", 15), BorderLayout.PAGE_END);

    // Set window
    this.window = win;
  }

  
  /** 
   * Return JLabel with title of this Panel
   * @param title
   * @param fontSize
   * @return JLabel
   */
  public static JLabel getTitle(String title, int fontSize) {
    JLabel gameTitle = new JLabel(title);
    gameTitle.setFont(Window.getNewFont(fontSize));
    gameTitle.setForeground(new Color(0, 0, 0));
    gameTitle.setHorizontalAlignment(JLabel.CENTER);
    gameTitle.setBorder(new EmptyBorder(5, 0, 15, 0));;
    return gameTitle;
  }

  
  /** 
   * Returns JPanel with loginBox
   * @return JPanel
   */
  private JPanel getLoginBox() { 
    // JPanel loginBox = new JPanel();

    JPanel loginBox = new JPanel() {
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
    loginBox.setOpaque(false);
    loginBox.setBorder(new EmptyBorder(10, 15, 10, 15));
    loginBox.setLayout(new GridLayout(3, 1));
    loginBox.setBackground(HomeView.backgroundColor);
    // Add components to loginBox Panel
    //Window.getPanel(0, new GridBagLayout(), )
    JPanel playerInput = getPlayerInputAux("Player name:", 20, "Rick");
    loginBox.add(Window.getPanel(0, new GridBagLayout(), playerInput));
    loginBox.add(getGameModeAux());
    loginBox.add(getPlayButtonAux("Play", 20));
    JPanel whiteBox = new WhiteBox();
    whiteBox.add(loginBox);
    JPanel textAreaBox = new JPanel();
    textAreaBox.setLayout(new GridLayout());
    textAreaBox.setOpaque(false);
    int margin = 30;
    textAreaBox.setBorder(new EmptyBorder(margin, margin, margin, margin));
    textAreaBox.add(whiteBox);
    return textAreaBox;
  }

  
  /** 
   * Returns JLabel with authors of the project
   * @param content
   * @param fontSize
   * @return JLabel
   */
  public static JLabel getAuthors(String content, int fontSize) {
    JLabel authors = new JLabel(content);
    authors.setFont(Window.getNewFont(fontSize));
    authors.setHorizontalAlignment(JLabel.RIGHT);
    authors.setVerticalAlignment(JLabel.BOTTOM);
    authors.setForeground(new Color(0, 0, 0));
    authors.setBorder(new EmptyBorder(10, 0, 5, 0));;
    return authors;
  }

  
  /** 
   * Returns Play button
   * @param content
   * @param fontSize
   * @return JPanel
   */
  private JPanel getPlayButtonAux(String content, int fontSize) {
    JPanel panelWrapper = new JPanel();
    panelWrapper.setBackground(HomeView.backgroundColor);
    JButton playButton = new JButton(content);
    playButton.setFont(Window.getNewFont(fontSize));
    playButton.setForeground(new Color(255, 255, 255));
    playButton.setBackground(new Color(0, 0, 0));
    playButton.setFocusPainted(false);
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    playButton.addActionListener((event) -> this.homeController.playPressed(this.window, this.playerNameInput, this.radioButtonNormal, this.radioButtonChallenge, this.radioButtonMP));
    // playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panelWrapper.add(playButton);
    panelWrapper.setOpaque(false);
    return panelWrapper;
  }

  
  /**
   * Return Panel with game modes 
   * @return JPanel
   */
  private JPanel getGameModeAux() {
    JPanel panelWrapper = new JPanel();
    panelWrapper.setOpaque(false);
    JLabel gameModeLabel = new JLabel("Game Mode: ");
    
    gameModeLabel.setFont(Window.getNewFont(20));
    gameModeLabel.setForeground(new Color(255, 255, 255));
    gameModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    this.radioButtonNormal = new JRadioButton("normal");
    this.radioButtonMP = new JRadioButton("multiplayer");
    this.radioButtonChallenge = new JRadioButton("challenge");
    
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(getRadioButton(this.radioButtonNormal, 15, true));
    buttonGroup.add(getRadioButton(this.radioButtonMP, 15, false));
    buttonGroup.add(getRadioButton(this.radioButtonChallenge, 15, false));
    
    panelWrapper.add(gameModeLabel);
    panelWrapper.add(this.radioButtonNormal);
    panelWrapper.add(this.radioButtonChallenge);
    panelWrapper.add(this.radioButtonMP);
    //panelWrapper.setBorder(BorderFactory.createLineBorder(Color.black));

    return panelWrapper;
  }
  
  /** 
   * Return panel with text input field
   * @param content
   * @param fontSize
   * @param placeholder
   * @return JPanel
   */
  private JPanel getPlayerInputAux(String content, int fontSize, String placeholder) {
    JPanel panelWrapper = new JPanel();
    JLabel playerNameLabel = new JLabel(content);
  
    playerNameLabel.setFont(Window.getNewFont(fontSize));
    playerNameLabel.setForeground(new Color(255, 255, 255));
    playerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    this.playerNameInput = new JTextField(10);
    this.playerNameInput.setFont(Window.getNewFont(fontSize));
    this.playerNameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.playerNameInput.setHorizontalAlignment(JTextField.CENTER);
    this.playerNameInput.setText(placeholder);
    
    panelWrapper.setBackground(HomeView.backgroundColor);
    panelWrapper.add(playerNameLabel);
    panelWrapper.add(playerNameInput);
    // panelWrapper.setBorder(BorderFactory.createLineBorder(Color.black));
    panelWrapper.setOpaque(false);
    return panelWrapper;
  }
    /**
   * @param button
   * @param fontSize
   * @param selected
   * @return JRadioButton
   */
  private JRadioButton getRadioButton(JRadioButton button, int fontSize, boolean selected) {
    button.setFocusPainted(false); 
    button.setOpaque(false);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setFont(Window.getNewFont(15));
    button.setForeground(new Color(0, 0, 0));
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setSelected(selected);
    return button;
  }
}
// [TODO]: reduce distances between components inside box + look into margins