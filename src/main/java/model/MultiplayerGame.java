package model;

import java.util.ArrayList;

public final class MultiplayerGame extends Game {

    public MultiplayerGame(String pathToData, ArrayList<Player> players) {
        super(pathToData, players, GameType.MP);
    }

    @Override
    public void init() {
    }

}