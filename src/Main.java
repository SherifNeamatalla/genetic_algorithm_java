import geneticalgorithm.mainalgorithm.AlgorithmLogger;
import geneticalgorithm.mainalgorithm.MainAlgorithm;
import geneticalgorithm.mainalgorithm.PopulationCreator;
import geneticalgorithm.mainalgorithm.model.Chromosome;
import geneticalgorithm.salesmanproblem.SalesmanChromosomePrinter;
import geneticalgorithm.salesmanproblem.SalesmanMutationManager;
import geneticalgorithm.salesmanproblem.SalesmanScoreEvaluator;
import geneticalgorithm.salesmanproblem.io.FileParser;
import geneticalgorithm.salesmanproblem.model.SalesmanGene;

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
