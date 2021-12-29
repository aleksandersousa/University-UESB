package syntactic_compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grammar {
  private ArrayList<String> grammar = new ArrayList<>();
  private String filename = "./sources/gramatica.txt";

  public Grammar() {
    File file = new File(filename);
    try {
      String line = " ";
      BufferedReader br = new BufferedReader(new FileReader(file));
      while ((line = br.readLine()) != null) {
        grammar.add(line);
      }
      br.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public String getGrammar(int index) {
    return grammar.get(index);
  }
}
