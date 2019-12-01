package geneticalgorithm.salesmanproblem.io;

import geneticalgorithm.salesmanproblem.model.SalesmanGene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

  public static List<SalesmanGene> parseFile(String filePath) throws IOException {
    File file = new File(filePath);

    BufferedReader br = new BufferedReader(new FileReader(file));

    List<SalesmanGene> result = new ArrayList<>();
    String st;
    while ((st = br.readLine()) != null) {
      List<String> tokens = List.of(st.split("\\s+"));
      String id = tokens.get(0);
      double x = Double.parseDouble(tokens.get(1));
      double y = Double.parseDouble(tokens.get(2));
      result.add(new SalesmanGene(id, x, y));
    }
    return result;
  }
}
