package model;

import java.util.ArrayList;

public final class ChallengeGame extends Game {

    public ChallengeGame(ArrayList<Player> players) {
        super(players, GameType.CHALLENGE);
        init();
    }

    @Override
    public void init() {
        Infos infos = getInfos();
        infos.setLifes(getActualPlayer().getLifes());
        infos.setType(GameType.CHALLENGE);
    }

}