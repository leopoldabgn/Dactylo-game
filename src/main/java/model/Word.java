package model;

import java.util.ArrayList;
import java.util.List;
/**
 * A class that represents a word in a typing game.
 *
 * The class has four fields: content, pushContent, validate, and stats.
 * - content: the actual word
 * - pushContent: the word as it has been typed so far
 * - validate: a boolean flag indicating whether the word has been completed and is correct
 * - stats: an instance of the inner class WordStats, which keeps track of statistics for the word
 *
 * The class has a constructor that takes a String representing the content of the word, and initializes the pushContent field to an empty String.
 * It also has a private copy constructor that takes a Word and creates a new Word with the same field values.
 *
 * The class has two methods for modifying the pushContent field: `pushLetter` and `removeLetter`. It also has getters and setters for the content, pushContent, and special fields.
 */
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
    /**
     * An inner class of the Word class that keeps track of statistics for a word.
     *
     * The class has a single field, charSeq, which is a list of CharStats objects representing statistics for each character in the word.
     *
     * The class has a default constructor that initializes charSeq by creating a CharStats object for each character in the word.
     * It also has a private copy constructor that takes a WordStats and creates a new WordStats with the same field values.
     *
     * The class has methods for calculating the number of good characters and errors in the word, as well as a method for calculating the Levenshtein distance between the word and another word.
     * It also has a clone method that creates a copy of the object.
     */
    public final class WordStats implements Cloneable {

        private ArrayList<CharStats> charSeq;

        public WordStats() {
            charSeq = new ArrayList<>();
            content.chars().forEach(c -> charSeq.add(new CharStats((char)c)));
        }

        private WordStats(WordStats stats) {
            this.charSeq = new ArrayList<>(stats.charSeq);
        }

        /**
        * Number of good chars in the push word in comparison with the real word.
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

    /*
    * Every time a character is typed, the time is recorded. If a character is erased, we set erasing to true. 
    * At the end, we look at all the fully correctly written words. For each of these words, we look at the time between 
    * each useful character. We know that a useful character is a character that has not been erased, so it has not been
    * set to true. Erasing must be false. We retrieve the new useful characters written each time we finish a word. 
    * We concatenate this list with the list of useful characters in the Player class. 
    * We can then calculate the time between two useful characters in a row and then calculate." 
    */

    public static class CharStats {
        private Character c;
        private boolean erased; 
        private long time;

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