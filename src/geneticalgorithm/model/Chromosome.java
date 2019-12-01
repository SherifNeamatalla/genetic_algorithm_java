package geneticalgorithm.model;

import java.util.List;

public class Chromosome<T extends Gene> {

  private List<T> genes;

  public Chromosome(List<T> genes) {
    this.genes = genes;
  }

  public List<T> getGenes() {
    return genes;
  }

  public void setGenes(List<T> genes) {
    this.genes = genes;
  }
}
