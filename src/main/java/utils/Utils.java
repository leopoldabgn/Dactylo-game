package utils;

public final class Utils {
  public static void log(String content) {
    System.out.print(String.format("\u001b[34m[LOG]:\u001b[0m %s\n",content));
  }
}
