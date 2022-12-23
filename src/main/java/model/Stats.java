package model;

public final class Stats implements Cloneable {
    
    private int nbWords; // Le nombre de mots tapés
    private int time; // Le temps en secondes

    private Stats() {}

    private Stats(int nbWords, int time) {
        this.nbWords = nbWords;
        this.time = time;
    }

    public static Stats empty() {
        return new Stats();
    }

    public void setNbWords(int nbWords) {
        this.nbWords = nbWords;
    }

    public int nbWords() {
        return nbWords;
    }

    public void removeTime(int seconds) {
        time += seconds;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public Stats clone() {
        return new Stats(nbWords, time);
    }

}
