package model;

import model.Game.GameType;

public final class Infos implements Cloneable {
    
    private int nbWords; // Le nombre de mots tapés
    private long startTime = -1, duration = -1, endTime = -1; // Le temps en millisecondes
    private int lifes;
    private GameType type = GameType.NORMAL;
    private int wordLevelRef;
    private int level = 1;

    private Infos() {}

    private Infos(int nbWords, long duration, int lifes, GameType type) {
        this.nbWords = nbWords;
        this.duration = duration;
        this.lifes = lifes;
        this.type = type; 
    }

    public static Infos empty() {
        return new Infos();
    }

    public void setNbWords(int nbWords) {
        this.nbWords = nbWords;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int nbWords() {
        return nbWords;
    }

    public int getLifes() {
        return lifes;
    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void setEndTime() {
        if(getTimeLeft() == -1)
            this.endTime = getTime();
        else
            this.endTime = duration - getTimeLeft();
    }


    public int getWordLevelRef() {
        return wordLevelRef;
    }

    public void updateWordLevelRef() {
        if(this.wordLevelRef == 5) {
            this.wordLevelRef = 0;
        }else {
            this.wordLevelRef+=1;
        }
        
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getTime() {
        if(endTime == -1) // Le chrono tourne toujours
            return System.currentTimeMillis() - startTime;
        return endTime; // Si chrono arrêté, alors time = endTime;
    }

    public long getTimeLeft() {
        if(duration == -1) // Pas de durée définie, donc pas de "temps restant"
            return -1;
        long timeLeft = duration - getTime();
        if(timeLeft < 0)
            timeLeft = 0;
        return timeLeft;
    }

    // A vérifier
    public void setDuration(int duration) {
        this.duration = duration * 1000; // On convertit en millisecondes
    }

    public long getDuration() {
        return duration;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Infos clone() {
        return new Infos(nbWords, duration, lifes, type);
    }

}
