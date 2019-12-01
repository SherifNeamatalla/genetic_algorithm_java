package geneticalgorithm;

import geneticalgorithm.interfaces.IScoreEvaluator;
import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

import java.util.Comparator;
import java.util.List;

public class MainAlgorithm<T extends Gene> {

  private IPopulationCreator<T> IPopulationCreator;
  private MutationManager<T> mutationManager;
  private IScoreEvaluator<T> scoreEvaluator;
  private List<T> possibleGenes;

  public MainAlgorithm(
      IPopulationCreator<T> IPopulationCreator,
      MutationManager<T> mutationManager,
      IScoreEvaluator<T> scoreEvaluator,
      List<T> possibleGenes) {
    this.IPopulationCreator = IPopulationCreator;
    this.mutationManager = mutationManager;
    this.scoreEvaluator = scoreEvaluator;
    this.possibleGenes = possibleGenes;
  }

  public Chromosome<T> getSolution(
      int generations,
      int populationCount,
      double mutationRate,
      double crossoverRate,
      double topSurvivorsPercentage) {

    List<Chromosome<T>> population =
        this.IPopulationCreator.newGeneration(this.possibleGenes, populationCount);
    AlgorithmLogger.log("Generated first population.");

    Chromosome<T> bestChromosome = null;

    for (int i = 0; i < generations; i++) {

      List<Chromosome<T>> survivingPopulation =
          IPopulationCreator.getCrossoverPool(population, scoreEvaluator, topSurvivorsPercentage);
      AlgorithmLogger.log("Generated surviving population for generation : " + i);

      List<Chromosome<T>> offSpring =
          IPopulationCreator.newGeneration(survivingPopulation, crossoverRate, scoreEvaluator);

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

      double bestScore = scoreEvaluator.evaluateChromosome(bestChromosome);
      double averageScore =
          scoreEvaluator.evaluateChromosome(population.get(population.size() / 2));
      double worstScore = scoreEvaluator.evaluateChromosome(population.get(population.size() - 1));

      AlgorithmLogger.logGenerationResults(i, bestScore, averageScore, worstScore);
    }

    return bestChromosome;
  }
}
