package geneticalgorithm.salesmanproblem;

import geneticalgorithm.mainalgorithm.AlgorithmLogger;
import geneticalgorithm.mainalgorithm.interfaces.IMutationManager;
import geneticalgorithm.mainalgorithm.model.Chromosome;
import geneticalgorithm.salesmanproblem.model.SalesmanGene;

import java.util.ArrayList;
import java.util.Random;

public class SalesmanMutationManager implements IMutationManager<SalesmanGene> {

  @Override
  public Chromosome<SalesmanGene> mutateChromosome(
      Chromosome<SalesmanGene> chromosome, double mutationRate) {

    Random random = new Random(System.currentTimeMillis());

    double randomValue = random.nextDouble();

    if (randomValue <= mutationRate) {
      var newChromosome = new Chromosome<>(new ArrayList<>(chromosome.getGenes()));
      int x = random.nextInt(newChromosome.getGenes().size());
      int y = random.nextInt(newChromosome.getGenes().size());

      SalesmanGene temp = newChromosome.getGenes().get(x);
      newChromosome.getGenes().set(x, newChromosome.getGenes().get(y));
      newChromosome.getGenes().set(y, temp);
      AlgorithmLogger.logMutation();
      return newChromosome;
    }
    return chromosome;
  }
}
