package model;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordQueueTest {
  @Test
  public void checkWordQueue() {
    // 1. Constructor 
    WordQueue wq = new WordQueue("src/test/resources/sample.txt", true);
    // 2. check if queue started
    assertThat(wq.getQueue().size()).isEqualTo(50/3);
    // 3. poll - check if size changes
    wq.poll();
    assertThat(wq.getQueue().size()).isEqualTo((50/3)-1);
    // 4. add - check if size changes
    wq.add();
    assertThat(wq.getQueue().size()).isEqualTo((50/3));

  }
}