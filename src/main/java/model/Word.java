package model;

public class Word {

    private String content, pushContent;
    private boolean validate;

    public Word(String content) {
        this.content = content;
        this.pushContent = "";
    }

    /** Number of good chars in the push word in comparison with the real word.
     *  This function can be useful to generate stats for a player in a game
     * @return int
     */
    public int nbGoodChars() {
        return 0;
    }

    /** Number of errors in the push word in comparison with the real word.
     *  This function can be useful to generate stats for a player in a game
     * @return int
     */
    public int nbErrors() {
        return 0;
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

    public boolean isValidate() {
        return validate;
    }

    public void validate() {
        this.validate = true;
    }

}