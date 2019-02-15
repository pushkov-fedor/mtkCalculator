public class Lexeme {
    LexemeType type;
    String text;

    public Lexeme(LexemeType type, String text){
        this.type = type;
        this.text = text;
    }

    public void appendChar(char i) {
        text+=String.valueOf(i);
    }

    public String toString(){
        return text;
    }
}
