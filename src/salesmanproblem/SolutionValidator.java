package salesmanproblem;

import geneticalgorithm.model.Chromosome;
import salesmanproblem.model.SalesmanGene;

import java.util.List;

public class SolutionValidator {

  public static boolean isValid(
      Chromosome<SalesmanGene> chromosome, List<SalesmanGene> possibleGenes) {

    if (chromosome.getGenes().size() != possibleGenes.size()) return false;
    for (SalesmanGene gene : possibleGenes) {
      if (chromosome.getGenes().indexOf(gene) == -1) return false;
    }
    return true;
  }
}
