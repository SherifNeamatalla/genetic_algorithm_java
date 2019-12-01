package geneticalgorithm.interfaces;

import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

public interface IChromosomePrinter<T extends Gene> {

  String getPrintedChromosome(Chromosome<T> chromosome);
}
