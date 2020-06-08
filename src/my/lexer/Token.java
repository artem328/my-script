package my.lexer;

public class Token {
    private TokenType type;
    private int position;
    private char[] value;

    public Token(TokenType type, int position, char[] value) {
        this.type = type;
        this.position = position;
        this.value = value;
    }

    public char[] getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public String getStringValue() {
        return String.copyValueOf(value);
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", position=" + position +
                ", value=" + getStringValue() +
                '}';
    }
}
