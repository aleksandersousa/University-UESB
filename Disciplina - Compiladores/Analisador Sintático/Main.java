import java.util.Scanner;

import scanner.LexicalAnalyzer;
import syntactic_compiler.Parser;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in); //Scanner para leitura/escrita
    boolean error = false; //Variavel auxilar para verificação de erro na entrada
    char again;

    do  {
      System.out.println("Digite o caminho relativo do arquivo txt a ser analizado: ");
      String path = sc.nextLine();

      /*verificação. if tudo ok -> iniciar analise -> nova analise ?, else -> Informar erro ao 
      programa e ao usuario*/
      if (path.endsWith(".txt")) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(path);
        lexicalAnalyzer.run();

        Parser parser = new Parser(lexicalAnalyzer.getOutputPath());
        parser.run();

        System.out.println("Deseja iniciar uma nova analise ? s/n");
        again = sc.nextLine().charAt(0);

        //Aproveitando a variavel de error no novo loop
        error = false; // para o loop default. 
        if (again == 's' || again == 'S') {
          error = true; //Loop continua.
        }
      } else {
        System.out.println("Formato invalido!!!"); //mensagem de erro
        error = true; //houve erro
      }
    } while(error);
    sc.close();
  }
}
