package geneticalgorithm.interfaces;

import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

import java.util.ArrayList;
import java.util.List;

public interface IMutationManager<T extends Gene> {

  Chromosome<T> mutateChromosome(Chromosome<T> chromosome, double mutationRate);

  default List<Chromosome<T>> mutateGeneration(
      List<Chromosome<T>> chromosomes, double mutationRate) {

    List<Chromosome<T>> result = new ArrayList<>();
    for (Chromosome<T> currentChromosome : chromosomes) {
      result.add(mutateChromosome(currentChromosome, mutationRate));
    }
    return result;
  }
}