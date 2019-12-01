package geneticalgorithm;

import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationManager<T extends Gene> {

  Chromosome<T> mutateChromosome(Chromosome<T> chromosome, double mutationRate) {

    Random random = new Random(System.currentTimeMillis());

    double randomValue = random.nextDouble();

    if (randomValue <= mutationRate) {
      var newChromosome = new Chromosome<>(new ArrayList<>(chromosome.getGenes()));
      int x = random.nextInt(newChromosome.getGenes().size());
      int y = random.nextInt(newChromosome.getGenes().size());

      T temp = newChromosome.getGenes().get(x);
      newChromosome.getGenes().set(x, newChromosome.getGenes().get(y));
      newChromosome.getGenes().set(y, temp);
      AlgorithmLogger.logMutation();
      return newChromosome;
    }
    return chromosome;
  }

  List<Chromosome<T>> mutateGeneration(List<Chromosome<T>> chromosomes, double mutationRate) {

    List<Chromosome<T>> result = new ArrayList<>();
    for (Chromosome<T> currentChromosome : chromosomes) {
      result.add(mutateChromosome(currentChromosome, mutationRate));
    }
    return result;
  }
}
