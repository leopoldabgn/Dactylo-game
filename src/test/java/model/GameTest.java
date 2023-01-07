package model;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import model.Game.GameType;
import java.util.ArrayList;


public class GameTest {
  @Test
  public void checkGameInfo() {
    Player player = new Player("Rick", 0);
    ArrayList<Player> players = new ArrayList<>();
    players.add(player);
    Game game = GameFactory.getGame(GameType.CHALLENGE, "src/test/resources/sample.txt", players);
    
    // Test game player
    assertThat(game.getActualPlayer().getName()).isEqualTo("Rick");
    // Test game level
    assertThat(game.getLevel()).isEqualTo(1);
    // Test game type
    assertThat(game.getType()).isEqualTo(GameType.CHALLENGE);
  }
}
