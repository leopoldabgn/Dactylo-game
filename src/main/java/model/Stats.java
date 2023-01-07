package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import model.Word.CharStats;

public final class Stats {
  /*
  
  - Vitesse (MPM) : le nombre de caractères utiles, divisé par le temps en minute, divisé encore par 5 (ici, on considère par convention qu’un mot fait en moyenne 5 caractères).
  - Précision (%) : le nombre de caractères utiles divisé par le nombre d’appuis de touche (×100).
  - Régularité : c’est l’écart-type de la durée entre 2 caractères utiles consécutifs. 

  */
  private double words_per_minute;
  private double precision;
  private double frequency;
  /** 
   * Private constructor for Stats class.
   *
   * @param wpm Words per minute statistic
   * @param precision Precision statistic
   * @param frequency Frequency statistic
   */
  private Stats(double wpm, double precision, double frequency) {
    this.words_per_minute = wpm;
    this.precision = precision;
    this.frequency = frequency;
  }

  
/**
 * Static factory method for creating an instance of Stats.
 *
 * @param game The game the player participated in
 * @param player The player whose statistics are being computed
 * @return Stats object containing the player's statistics for the game
 */
  public static Stats createStats(Game game, Player player) {
    return new Stats(computeWPM(game, player), computePrecision(game, player), computeFrequency(game, player));
  }

  
  /** 
   * @param game
   * @param player
   * @return double
   */
  // En secondes
  private static double computeFrequency(Game game, Player player) {
    ArrayList<Double> durations = getDurationsBetween2Chars(player.getGoodChars());
    // System.out.println(durations);
    if(durations.isEmpty())
      return 0;
    // On calcul l'écart type de notre série statistique (les durées entre deux chars utiles)
    double average = average(durations);
    double result = 0;
    for(Double d : durations) {
      double val = Math.abs(d - average);
      val *= val;
      result += val;
    }
    result /= durations.size();
    result = Math.sqrt(result);
    return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  
  /** 
   * @param game
   * @param player
   * @return double
   */
  private static double computePrecision(Game game, Player player) {
    if(player.nbKeysPressed() == 0)
      return 0;
    Double precision = ((double)player.nbGoodChars() / player.nbKeysPressed()) * 100;
    return BigDecimal.valueOf(precision).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  
  /** 
   * @param game
   * @param player
   * @return double
   */
  private static double computeWPM(Game game, Player player) {
    double time = game.getInfos().getTime(); // temps en milliseconde
    time = (time / 1_000) / 60; // On convertit en secondes, puis en minutes
    Double wpm = ((double)player.nbGoodChars() / time) / 5;
    return BigDecimal.valueOf(wpm).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  
  /** 
   * @param chars
   * @return ArrayList<Double>
   */
  private static ArrayList<Double> getDurationsBetween2Chars(List<CharStats> chars) {
    ArrayList<Double> durations = new ArrayList<>();
    for(int i=0;i<chars.size()-1;i++) {
      CharStats c1 = chars.get(i);
      CharStats c2 = chars.get(i+1);
      // On converti le temps en seconde
      durations.add((double)(c2.getTime() - c1.getTime()) / 1000);
    }
    return durations;
  }

  
  /** 
   * @param numbers
   * @return double
   */
  private static double average(List<Double> numbers) {
    OptionalDouble average = numbers.stream().mapToDouble(x -> x).average();
    return average.isPresent() ? average.getAsDouble() : 0; 
  }

  
  /** 
   * @return double
   */
  public double getWords_per_minute() {
    return words_per_minute;
  }

  
  /** 
   * @return double
   */
  public double getPrecision() {
    return precision;
  }

  
  /** 
   * @return double
   */
  public double getFrequency() {
    return frequency;
  }

}
