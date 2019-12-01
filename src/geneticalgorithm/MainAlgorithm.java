package geneticalgorithm;

import geneticalgorithm.interfaces.IScoreEvaluator;
import geneticalgorithm.interfaces.IChromosomePrinter;
import geneticalgorithm.interfaces.IMutationManager;
import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

import java.util.Comparator;
import java.util.List;

public class MainAlgorithm<T extends Gene> {

  private PopulationCreator<T> populationCreator;
  private IMutationManager<T> mutationManager;
  private IScoreEvaluator<T> scoreEvaluator;
  private IChromosomePrinter<T> chromosomePrinter;
  private List<T> possibleGenes;

  public MainAlgorithm(
      PopulationCreator<T> populationCreator,
      IMutationManager<T> mutationManager,
      IScoreEvaluator<T> scoreEvaluator,
      IChromosomePrinter<T> chromosomePrinter,
      List<T> possibleGenes) {
    this.populationCreator = populationCreator;
    this.mutationManager = mutationManager;
    this.scoreEvaluator = scoreEvaluator;
    this.chromosomePrinter = chromosomePrinter;
    this.possibleGenes = possibleGenes;
  }

  public Chromosome<T> getSolution(
      int generations,
      int populationCount,
      double mutationRate,
      double crossoverRate,
      double topSurvivorsPercentage) {

    List<Chromosome<T>> population =
        this.populationCreator.newGeneration(this.possibleGenes, populationCount);
    AlgorithmLogger.log("Generated first population.");

    Chromosome<T> bestChromosome = null;

    for (int i = 0; i < generations; i++) {

      List<Chromosome<T>> survivingPopulation =
          populationCreator.getCrossoverPool(population, scoreEvaluator, topSurvivorsPercentage);
      AlgorithmLogger.log("Generated surviving population for generation : " + i);

      List<Chromosome<T>> offSpring =
          populationCreator.newGeneration(survivingPopulation, crossoverRate, scoreEvaluator);

      AlgorithmLogger.log("Generated offSpring population for generation : " + i);

      population = mutationManager.mutateGeneration(offSpring, mutationRate);

      AlgorithmLogger.log("Generated mutated population for generation : " + i);

      AlgorithmLogger.log("New generation size : " + population.size());

      population.sort(Comparator.comparing(scoreEvaluator::evaluateChromosome));

      // To always keep the best creature.
      if (bestChromosome == null
          || scoreEvaluator.evaluateChromosome(population.get(0))
              < scoreEvaluator.evaluateChromosome(bestChromosome))
        bestChromosome = population.get(0);
      else population.set(population.size() - 1, bestChromosome);

      AlgorithmLogger.logBestResult(chromosomePrinter.getPrintedChromosome(bestChromosome));
    }

    return bestChromosome;
  }
}
