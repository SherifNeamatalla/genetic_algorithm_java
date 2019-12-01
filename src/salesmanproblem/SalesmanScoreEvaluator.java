package salesmanproblem;

import geneticalgorithm.interfaces.IScoreEvaluator;
import geneticalgorithm.model.Chromosome;
import salesmanproblem.model.SalesmanGene;

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
