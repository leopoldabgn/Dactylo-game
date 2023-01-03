package controllers;

import java.util.ArrayList;

import model.Game;
import model.GameFactory;
import model.Player;
import model.Game.GameType;
import utils.Utils;
import view.Window;

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
    // Game.erase()
    win.setHomeView();
    // Utils.log("Menu Pressed");
  }
}
