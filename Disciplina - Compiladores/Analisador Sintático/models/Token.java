package models;

public class Token {
  private String type;
  private String lexemme;
  private int line;

  public Token(String type, String lexemme, int line) {
    super();
    this.type = type;
    this.lexemme = lexemme;
    this.line = line;
  }

  public int getLine() {
    return line;
  }

  public String getLexemme() {
    return lexemme;
  }

  public String getToken() {
    return type;
  }
}
