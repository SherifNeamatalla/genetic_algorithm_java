package geneticalgorithm;

import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

import java.util.List;

public class SolutionValidator<T extends Gene> {

  public boolean isValid(Chromosome<T> chromosome, List<T> possibleGenes) {

    if (chromosome.getGenes().size() != possibleGenes.size()) return false;
    for (T gene : possibleGenes) {
      if (chromosome.getGenes().indexOf(gene) == -1) return false;
    }
    return true;
  }
}
