package model;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {
  @Test
  public void CheckWord() {
    // 1. Create word
    Word w = new Word("TEST");
    // 2. pushLetter
    w.pushLetter('T');
    assertThat(w.getPushedContent()).isEqualTo("T");
    // 3. removeLetter
    w.removeLetter();
    assertThat(w.getPushedContent()).isEqualTo("");
    // 4. WordStats
    w.pushContent("TEST");
    assertThat(w.getContent()).isEqualTo("TEST");
    assertThat(w.getPushedContent()).isEqualTo("TEST");
    // 4.a nbGoodChars
    assertThat(w.getWordStats().nbGoodChars()).isEqualTo(4);
    // 4.b nbErrors
    assertThat(w.getWordStats().nbErrors()).isEqualTo(0);
  }
}
