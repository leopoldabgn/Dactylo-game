package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public final class WordQueue {

    private Queue<Word> queue = new LinkedList<>();
    private Iterator<String> dataSource;
    private int MAX_SIZE = 50;
    private boolean add_special = false;
    
    private static String[] texts = new String[] {
        "src/main/resources/sample.txt",
        "src/main/resources/fr.txt"
    };
    
    private WordQueue(boolean add_special) {
        this((int)(Math.random() * texts.length), add_special);
    }

    private WordQueue(int data, boolean add_special) {
        this.dataSource = (Iterator<String>) createIterable(texts[data]);
        this.add_special = add_special;
        startQueue();
    }

    public static WordQueue normalQueue() {
        return new WordQueue(false);
    }
    
    public static WordQueue challengeQueue() {
        return new WordQueue(true);
    }

    /**
    *  A private method that initializes the queue by adding a set number of items to it. 
    * @param None.
    * @return void.
    */
    private void startQueue() {
        // Au debut, la file contient 1/3 de sa capacité
        for(int i=0; i<MAX_SIZE/3; i++) {
            this.add();
        }
    }


    /** 
     * Convert text file into Iterator. Used as a data source for the queue.
     * @param dataPath
     * @return Iterator<String>
     */
    private Iterator<String> createIterable(String dataPath) {
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(dataPath));
            List<String> temp = new ArrayList<>();
            fileContent.stream().forEach(x -> Arrays.asList(x.split(" ")).forEach(y -> temp.add(deleteChars(y, ";.,?:!\"’'«»123456789ÇÀÉÈ"))));
            return temp.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Retrieves and removes the head (first element) of this queue.
     * @return Word 
     */
    public Word poll() {
        if(!(this.queue.isEmpty())) {
            return this.queue.poll();
        }
        return null;
    }

    private String deleteChars(String str, String charsToRemove) {
        StringBuilder builder = new StringBuilder();
        for(Character c : str.toCharArray()) {
            if(charsToRemove.contains(c+""))
                continue;
            builder.append(c);
        }
        return builder.toString();
    }
    
    /** 
     * Add the next word in the queue if not full.
     * @return boolean
     */
    public Word add() {
        if(this.queue.size() < this.MAX_SIZE) {
            Word new_word = new Word(this.dataSource.next());
            if(add_special) {
                if(oneTenthChance()) {
                    new_word.setSpecial(true);
                }
            }
            this.queue.add(new_word);
            return new_word;
        }
        return null;
    }


    public static boolean oneTenthChance() {
        Random rand = new Random();
        return rand.nextInt(10) == 0;
    }

    public Queue<Word> getQueue() {
        return new LinkedList<>(queue);
    }

    public boolean isHalfFull() {
        return queue.size() >= MAX_SIZE / 2;
    }

    public boolean isFull() {
        return queue.size() == MAX_SIZE;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    /** Overwrite toStrig method
     * @return String
     */
    public String toString() {
        String res = "";
        for (Word word : this.queue) {
            res += word.getContent() + " ";
        }
        return res;
    }

} 