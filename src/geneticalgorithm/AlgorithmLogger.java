package geneticalgorithm;

public class AlgorithmLogger {

  public static boolean BEST_RESULT_LOGGING_ENABLED = false;
  public static boolean LOGGING_ENABLED = false;
  public static boolean MUTATION_LOGGING_ENABLED = false;

  public static void log(String message) {
    if (LOGGING_ENABLED) System.out.println(message);
  }

  public static void logMutation() {
    if (MUTATION_LOGGING_ENABLED) System.out.println("Mutation happened!");
  }

  public static void logBestResult(String message) {

    if (BEST_RESULT_LOGGING_ENABLED) {
      System.out.println("Best result found!");
      System.out.println(message);
    }
  }
}
