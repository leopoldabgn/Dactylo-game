package controllers;

import java.util.ArrayList;

import model.Game;
import model.GameFactory;
import model.Player;
import model.Game.GameType;
import utils.Utils;
import view.Window;

/**
The StatController class is a final class that contains methods to
reset the game data and return to the home screen.
*/

public final class StatController {
  public void playPressed(Window win, Game game) {
    ArrayList<Player> players = game.getPlayers();
    players.stream().forEach(player -> player.resetPlayerData());
    GameType gt = game.getType();
    Game new_game = GameFactory.getGame(gt, players);
    win.setGameView(new_game);
    
    // Utils.log("Play Pressed!");
    Utils.log("Game Info : "+ new_game.toString());
    // Utils.log("player lifes: "+ players.get(0).getLifes());
  }

  public void menuPressed(Window win, Game game) {
    win.setHomeView();
    // Utils.log("Menu Pressed");
  }
}
