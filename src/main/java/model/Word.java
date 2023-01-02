package model;

import java.util.ArrayList;
import java.util.List;

public final class Word implements Cloneable {

    private String content, pushContent;
    private boolean validate;
    private WordStats stats;
    private boolean special = false;

    public Word(String content) {
        this.content = content;
        this.pushContent = "";
        this.stats = new WordStats();
    }
    
    private Word(Word word) {
        this.content = word.content;
        this.pushContent = word.pushContent;
        this.validate = word.validate;
        this.stats = word.stats.clone();
    }

    /**
     * @param letter
     */
    public void pushLetter(char letter) {
        // We don't accept another letter the length of pushContent is already equals
        // to the length of the word.
        // Maybe we will change this in the futur
        if(pushContent.length() == content.length())
            return;
        pushContent += letter;
    }

    public void removeLetter() {
        int len = pushContent.length();
        if(len == 0)
            return;
        pushContent = pushContent.substring(0, len-1);
    }

    /** 
     * @return String
     */
    public String getContent() {
        return content;
    }
    
    /** 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /** 
     * @return String
     */
    public String getPushedContent() {
        return pushContent;
    }

    /** 
     * @param pushContent
     */
    public void pushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    
    /** 
     * @return boolean
     */
    public boolean isValidate() {
        return validate;
    }

    public void validate() {
        this.validate = true;
    }

    
    /** 
     * @return boolean
     */
    public boolean isSpecial() {
        return special;
    }

    
    /** 
     * @param special
     */
    public void setSpecial(boolean special) {
        this.special = special;
    }

    public final class WordStats implements Cloneable {

        private ArrayList<CharStats> charSeq;

        public WordStats() {
            charSeq = new ArrayList<>();
            content.chars().forEach(c -> charSeq.add(new CharStats((char)c)));
        }

        private WordStats(WordStats stats) {
            this.charSeq = new ArrayList<>(stats.charSeq);
        }

        /** Number of good chars in the push word in comparison with the real word.
         *  This function can be useful to generate stats for a player in a game
         * @return int
         */
        public int nbGoodChars() {
            if(content.equals(pushContent))
                return (int)charSeq.stream().filter(c -> !c.erased).count();
            return 0;
        }

        /** Number of errors in the push word in comparison with the real word.
         *  Permet de calculer la distance d'édition entre deux mots.
         *  Cette fonction est utile pour calculer le nombre d'erreurs dans un mot
         *  pour le mode Challenge (mode jeu)
         * @return int
         */
        public int nbErrors() {
            int maxLen = Math.max(content.length(), pushContent.length());
            int dist = 0;
            // On parcourt les deux mots caractère par caractère
            for(int i=0;i<maxLen;i++) {
                if(i >= content.length() || i >= pushContent.length()) {
                    dist += 1;
                    continue;
                }
                // Si c'est le même caractère on ajoute rien. Sinon on ajoute 1
                dist += content.charAt(i) == pushContent.charAt(i) ? 0 : 1;
            }

            return dist;
        }

        public List<CharStats> getGoodChars() {
            if(content.equals(pushContent))
                return charSeq.stream().filter(c -> !c.erased).toList();
            return new ArrayList<>();
        }

        public WordStats clone() {
            return new WordStats(this);
        }

        public int getWordSize() {
            return this.charSeq.size();
        }

    }

    // On retient le temps a chaque fois qu'un caractère est tapé
    // Si un caractère est effacé on met erased à true
    // A la fin on regarde tous les mots entièrement bien écrit
    // Pour chacun des mots, on regarde le temps entre chaque caractère utile
    // Sachant qu'un caractère utile est un caractère qui n'a pas été effacé et
    // donc qui n'a pas erased à true. Il faut que erased soit faux

    // On récupère les nouveaux caractères utiles écrit à chaque fois qu'on termine un mot
    // On concatène cette liste à la liste des caractères utiles dans la classe Player
    // On peut encuiste calculer a chaque fois le temps entre deux caractères utiles à la suite puis on calcul

    public static class CharStats {
        private Character c;
        private boolean erased; // A été effacé ou non
        private long time; // temps entre le dernier caractère utile et lui

        public CharStats(Character c) {
            this.c = c;
        }

        public long getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "["+c+", time="+time+"]";
        }

    }

    
    /** On change l'attribut time du caractère actuel. time represente
     *  l'heure exacte en millisecondes où on a écrit le caractère
     * @param time
     */
    public void setTimeActualChar(long time) {
        int index = indexActualChar();
        if(index != -1)
            stats.charSeq.get(index).time = time;
    }

    /*
     * Permet de mettre l'attribut erased du caractère actuel à true.
     * Ceci est important pour faire le calcul des statistiques. En effet,
     * un caractère bien tapé ne compte pas si il a été effacé
     */
    public void erasedActualChar() {
        int index = indexActualChar();
        if(index != -1)
            stats.charSeq.get(index).erased = true;
    }

    
    /** Permet de recupérer l'index du dernier caractère écrit
     * @return int
     */
    private int indexActualChar() {
        int index = pushContent.length() - 1;
        if(index < 0 || index >= content.length())
            return -1;
        return index;
    }

    
    /** 
     * @return WordStats
     */
    public WordStats getWordStats() {
        return stats.clone();
    }

    
    /** 
     * @return Word
     */
    public Word clone() {
        return new Word(this);
    }

}