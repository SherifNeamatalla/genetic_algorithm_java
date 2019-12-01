package geneticalgorithm.salesmanproblem;

import geneticalgorithm.mainalgorithm.interfaces.IScoreEvaluator;
import geneticalgorithm.mainalgorithm.model.Chromosome;
import geneticalgorithm.salesmanproblem.model.SalesmanGene;

public class SalesmanScoreEvaluator implements IScoreEvaluator<SalesmanGene> {
  @Override
  public double evaluateChromosome(Chromosome<SalesmanGene> chromosome) {
    double totalDistance = 0;

    SalesmanGene previousGene = null;
    for (SalesmanGene gene : chromosome.getGenes()) {

      if (previousGene != null) {
        totalDistance += UtilHelper.getDistance(previousGene, gene);
      }

      previousGene = gene;
    }
    return totalDistance;
  }
}
