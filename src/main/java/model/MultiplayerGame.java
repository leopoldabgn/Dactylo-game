package model;

import java.util.ArrayList;

public final class MultiplayerGame extends Game {

    public MultiplayerGame(String pathToData, ArrayList<Player> players, GameType type) {
        super(pathToData, players, type);
    }

    @Override
    public void init() {
        // TODO
    }

}