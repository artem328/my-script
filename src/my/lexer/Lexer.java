package my.lexer;

import my.SyntaxErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<Token> tokens = new ArrayList<>();
    private int position = 0;
    private Token token;
    private Token lookahead;

    public Lexer(InputStream input) throws IOException, SyntaxErrorException {
        Reader reader = new InputStreamReader(input);
        Tokenizer tokenizer = new Tokenizer(reader);

        Token token;
        do {
            token = tokenizer.next();
            tokens.add(token);
        } while (!token.getType().equals(TokenType.EOF));

        reader.close();
    }

    @Deprecated
    public void printTokens() {
        for (Token t : tokens) {
            System.out.println(t);
        }
    }

    public boolean isNextTokenOfType(TokenType type) {
        return null != lookahead && lookahead.getType().equals(type);
    }

    public boolean isNextTokenOfAnyType(TokenType... types) {
        for (TokenType type : types) {
            if (isNextTokenOfType(type)) {
                return true;
            }
        }

        return false;
    }

    public void moveToNext() {
        token = lookahead;
        lookahead = tokens.size() > position ? tokens.get(position) : null;
        position++;
    }

    public Token lookahead() {
        return lookahead;
    }
}
