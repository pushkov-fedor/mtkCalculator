import java.io.IOException;

public class Calculator {
    private Lexer lexer;
    private Lexeme current;

    public Calculator(Lexer lexer) throws IOException, LexerException {
        this.lexer = lexer;
        current = lexer.getLexeme();
    }

    int calculate() throws ParseException, IOException, LexerException {
        int res = parseExpression();
        if(current.type!=LexemeType.EOF) throw new ParseException();
        return res;
    }

    int parseExpression() throws IOException, LexerException, ParseException {
        int temp = parseTerm();
        while(current.type == LexemeType.PLUS || current.type == LexemeType.MINUS){
            if(current.type == LexemeType.PLUS){
                current = lexer.getLexeme();
                temp += parseTerm();
            } else {
                current = lexer.getLexeme();
                temp -= parseTerm();
            }
        }
        return temp;
    }

    int parseTerm() throws IOException, LexerException, ParseException {
        int temp = parseFactor();
        while(current.type == LexemeType.MULT || current.type == LexemeType.DIV){
            if(current.type == LexemeType.MULT){
                current = lexer.getLexeme();
                temp *= parseFactor();
            } else {
                current = lexer.getLexeme();
                temp /= parseFactor();
            }
        }
        return temp;
    }

    int parseFactor() throws IOException, LexerException, ParseException {
        int temp = parsePower();
        if(current.type==LexemeType.POWER){
            current = lexer.getLexeme();
            temp = (int)Math.pow(temp, parsePower());
        }
        return temp;
    }

    int parsePower() throws IOException, LexerException, ParseException {
//        current = lexer.getLexeme();
        int temp = 1;
        if(current.type == LexemeType.MINUS) {
            temp *= -1;
            current = lexer.getLexeme();
        }
        temp*=parseAtom();
        return temp;
    }

    int parseAtom() throws IOException, LexerException, ParseException {
        if(current.type == LexemeType.NUM){
            int temp = Integer.parseInt(current.text);
            current = lexer.getLexeme();
            return temp;
        }
        if(current.type == LexemeType.OPEN){
            current = lexer.getLexeme();
            int temp = parseExpression();
            if(current.type != LexemeType.CLOSE) {
                throw new ParseException();
            }
            current = lexer.getLexeme();
            return temp;
        }
        throw new ParseException();
    }
}
