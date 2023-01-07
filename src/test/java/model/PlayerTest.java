package model;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
  @Test
  public void checkPlayerInfo() {
    Player player = new Player("Morty", 150);
    // Check lifes
    assertThat(player.getLifes()).isEqualTo(20);
    // Check points
    assertThat(player.getPoints()).isEqualTo(150);
     // Check name
    assertThat(player.getName()).isEqualTo("Morty");
  }
}
