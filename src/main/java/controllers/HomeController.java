package controllers;

import java.util.ArrayList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Game;
import model.GameFactory;
import model.Player;
import model.Game.GameType;

public class HomeController {

  /**
   * Call Window.java to update GUI with new Game created
   * @param playerNameInput
   * @param radioButtonNormal
   * @param radioButtonChallenge
   * @param radioButtonMP
   */
  public void playPressed(JTextField playerNameInput, JRadioButton radioButtonNormal, JRadioButton radioButtonChallenge, JRadioButton radioButtonMP) {
    String playerName = playerNameInput.getText();
    GameType gameType = getGameType(radioButtonNormal, radioButtonChallenge, radioButtonMP);
    
    Player player = new Player(playerName, 0);
    ArrayList<Player> players = new ArrayList<>();
    players.add(player);

    Game game = GameFactory.getGame(gameType, "src/main/resources/sample.txt", players);
    // [TODO]: Open next view with game 
    // Window.openGame(game);
    System.out.println(game);
  }

  /**
   * @param radioButtonNormal
   * @param radioButtonChallenge
   * @param radioButtonMP
   * @return GameType
   */
  private GameType getGameType(JRadioButton radioButtonNormal, JRadioButton radioButtonChallenge, JRadioButton radioButtonMP) {
    if(radioButtonNormal.isSelected()) {
      return GameType.NORMAL;
    }else if(radioButtonChallenge.isSelected()) {
      return GameType.CHALLENGE;
    }
    return GameType.MP;
  }

}