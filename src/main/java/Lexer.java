import java.io.IOException;
import java.io.Reader;

public class Lexer {

    private Reader reader;
    private int current;

    public Lexer(Reader reader){
        this.reader = reader;
        try {
            current = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Lexeme getLexeme() throws IOException, LexerException {
        Lexeme lexem;
        switch (current){
            case '+':
                lexem = new Lexeme(LexemeType.PLUS, "+");
                current = reader.read();
                return lexem;
            case '-':
                lexem = new Lexeme(LexemeType.MINUS, "-");
                current = reader.read();
                return lexem;
            case '*':
                lexem = new Lexeme(LexemeType.MULT, "*");
                current = reader.read();
                return lexem;
            case '/':
                lexem = new Lexeme(LexemeType.DIV, "/");
                current = reader.read();
                return lexem;
            case '^':
                lexem = new Lexeme(LexemeType.POWER, "^");
                current = reader.read();
                return lexem;
            case '(':
                lexem = new Lexeme(LexemeType.OPEN, "(");
                current = reader.read();
                return lexem;
            case ')':
                lexem = new Lexeme(LexemeType.CLOSE, ")");
                current = reader.read();
                return lexem;
            case -1:
                lexem = new Lexeme(LexemeType.EOF, "EOF");
                return lexem;
            default:
                if(Character.isDigit(current)){
                    lexem = new Lexeme(LexemeType.NUM, String.valueOf((char)current));
                    while( Character.isDigit(current = reader.read()) ){
                        lexem.appendChar((char)current);
                    }
                    return lexem;
                } else {
                    throw new LexerException();
                }
        }
    }
}
