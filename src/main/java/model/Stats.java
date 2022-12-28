package model;

public class Stats {
  /*
  
  - Vitesse (MPM) : le nombre de caractères utiles, divisé par le temps en minute, divisé encore par 5 (ici, on considère par convention qu’un mot fait en moyenne 5 caractères).
  - Précision (%) : le nombre de caractères utiles divisé par le nombre d’appuis de touche (×100).
  - Régularité : c’est l’écart-type de la durée entre 2 caractères utiles consécutifs. 

  */
  private int words_per_minute;
  private int precision;
  private int frequency;

  private Stats(int wpm, int precision, int frequency) {
    this.words_per_minute = wpm;
    this.precision = precision;
    this.frequency = frequency;
  }

  public static Stats createStats(Player player) {
    return new Stats(computeWPM(player), computePrecision(player), computeFrequency(player));
  }


  private static int computeFrequency(Player player) {
    return 150;
  }


  private static int computePrecision(Player player) {
    return 15;
  }


  private static int computeWPM(Player player) {
    return 7;
  }

  public int getWords_per_minute() {
    return words_per_minute;
  }

  public int getPrecision() {
    return precision;
  }

  public int getFrequency() {
    return frequency;
  }

}
