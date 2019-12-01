package geneticalgorithm.salesmanproblem;

import geneticalgorithm.salesmanproblem.model.SalesmanGene;

public class UtilHelper {

  static double getDistance(SalesmanGene gene1, SalesmanGene gene2) {

    double lat1 = gene1.getX();
    double lng1 = gene1.getY();
    double lat2 = gene2.getX();
    double lng2 = gene2.getY();

    double firstSum = Math.abs(lat1 - lat2);
    double secondSum = Math.abs(lng1 - lng2);

    return Math.sqrt(Math.pow(firstSum, 2) + Math.pow(secondSum, 2));
  }
}
