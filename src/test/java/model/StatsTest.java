package model;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import model.Game.GameType;

public class StatsTest {
  @Test
  public void checkStatsInfo() {
    //  Check stats
    Player player = new Player("Rick", 0);
    ArrayList<Player> players = new ArrayList<>();
    players.add(player);
    Game game = GameFactory.getGame(GameType.CHALLENGE, players);
    Word word = game.getActualWord();
    
    String[] mots = new String[] {
      "Quds",
      word.getContent()
    };

    for(String m : mots) {
      Word w = game.getActualWord();
      for(char c : (m.toCharArray()))
        w.pushLetter(c);
    //   w.removeLetter();
    //   w.pushLetter(m.charAt(m.length()-1));
      player.concatToGoodChars(w.getWordStats().getGoodChars());
      game.nextWord();
    }
    
    for(int i=0;i<mots[1].length() * 2;i++)
      player.keyPressed();
    
    Stats st = Stats.createStats(game, player);
    player.updatePlayerStats();
    // assertThat(st.getPrecision()).isEqualTo(50);
    assertThat(player.getGoodChars().size()).isEqualTo(mots[1].length());

  }
}
