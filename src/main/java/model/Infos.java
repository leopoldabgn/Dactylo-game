package model;

import model.Game.GameType;
/**
 * A class representing information about a game, such as the number of words typed, the duration of the game,
 * and the number of lives remaining.
 *
 */
public final class Infos implements Cloneable {
    
    private static int MAX_WORDS_BEFORE_NEXT_LEVEL = 100;

    private int nbWords, nbCorrectWords; // Le nombre de mots tapés
    private long startTime = -1, duration = -1, endTime = -1; // Le temps en millisecondes
    private int lifes;
    private GameType type = GameType.NORMAL;
    private int wordLevelRef;
    private int level = 1;

    private Infos() {}

    private Infos(int nbWords, int nbCorrectWords, long duration, int lifes, GameType type) {
        this.nbWords = nbWords;
        this.nbCorrectWords = nbCorrectWords;
        this.duration = duration;
        this.lifes = lifes;
        this.type = type; 
    }

    
    /** 
     * @return Infos
     */
    public static Infos empty() {
        return new Infos();
    }

    
    /** 
     * @param nbCorrectWords
     */
    public void setNbCorrectWords(int nbCorrectWords) {
        this.nbCorrectWords = nbCorrectWords;
    }

    
    /** 
     * @param lifes
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    
    /** 
     * @param words
     */
    public void setNbWords(int words) {
        this.nbWords = words;
    }

    
    /** 
     * @return int
     */
    public int nbWords() {
        return nbWords;
    }

    
    /** 
     * @return int
     */
    public int nbCorrectWords() {
        return nbCorrectWords;
    }

    
    /** 
     * @return int
     */
    public int getLifes() {
        return lifes;
    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void setEndTime() {
        this.endTime = System.currentTimeMillis();
    }


    
    /** 
     * @return int
     */
    public int getWordLevelRef() {
        return wordLevelRef;
    }

    public void updateWordLevelRef() {
        wordLevelRef = wordLevelRef >= MAX_WORDS_BEFORE_NEXT_LEVEL ? 0 : wordLevelRef+1;
    }
    
    /** 
     * @return double
     */
    public double speedTime() {
        return 3 * Math.pow(0.9, level);
    }

    
    /** 
     * @return int
     */
    public int getLevel() {
        return level;
    }

    
    /** 
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

  public void updateLevel() {
    if(wordLevelRef == MAX_WORDS_BEFORE_NEXT_LEVEL)
      this.level++;
  }
    
    /** 
     * @return long
     */
    public long getTime() {
        if(endTime == -1) // Le chrono tourne toujours
            return System.currentTimeMillis() - startTime;
        return endTime - startTime; // Si chrono arrêté
    }

    
    /** 
     * @return long
     */
    public long getTimeLeft() {
        if(duration == -1) // Pas de durée définie, donc pas de "temps restant"
            return -1;
        long timeLeft = duration - getTime();
        if(timeLeft < 0)
            timeLeft = 0;
        return timeLeft;
    }

    
    /** 
     * @param duration
     */
    // A vérifier
    public void setDuration(double duration) {
        this.duration = (long)(duration * 1000); // On convertit en millisecondes
    }

    
    /** 
     * @return long
     */
    public long getDuration() {
        return duration;
    }

    
    /** 
     * @return GameType
     */
    public GameType getType() {
        return type;
    }

    
    /** 
     * @param type
     */
    public void setType(GameType type) {
        this.type = type;
    }

    
    /** 
     * @return Infos
     */
    public Infos clone() {
        return new Infos(nbWords, nbCorrectWords, duration, lifes, type);
    }

    
    /** 
     * @return int
     */
    public static int getMaxWordsBeforeNextLevel() {
        return MAX_WORDS_BEFORE_NEXT_LEVEL;
    }

}
