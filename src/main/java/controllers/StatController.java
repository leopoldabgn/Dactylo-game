package controllers;

import model.Game;
import view.Window;

public class StatController {
  public void playPressed(Window win, Game game) {
    game.reset();
    win.setGameView(game);
    // Utils.log("Play Pressed!");
  }

  public void menuPressed(Window win, Game game) {
    // Game.erase()
    win.setHomeView();
    // Utils.log("Menu Pressed");
  }
}
