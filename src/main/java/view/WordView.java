package view;

import javax.swing.JLabel;

import model.Word;

public class WordView extends JLabel {

    public static final String FILL_COLOR  = "#000",
                               EMPTY_COLOR = "#888",
                               ERROR_COLOR = "#cf1f1f",
                               VALID_COLOR = "#1fcf39";

    private Word word;

    public WordView(Word word) {
        super(word.getContent());
        this.word = word;
        // Put the word in black
        setColoredText(word.getContent());
    }

    /** 
     *
     */
    public void setColoredText() {
        setColoredText(word.getPushedContent());
    }

    /** 
     * @param pushText
     */
    public void setColoredText(String pushText) {
        String coloredLabel = getColoredLabel(word.getContent(), pushText);
        setText(coloredLabel);
        revalidate();
        repaint();
    }

    /** 
     * @param word
     * @param pushWord
     * @return String
     */
    public static String getColoredLabel(String word, String pushWord) {
        int len = word.length(),
            pushLen = pushWord.length();
        String res = "";

        for(int i=0;i<len;i++) {
            if(i >= pushLen)
                break;
            char pushChar = pushWord.charAt(i);
            // If the current char is equal to the pushed char, then we put the fill color otherwise we put the error color
            res += getColoredString(""+pushChar, word.charAt(i) == pushChar ? FILL_COLOR : ERROR_COLOR);
        }

        if(pushLen-1 < len)
            res += getColoredString(word.substring(pushLen, len), EMPTY_COLOR);

        return "<html>"+res+"</html>";
    }
    
    /** 
     * @param text
     * @param color
     * @return String
     */
    public static String getColoredString(String text, String color) {
        return "<font color="+color+">"+text+"</font>";
    }

    // SHORTCUT TO WORD METHODS

    public void pushLetter(char letter) {
        word.pushLetter(letter);
    }

    public void removeLetter() {
        word.removeLetter();
    }

    public void pushContent(String pushContent) {
        word.pushContent(pushContent);
    }

}