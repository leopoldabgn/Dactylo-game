// package model;
// import java.util.ArrayList;
// import static org.assertj.core.api.Assertions.assertThat;

// import org.junit.jupiter.api.Test;

// import model.Game.GameType;

// public class StatsTest {
//   @Test
//   public void checkStatsInfo() {
//     //  Check stats
//     Player player = new Player("Rick", 0);
//     ArrayList<Player> players = new ArrayList<>();
//     players.add(player);
//     Game game = GameFactory.getGame(GameType.CHALLENGE, "src/test/resources/sample.txt", players);
    
//     String[] mots = new String[] {
//       "Quzz",
//       "equidem"
//     };

//     for(String m : mots) {
//       Word w = game.getActualWord();
//       for(char c : (m.toCharArray()))
//         w.pushLetter(c);
      
//       player.concatToGoodChars(w.getWordStats().getGoodChars());
//       game.nextWord();
//     }
//     game.nextWord();
//     for(int i=0;i<14;i++)
//       player.keyPressed();
    
//     Stats st = Stats.createStats(game, player);
//     player.updatePlayerStats();
//     // assertThat(st.getPrecision()).isEqualTo(50);
//     assertThat(player.getGoodChars().size()).isEqualTo(7);

//   }
// }
