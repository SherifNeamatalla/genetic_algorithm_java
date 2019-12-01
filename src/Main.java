import geneticalgorithm.AlgorithmLogger;
import geneticalgorithm.MainAlgorithm;
import geneticalgorithm.PopulationCreator;
import geneticalgorithm.SolutionValidator;
import geneticalgorithm.model.Chromosome;
import salesmanproblem.SalesmanChromosomePrinter;
import salesmanproblem.SalesmanMutationManager;
import salesmanproblem.SalesmanScoreEvaluator;
import salesmanproblem.io.FileParser;
import salesmanproblem.model.SalesmanGene;

import java.io.IOException;
import java.util.List;

public class Main {

  private static final int GENERATIONS = 100000;
  private static final int POPULATION_COUNT = 10;

  private static final double MUTATION_RATE = 0.1;

  private static final double CROSSOVER_RATE = 0.7;

  private static final double TOP_SURVIVORS_PERCENTAGE = 0.8;

  public static void main(String[] args) throws IOException {

    AlgorithmLogger.MUTATION_LOGGING_ENABLED = false;
    AlgorithmLogger.LOGGING_ENABLED = false;
    AlgorithmLogger.BEST_RESULT_LOGGING_ENABLED = true;

    List<SalesmanGene> possibleGenes = FileParser.parseFile("resources/salesman_test3");
    MainAlgorithm<SalesmanGene> mainAlgorithm =
        new MainAlgorithm<>(
            new PopulationCreator<>(),
            new SalesmanMutationManager(),
            new SalesmanScoreEvaluator(),
            new SalesmanChromosomePrinter(),
            possibleGenes);

    Chromosome<SalesmanGene> result =
        mainAlgorithm.getSolution(
            GENERATIONS, POPULATION_COUNT, MUTATION_RATE, CROSSOVER_RATE, TOP_SURVIVORS_PERCENTAGE);
    System.out.println(new SolutionValidator<SalesmanGene>().isValid(result, possibleGenes));
  }
}
