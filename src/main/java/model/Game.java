package model;

public abstract sealed class Game permits NormalMode, GameMode, MultiplayerMode { // sealed
  private final WordQueue wordQueue;
  private Word actualWord;

  public abstract void init();

  public Game(String pathToData) {
    this.wordQueue = new WordQueue(pathToData);
    actualWord = wordQueue.getQueue().peek();
  }
  
  public WordQueue getWordQueue() {
    return wordQueue;
  }

  public Word nextWord() {
    actualWord = wordQueue.getQueue().poll();
    return actualWord;
  }

  public Word getActualWord() {
    return actualWord;
  }

}