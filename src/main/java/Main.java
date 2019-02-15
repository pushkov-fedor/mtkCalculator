import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) throws IOException, LexerException, ParseException {
        Lexer lexer = new Lexer(new StringReader(new String("-(((2+13)*3)^2)/5")));
        Calculator calculator = new Calculator(lexer);
        System.out.println(calculator.calculate());
    }

}
