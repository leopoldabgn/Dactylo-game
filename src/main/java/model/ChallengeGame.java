package model;

import java.util.ArrayList;

public final class ChallengeGame extends Game {

    public ChallengeGame(String pathToData, ArrayList<Player> players) {
        super(pathToData, players, GameType.CHALLENGE);
    }

    @Override
    public void init() {
        // TODO
    }

}