package lexer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<Token> tokens = new ArrayList<>();

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
}
