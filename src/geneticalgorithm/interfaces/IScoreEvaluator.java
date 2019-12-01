package geneticalgorithm.interfaces;

import geneticalgorithm.model.Chromosome;
import geneticalgorithm.model.Gene;

public interface IScoreEvaluator<T extends Gene> {

  double evaluateChromosome(Chromosome<T> chromosome);
}
