package my;

import my.lexer.TokenType;

public class SyntaxErrorException extends Throwable {
    public SyntaxErrorException(char ch, int position) {
        super(String.format("Unexpected \"%s\" at position %s", ch, position));
    }

    public SyntaxErrorException(TokenType expected, TokenType given, int position) {
        super(String.format("Expected %s, given %s at position %d", expected.toString(), given.toString(), position));
    }
}
