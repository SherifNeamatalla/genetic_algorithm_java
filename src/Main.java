import geneticalgorithm.AlgorithmLogger;
import geneticalgorithm.MainAlgorithm;
import geneticalgorithm.PopulationCreator;
import geneticalgorithm.model.Chromosome;
import salesmanproblem.SalesmanChromosomePrinter;
import salesmanproblem.SalesmanMutationManager;
import salesmanproblem.SalesmanScoreEvaluator;
import salesmanproblem.io.FileParser;
import salesmanproblem.model.SalesmanGene;

import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {

    AlgorithmLogger.MUTATION_LOGGING_ENABLED = false;
    AlgorithmLogger.LOGGING_ENABLED = false;
    AlgorithmLogger.BEST_RESULT_LOGGING_ENABLED = true;

    var logger = new SalesmanChromosomePrinter();

    List<SalesmanGene> possibleGenes = FileParser.parseFile("resources/salesman_test2");
    MainAlgorithm<SalesmanGene> mainAlgorithm =
        new MainAlgorithm<>(
            new PopulationCreator<>(),
            new SalesmanMutationManager(),
            new SalesmanScoreEvaluator(),
            logger,
            possibleGenes);

    Chromosome<SalesmanGene> result = mainAlgorithm.getSolution(100, 100, 0.1, 0.7, 0.8);

    AlgorithmLogger.logBestResult(logger.getPrintedChromosome(result));
  }
}
