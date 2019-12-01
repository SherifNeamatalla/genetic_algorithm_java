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

  public static void logGenerationResults(
      int generationNumber, double bestScore, double averageScore, double worstScore) {

    if (BEST_RESULT_LOGGING_ENABLED) {
      System.out.println("Generation " + generationNumber + " : ");
      System.out.println(
          "Best : " + bestScore + " Avg: " + averageScore + " Worst : " + worstScore);
    }
  }
}
