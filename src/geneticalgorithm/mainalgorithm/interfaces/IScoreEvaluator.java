package geneticalgorithm.mainalgorithm.interfaces;

import geneticalgorithm.mainalgorithm.model.Chromosome;
import geneticalgorithm.mainalgorithm.model.Gene;

public interface IScoreEvaluator<T extends Gene> {

  double evaluateChromosome(Chromosome<T> chromosome);
}
