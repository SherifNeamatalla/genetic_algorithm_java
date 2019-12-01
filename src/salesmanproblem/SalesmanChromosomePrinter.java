package salesmanproblem;

import geneticalgorithm.interfaces.IChromosomePrinter;
import geneticalgorithm.model.Chromosome;
import salesmanproblem.model.SalesmanGene;

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
    return String.valueOf(totalDistance);
  }
}
