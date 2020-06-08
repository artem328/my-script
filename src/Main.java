import my.SyntaxErrorException;
import my.lexer.Lexer;
import my.parser.Parser;
import my.parser.node.ProgramNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SyntaxErrorException {
        String filename;
        try {
            filename = args[0];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("First argument must be a path to a myscript file");
            System.exit(1);
            return;
        }

        Lexer lexer = new Lexer(new FileInputStream(new File(filename)));

        lexer.printTokens();

        Parser parser = new Parser(lexer);

        ProgramNode p = parser.Program();

        System.out.println(p);
    }
}
