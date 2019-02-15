import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class TestEverything {

    @Test
    public void getLexeme() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader(new String("+-*/^()60")));
        assertEquals(lexer.getLexeme().toString(), "+");
        assertEquals(lexer.getLexeme().toString(), "-");
        assertEquals(lexer.getLexeme().toString(), "*");
        assertEquals(lexer.getLexeme().toString(), "/");
        assertEquals(lexer.getLexeme().toString(), "^");
        assertEquals(lexer.getLexeme().toString(), "(");
        assertEquals(lexer.getLexeme().toString(), ")");
        assertEquals(lexer.getLexeme().toString(), "60");
        assertEquals(lexer.getLexeme().toString(), "EOF");
    }

    @Test
    public void calculate() throws IOException, LexerException, ParseException {
        Calculator calculator = new Calculator(new Lexer(new StringReader(new String("-(((2+13)*3)^2)/5"))));
        assertEquals(-405, calculator.calculate());
    }

    @Test(expected = ParseException.class)
    public void calculateException() throws IOException, LexerException, ParseException {
        Calculator calculator = new Calculator(new Lexer(new StringReader(new String("-15)"))));
        assertEquals(-401, calculator.calculate());
    }
}