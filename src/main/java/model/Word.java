package model;

public class Word {

    private String content;

    public Word(String content) {
        this.content = content;
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

}