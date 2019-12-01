package salesmanproblem;

import geneticalgorithm.IPopulationCreator;
import geneticalgorithm.model.Chromosome;
import salesmanproblem.model.SalesmanGene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SalesmanPopulationCreator implements IPopulationCreator<SalesmanGene> {

  public List<Chromosome<SalesmanGene>> newGeneration(
      List<SalesmanGene> possibleGenes, int populationCount) {
    List<Chromosome<SalesmanGene>> newPopulation = new ArrayList<>();

    for (int i = 0; i < populationCount; i++) {

      List<SalesmanGene> newGenome = new ArrayList<>(possibleGenes);

      Collections.shuffle(newGenome);

      newPopulation.add(new Chromosome<>(newGenome));
    }

    return newPopulation;
  }

  public Chromosome<SalesmanGene> newOffspring(
      Chromosome<SalesmanGene> parent1, Chromosome<SalesmanGene> parent2) {
    Random random = new Random(System.currentTimeMillis());

    int index1 = random.nextInt(parent1.getGenes().size());
    int index2 = random.nextInt(parent1.getGenes().size());

    int max = Math.max(index1, index2);
    int min = Math.min(index1, index2);

    var childP1 = new ArrayList<>(parent1.getGenes().subList(min, max));
    var childP2 =
        parent2.getGenes().stream()
            .filter(g -> !childP1.contains(g))
            .collect(Collectors.toCollection(ArrayList::new));

    childP1.addAll(childP2);

    return new Chromosome<>(childP1);
  }
}
