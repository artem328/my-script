package lexer;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class Tokenizer {
    final private static Pattern skip = Pattern.compile("\\s");
    final private static Pattern numberStart = Pattern.compile("[.0-9]");

    private Reader reader;
    private int currentPosition = -1;
    private int tokenPosition = -1;
    private int currentByte = -1;
    private int nextByte;

    public Tokenizer(Reader reader) throws IOException {
        this.reader = reader;
        nextByte = reader.read();
    }

    public Token next() throws IOException, SyntaxErrorException {
        moveToNextChar();
        tokenPosition = currentPosition;

        if (!hasCurrentChar()) {
            return new Token(TokenType.EOF, tokenPosition, toCharArray());
        }

        skip();

        if (!hasCurrentChar()) {
            return new Token(TokenType.EOF, tokenPosition,toCharArray());
        }

        return tokenize();
    }

    private Token tokenize() throws SyntaxErrorException, IOException {
        char ch = getCurrentChar();
        switch (ch) {
            case '+':
                return new Token(TokenType.OPERATOR_PLUS, tokenPosition, toCharArray(ch));
            case '-':
                return new Token(TokenType.OPERATOR_MINUS, tokenPosition, toCharArray(ch));
            case '*':
                return new Token(TokenType.OPERATOR_MULTIPLY, tokenPosition, toCharArray(ch));
            case '/':
                return new Token(TokenType.OPERATOR_DIVIDE, tokenPosition, toCharArray(ch));
            case '(':
                return new Token(TokenType.OPEN_PARENTHESIS, tokenPosition, toCharArray(ch));
            case ')':
                return new Token(TokenType.CLOSE_PARENTHESIS, tokenPosition, toCharArray(ch));
        }

        if (numberStart.matcher("" + ch).matches()) {
            return tokenizeNumber();
        }

        throw new SyntaxErrorException(ch, tokenPosition);
    }

    private Token tokenizeNumber() throws IOException {
        List<Character> characters = new ArrayList<>();
        char first = getCurrentChar();
        characters.add(first);
        List<String> allowed = new ArrayList<>();
        allowed.add("0-9");
        TokenType type = TokenType.INTEGER;

        if ('.' != first) {
            allowed.add(".");
        } else {
            type = TokenType.FLOAT;
        }

        Pattern pattern = Pattern.compile("[" + String.join("", allowed) + "]");

        while (hasNextChar() && pattern.matcher("" + getNextChar()).matches()) {
            moveToNextChar();
            char current = getCurrentChar();
            characters.add(current);

            if ('.' == current) {
                type = TokenType.FLOAT;
                allowed.remove(".");
                pattern = Pattern.compile("[" + String.join("", allowed) + "]");
            }
        }

        return new Token(type, tokenPosition, toCharArray(characters));
    }

    private boolean hasCurrentChar() {
        return -1 != currentByte;
    }

    private char getCurrentChar() {
        return (char) currentByte;
    }

    private char getNextChar() {
        return (char) nextByte;
    }

    private boolean hasNextChar() {
        return -1 != nextByte;
    }

    private void moveToNextChar() throws IOException {
        currentByte = nextByte;
        nextByte = reader.read();
        currentPosition++;
    }

    private void skip() throws IOException {
        char ch = getCurrentChar();

        while (skip.matcher("" + ch).matches()) {
            moveToNextChar();
            ch = getCurrentChar();
            tokenPosition++;
        }
    }

    private char[] toCharArray() {
        return new char[]{};
    }

    private char[] toCharArray(char ch) {
        return new char[]{ch};
    }

    private char[] toCharArray(List<Character> list) {
        char[] chars = new char[list.size()];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = list.get(i);
        }

        return chars;
    }
}
