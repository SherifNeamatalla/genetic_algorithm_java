package geneticalgorithm.salesmanproblem;

import geneticalgorithm.mainalgorithm.interfaces.IChromosomePrinter;
import geneticalgorithm.mainalgorithm.model.Chromosome;
import geneticalgorithm.salesmanproblem.model.SalesmanGene;

public class SalesmanChromosomePrinter implements IChromosomePrinter<SalesmanGene> {

  public String getPrintedChromosome(Chromosome<SalesmanGene> chromosome) {

    double totalDistance = 0;
    StringBuilder result = new StringBuilder();

    SalesmanGene previousGene = null;
    for (SalesmanGene gene : chromosome.getGenes()) {
      if (previousGene != null) totalDistance += UtilHelper.getDistance(previousGene, gene);

      previousGene = gene;
      result.append(gene.getId()).append(" ");
    }
    return result.toString() + "\n Total distance : " + totalDistance;
  }
}
