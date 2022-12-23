package model;

public final class Infos implements Cloneable {
    
    private int nbWords; // Le nombre de mots tapés
    private int time; // Le temps en secondes

    private Infos() {}

    private Infos(int nbWords, int time) {
        this.nbWords = nbWords;
        this.time = time;
    }

    public static Infos empty() {
        return new Infos();
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

    public Infos clone() {
        return new Infos(nbWords, time);
    }

}
