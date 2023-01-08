package model;

import java.util.ArrayList;

/**
 * Represents a Challenge game in the game application.
 * This class extends the abstract Game class and implements the #init() method.
 *
 * @see Game
 */
public final class ChallengeGame extends Game {

    /**
     * Constructs a new Challenge game with the specified data file path and list of players.
     *
     * @param players the list of players participating in this game
    */
    public ChallengeGame(ArrayList<Player> players) {
        super(players, GameType.CHALLENGE);
        this.setWordQueue(WordQueue.challengeQueue());
        setActualWord(getWordQueue().getQueue().peek());
        init();
    }

    /**
     * Initializes the Challenge game by setting the lives and type of game in the Infos object.
     * The number of lives is set to the number of lives of the current player.
     * The type of game is set to GameType#CHALLENGE.
     */
    @Override
    public void init() {
        Infos infos = getInfos();
        infos.setLifes(getActualPlayer().getLifes());
        infos.setType(GameType.CHALLENGE);
    }
}