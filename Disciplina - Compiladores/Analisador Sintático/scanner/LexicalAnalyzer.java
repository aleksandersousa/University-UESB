package scanner;

import java.io.FileWriter;
import java.io.IOException;

import scanner.lexical_compiler.Scanner;
import scanner.lexical_compiler.Token;

//ANALIZADOR LEXICO
public class LexicalAnalyzer {
  private final int OUTPUT_NUMBER = 2; // pocição vetor de criação de nome (numero)
  private Scanner sc; //Scanner de ANALISE 
  private String path; //Caminho do arquivo 
  private String outputPath; //Caminho de saida do arquivo 
  private Token token = null; //tipo token <cadeia, token>

  public LexicalAnalyzer(String path) {
    this.path = path;
    this.sc = new Scanner(path);
  }

  public void run() {
    //LOGICA CRIAÇÂO DE ARQUIVO
      try   {
        String[] outputName = path.split("t"); //input1.txt = 1
        //criação do txt com a analise no caminho -> sources/outputs/output[NUMERO_OUTPUT].txt
        outputPath = "scanner/sources/outputs/output" + outputName[OUTPUT_NUMBER] + "txt";
        FileWriter file = new FileWriter((outputPath)); 
      
        do {
          token = sc.nextToken(); //ANALISE LEXICA
          if (token != null) {
            file.write(token.toString() + "\n"); //ESCREVENDO ANALISE NO ARQUIVO DE SAIDA a cada cadeia: <cadeia, token>
          }
        } while (token != null);
        
        file.close();
        //Arquivo criado em:
        System.out.println("Arquivo criado/atualizado com sucesso no caminho relativo: sources/outputs/output" + outputName[OUTPUT_NUMBER] + "txt");
        
      } catch (IOException e) {
        System.out.println("Arquivo invalido!!!");
      }
  }

  public String getOutputPath() {
    return this.outputPath;
  }
}
