package geneticalgorithm.mainalgorithm.interfaces;

import geneticalgorithm.mainalgorithm.model.Chromosome;
import geneticalgorithm.mainalgorithm.model.Gene;

public interface IChromosomePrinter<T extends Gene> {

  String getPrintedChromosome(Chromosome<T> chromosome);
}
