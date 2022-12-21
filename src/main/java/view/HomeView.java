package view;

import javax.swing.*;

import controllers.HomeController;

import java.awt.*;

public class HomeView extends JPanel{
  private BorderLayout viewLayout = new BorderLayout();
  private Color backgroundColor = new Color(0, 95, 115);
  private String title = "Dactylo Game";
  private JTextField playerNameInput;
  private JRadioButton radioButtonNormal;
  private JRadioButton radioButtonChallenge;
  private JRadioButton radioButtonMP;
  private HomeController homeController = new HomeController();
  

  public HomeView() {
    this.setLayout(viewLayout);
    this.setBackground(this.backgroundColor);
    setViewTitle();
    setLoginBox();
    setAuthors();
  }

  private void setViewTitle() {
    JLabel gameTitle = new JLabel(this.title);
    gameTitle.setFont(Window.getNewFont(30));
    gameTitle.setForeground(new Color(0, 0, 0));
    gameTitle.setHorizontalAlignment(JLabel.CENTER);
    this.add(gameTitle, BorderLayout.PAGE_START);
  }

  private void setLoginBox() {
    JPanel loginBox = new JPanel();
    loginBox.setLayout(new BoxLayout(loginBox, BoxLayout.Y_AXIS));
    loginBox.setBackground(this.backgroundColor);

    setPlayerInputAux(loginBox);
    setGameModeAux(loginBox);
    setPlayButtonAux(loginBox);
    this.add(loginBox, BorderLayout.CENTER);
  }

  private void setPlayButtonAux(JPanel loginBox) {
    JPanel panelWrapper = new JPanel();
    panelWrapper.setBackground(this.backgroundColor);
    JButton playButton = new JButton("Play");
    playButton.setFont(Window.getNewFont(20));
    playButton.setForeground(new Color(255, 255, 255));
    playButton.setBackground(new Color(0, 0, 0));
    playButton.setFocusPainted(false);
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    playButton.addActionListener((event) -> this.homeController.playPressed(this.playerNameInput, this.radioButtonNormal, this.radioButtonChallenge, this.radioButtonMP));
    panelWrapper.add(playButton);
    loginBox.add(panelWrapper);
  }

  private void setGameModeAux(JPanel loginBox) {
    JPanel panelWrapper = new JPanel();
    panelWrapper.setBackground(this.backgroundColor);
    JLabel gameModeLabel = new JLabel("Game Mode: ");
    gameModeLabel.setFont(Window.getNewFont(20));
    gameModeLabel.setForeground(new Color(255, 255, 255));
    gameModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.radioButtonNormal = new JRadioButton("normal");
    this.radioButtonNormal.setSelected(true);
    this.radioButtonNormal.setFocusPainted(false); 
    this.radioButtonNormal.setOpaque(false);
    this.radioButtonNormal.setFont(Window.getNewFont(15));
    this.radioButtonNormal.setForeground(new Color(0, 0, 0));
    this.radioButtonNormal.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.radioButtonChallenge = new JRadioButton("challenge");
    this.radioButtonChallenge.setFocusPainted(false); 
    this.radioButtonChallenge.setOpaque(false);
    this.radioButtonChallenge.setFont(Window.getNewFont(15));
    this.radioButtonChallenge.setForeground(new Color(0, 0, 0));
    this.radioButtonChallenge.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.radioButtonMP = new JRadioButton("multiplayer");
    this.radioButtonMP.setFocusPainted(false); 
    this.radioButtonMP.setOpaque(false);
    this.radioButtonMP.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.radioButtonMP.setFont(Window.getNewFont(15));
    this.radioButtonMP.setForeground(new Color(0, 0, 0));
    this.radioButtonMP.setAlignmentX(Component.CENTER_ALIGNMENT);
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(radioButtonNormal);
    buttonGroup.add(radioButtonChallenge);
    buttonGroup.add(radioButtonMP);
    
    panelWrapper.add(gameModeLabel);
    panelWrapper.add(radioButtonNormal);
    panelWrapper.add(radioButtonChallenge);
    panelWrapper.add(radioButtonMP);

    loginBox.add(panelWrapper);
  }

  private void setPlayerInputAux(JPanel loginBox) {
    JPanel panelWrapper = new JPanel();
    panelWrapper.setBackground(this.backgroundColor);
    JLabel playerNameLabel = new JLabel("Player name: ");
    playerNameLabel.setFont(Window.getNewFont(20));
    playerNameLabel.setForeground(new Color(255, 255, 255));
    playerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.playerNameInput = new JTextField(10);
    playerNameInput.setFont(Window.getNewFont(20));
    playerNameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerNameInput.setHorizontalAlignment(JTextField.CENTER);
    playerNameInput.setText("Rick");
    panelWrapper.add(playerNameLabel);
    panelWrapper.add(playerNameInput);

    loginBox.add(panelWrapper);
  }

  private void setAuthors() {
    JLabel authors = new JLabel("@parismollo & @leopoldabgn");
    authors.setFont(Window.getNewFont(15));
    authors.setHorizontalAlignment(JLabel.RIGHT);
    authors.setVerticalAlignment(JLabel.BOTTOM);
    authors.setForeground(new Color(0, 0, 0));
    this.add(authors, BorderLayout.PAGE_END);
  }
}

// [TODO]: refactor code
// [TODO]: reduce distances between components inside box + look into margins