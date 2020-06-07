package lexer;

public class SyntaxErrorException extends Throwable {
    SyntaxErrorException(char ch, int position) {
        super(String.format("Unexpected \"%s\" at position %s", ch, position));
    }
}
