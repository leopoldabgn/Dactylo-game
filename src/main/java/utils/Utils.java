package utils;

/**
 * This class provides utility functions for logging. 
 */
public final class Utils {
  
  /**
   * Prints a log message in blue with the prefix "[LOG]: ".
   * @param content the content of the log message
   */
  public static void log(String content) {
    System.out.print(String.format("\u001b[34m[LOG]:\u001b[0m %s\n",content));
  }
}
