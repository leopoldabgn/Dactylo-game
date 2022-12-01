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

public class WordQueue{
    private Queue<Word> queue = new LinkedList<>();
    private Iterator<String> dataSource;
    private int MAX_SIZE = 15;

    public WordQueue(String dataPath) {
        this.dataSource = (Iterator<String>) createIterable(dataPath);
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
            fileContent.stream().forEach(x -> Arrays.asList(x.split(" ")).forEach(y -> temp.add(y)));
            return temp.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    
    /** 
     * Add the next word in the queue if not full.
     * @return boolean
     */
    public boolean addNext() {
        if(this.queue.size() < this.MAX_SIZE) {
            return this.queue.add(new Word(this.dataSource.next()));
        }
        return false;
    }

    
    /** 
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
        String pathToFile = "Dactylo-game/src/main/resources/sample.txt";
        WordQueue wq = new WordQueue(pathToFile);
        for (int i = 0; i < 16; i++) {
            wq.addNext();
        }
        System.out.println(wq);
    }

} 