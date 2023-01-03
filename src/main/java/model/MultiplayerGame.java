package model;

import java.util.ArrayList;

public final class MultiplayerGame extends Game {

    public MultiplayerGame(ArrayList<Player> players) {
        super(players, GameType.MP);
    }

    @Override
    public void init() {
        // TODO
    }

}