package utils;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
  public static final String delimiter = ",";

  public static ArrayList<String[]> read(String csvFile) {
    ArrayList<String[]> table = new ArrayList<>();
    File file = new File(csvFile);

    try {
      String line = " ";
      BufferedReader br = new BufferedReader(new FileReader(file));
      final String DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
      while ((line = br.readLine()) != null) {
        String[] tokens = line.split(DELIMITER, -1);
        table.add(tokens);
      }
      br.close();
      return table;
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return table;
    }
  }
}
