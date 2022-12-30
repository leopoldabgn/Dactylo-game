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

public class WordQueue {

    private Queue<Word> queue = new LinkedList<>();
    private Iterator<String> dataSource;
    private int MAX_SIZE = 15;
    
    
    public WordQueue(String dataPath) {
        this.dataSource = (Iterator<String>) createIterable(dataPath);
        startQueue();
    }
    
    /**
    *  A private method that initializes the queue by adding a set number of items to it. 
    * @param None.
    * @return void.
    */
    private void startQueue() {
        for(int i=0; i<MAX_SIZE; i++) {
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
            fileContent.stream().forEach(x -> Arrays.asList(x.split(" ")).forEach(y -> temp.add(deleteChars(y, ";.,"))));
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
            this.queue.add(new_word);
            return new_word;
        }
        return null;
    }

    // Copie necessaire ici ?
    public Queue<Word> getQueue() {
        return new LinkedList<>(queue);
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
    
    public static void main(String[] args) {
        String pathToFile = "src/main/resources/sample.txt";
        WordQueue wq = new WordQueue(pathToFile);
        // System.out.println("\nQueue: "+wq+"\n");
        wq.poll();
        // System.out.println("Queue: "+wq+"\n");
    }

} 