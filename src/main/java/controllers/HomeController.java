package controllers;

import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Game;
import model.GameFactory;
import model.Player;
import model.Game.GameType;

public class HomeController {

  public void playPressed(JTextField playerNameInput, JRadioButton radioButtonNormal, JRadioButton radioButtonChallenge, JRadioButton radioButtonMP) {
    String playerName = playerNameInput.getText();
    GameType gameType = getGameType(radioButtonNormal, radioButtonChallenge, radioButtonMP);
    Player player = new Player(playerName, 0);
    ArrayList<Player> players = new ArrayList<>();
    players.add(player);
    // [TODO]: Temporary solution below -> allow user to pick data to pass to game (different words)
    Game g = GameFactory.getGame(gameType, "src/main/resources/sample.txt", players);
    System.out.println(g);
  }

  private GameType getGameType(JRadioButton radioButtonNormal, JRadioButton radioButtonChallenge, JRadioButton radioButtonMP) {
    if(radioButtonNormal.isSelected()) {
      return GameType.NORMAL;
    }else if(radioButtonChallenge.isSelected()) {
      return GameType.CHALLENGE;
    }
    return GameType.MP;
  }

}