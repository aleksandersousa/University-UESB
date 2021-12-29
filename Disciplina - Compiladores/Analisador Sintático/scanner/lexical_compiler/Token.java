package scanner.lexical_compiler;

//FORMATO DO TOKEN <TOKEN, LEXEMAS> 
public class Token {
  public static final String TK_ID = "TK_IDENTIFICADOR";
  public static final String TK_INT = "TK_INTEIRO";
  public static final String TK_REAL = "TK_REAL";
  public static final String TK_OP_LOG = "TK_OP_LOGICO";
  public static final String TK_OP_ARIT = "TK_OP_ARITMETICO";
  public static final String TK_OP_REL = "TK_OP_RELACIONAL";
  public static final String TK_OP_STR = "TK_OP_STRING";
  public static final String TK_OP_CHAR = "TK_OP_CHAR";
  public static final String TK_SIMB_ESP = "TK_SIMBOLO_ESPECIAL";
  public static final String TK_END = "TK_FIM";
  public static final String TK_ARRAY = "TK_ARRAY";
  public static final String TK_AT = "TK_ATRIBUICAO";
  public static final String TK_KEYWORD = "TK_PALAVRA_RESERVADA";
  public static final String TK_ERROR = "TK_ERROR";

  private String type;
  private String lexemme;
  private int line;

  public Token(String type, String lexemme, int line) {
    super();
    this.type = type;
    this.lexemme = lexemme;
    this.line = line;
  }

  public Token() {
    super();
  }

  @Override
  public String toString() {
    return "<line=" + line + ", type=" + type + ", lexemme= " + lexemme + ">";
  }
}
