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
  private long frequency;

  private Stats(double wpm, double precision, long frequency) {
    this.words_per_minute = wpm;
    this.precision = precision;
    this.frequency = frequency;
  }

  public static Stats createStats(Game game, Player player) {
    return new Stats(computeWPM(game, player), computePrecision(game, player), computeFrequency(game, player));
  }

  // En millisecondes
  private static long computeFrequency(Game game, Player player) {
    ArrayList<Long> durations = getDurationsBetween2Chars(player.getGoodChars());
    System.out.println(durations);
    if(durations.isEmpty())
      return 0;
    // On calcul l'écart type de notre série statistique (les durées entre deux chars utiles)
    double average = average(durations);
    double result = 0;
    for(Long d : durations) {
      double val = Math.abs(d - average);
      val *= val;
      result += val;
    }
    result /= durations.size();
    result = Math.sqrt(result);
    return (long)Math.round(result);
  }

  private static double computePrecision(Game game, Player player) {
    if(player.nbKeysPressed() == 0)
      return 0;
    Double precision = ((double)player.nbGoodChars() / player.nbKeysPressed()) * 100;
    return BigDecimal.valueOf(precision).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  private static double computeWPM(Game game, Player player) {
    double time = game.getInfos().getTime(); // temps en milliseconde
    time = (time / 1_000) / 60; // On convertit en secondes, puis en minutes
    Double wpm = ((double)player.nbGoodChars() / time) / 5;
    return BigDecimal.valueOf(wpm).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  private static ArrayList<Long> getDurationsBetween2Chars(List<CharStats> chars) {
    ArrayList<Long> durations = new ArrayList<>();
    for(int i=0;i<chars.size()-1;i++) {
      CharStats c1 = chars.get(i);
      CharStats c2 = chars.get(i+1);
      durations.add(c2.getTime() - c1.getTime());
    }
    return durations;
  }

  private static double average(List<Long> numbers) {
    OptionalDouble average = numbers.stream().mapToDouble(x -> x).average();
    return average.isPresent() ? average.getAsDouble() : 0; 
  }

  public double getWords_per_minute() {
    return words_per_minute;
  }

  public double getPrecision() {
    return precision;
  }

  public long getFrequency() {
    return frequency;
  }

}
