package scanner.utils;

//CLASSE UTILITARIA VERIFICAÇÂO DE CARACTER
public class Util {
  public static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  public static boolean isChar(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  public static boolean isOperator(char c) {
    return c == '>' || c == '<';
  }

  public static boolean isAritmeticOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
  }

  public static boolean isKeyword(String term) {
    return term.matches(
      "(^(?i)(ABSOLUTE|ARRAY|BEGIN|CASE|CONST|DIV|DO|DOWTO|ELSE|END|EXTERNAL|FILE|FOR|FORWARD|FUNC|FUNCTION|GOTO|IF|IMPLEMENTATION|READ|WRITE|WRITELN|INTERFACE|INTERRUPT|LABEL|MAIN|NIL|NIT|OF|PACKED|PROC|PROGRAM|REAL|RECORD|REPEAT|SET|SHL|SHR|STRING|THEN|TO|TYPE|UNIT|UNTIL|USES|VAR|WHILE|WITH|XOR|ANDa){0,}$)")
        ? true
        : false;
  }

  public static boolean isLogicOperator(String term) {
    return term.matches("(^(?i)(AND|OR|NOT|MOD){0,}$)");
  }

  public static boolean isCharOperator(char c) {
    return c == '\'';
  }

  public static boolean isStringOperator(char c) {
    return c == '"';
  } 
  
  public static boolean isSpecialSymbol(char c) {
    return c == '=' || c == '(' || c == ')' || c == '[' || c == ']' || c == ',' || c == ';' || c == ':';
  }

  public static boolean isSpace(char c) {
    return c == ' ' || c == '\n' || c =='\t' || c == '\r';
  }

  public static boolean isEnd(char c) {
    return c == '.';
  }
}
