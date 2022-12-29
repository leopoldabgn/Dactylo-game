package model;

public final class Infos implements Cloneable {
    
    private int nbWords; // Le nombre de mots tapés
    private long startTime = -1, duration = -1, endTime = -1; // Le temps en millisecondes

    private Infos() {}

    private Infos(int nbWords, long duration) {
        this.nbWords = nbWords;
        this.duration = duration;
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

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void setEndTime() {
        if(getTimeLeft() == -1)
            this.endTime = getTime();
        else
            this.endTime = duration - getTimeLeft();
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

    public Infos clone() {
        return new Infos(nbWords, duration);
    }

}
