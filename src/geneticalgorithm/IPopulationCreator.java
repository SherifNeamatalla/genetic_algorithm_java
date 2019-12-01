package geneticalgorithm;

import geneticalgorithm.interfaces.IScoreEvaluator;
import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

import java.util.*;

public interface IPopulationCreator<T extends Gene> {

  List<Chromosome<T>> newGeneration(List<T> possibleGenes, int populationCount);

  default List<Chromosome<T>> newGeneration(
      List<Chromosome<T>> population, double crossoverRate, IScoreEvaluator<T> scoreEvaluator) {

    Random random = new Random(System.currentTimeMillis());

    List<Chromosome<T>> newPopulation = new ArrayList<>();
    population.sort(Comparator.comparing(scoreEvaluator::evaluateChromosome));
    newPopulation.add(population.get(0));
    newPopulation.add(population.get(1));
    Collections.shuffle(population);

    for (int i = 0; i < population.size() - 1; i++) {
      var parent1 = population.get(i);
      var parent2 = population.get((i + 1));

      double randomValue = random.nextDouble();

      if (randomValue >= crossoverRate) {
        var clone1 = new Chromosome<>(new ArrayList<>(parent1.getGenes()));
        var clone2 = new Chromosome<>(new ArrayList<>(parent2.getGenes()));

        newPopulation.add(clone1);
        newPopulation.add(clone2);

      } else {

        var child1 = newOffspring(parent1, parent2);
        var child2 = newOffspring(parent2, parent1);

        newPopulation.add(child1);
        newPopulation.add(child2);
      }
    }

    return newPopulation;
  }

  Chromosome<T> newOffspring(Chromosome<T> parent1, Chromosome<T> parent2);

  default List<Chromosome<T>> getCrossoverPool(
      List<Chromosome<T>> population,
      IScoreEvaluator<T> scoreEvaluator,
      double topSurvivorsPercentage) {
    population.sort(Comparator.comparing(scoreEvaluator::evaluateChromosome));

    var topPopulation = population.subList(0, (population.size() / 2));

    Collections.shuffle(topPopulation);

    var topSurvivors =
        topPopulation.subList(0, (int) (topPopulation.size() * topSurvivorsPercentage));

    var bottomPopulation = population.subList((population.size() / 2), population.size());

    Collections.shuffle(bottomPopulation);

    var bottomSurvivors =
        bottomPopulation.subList(
            0, (int) Math.ceil(bottomPopulation.size() * (1 - topSurvivorsPercentage)));

    topSurvivors.addAll(bottomSurvivors);

    return topSurvivors;
  }
}
