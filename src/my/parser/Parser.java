package my.parser;

import my.SyntaxErrorException;
import my.lexer.Lexer;
import my.lexer.Token;
import my.lexer.TokenType;
import my.parser.node.ExpressionNode;
import my.parser.node.ProgramNode;
import my.parser.node.StatementNode;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    /**
     * <program> ::= {<statement>}.
     */
    public ProgramNode Program() throws SyntaxErrorException {
        List<StatementNode> statements = new ArrayList<>();

        lexer.moveToNext();

        while (!lexer.isNextTokenOfType(TokenType.EOF)) {
            statements.add(Statement());
        }

        return new ProgramNode(statements);
    }

    /**
     * <statement> ::= <print-statement> ";" | <expression> ";".
     */
    private StatementNode Statement() throws SyntaxErrorException {
        if (lexer.isNextTokenOfAnyType(TokenType.PRINT, TokenType.PRINTLN)) {
            // return print
            match(TokenType.SEMICOLON);
        }

        ExpressionNode expression = Expression();
        match(TokenType.SEMICOLON);

        return expression;
    }

    /**
     * <expression> ::= <arithmetic-expression> | <literal>
     */
    private ExpressionNode Expression() {
        if (!lexer.isNextTokenOfAnyType(TokenType.INTEGER, TokenType.FLOAT)) {

        }
        return new ExpressionNode();
    }

    private void match(TokenType type) throws SyntaxErrorException {
        if (lexer.isNextTokenOfType(type)) {
            lexer.moveToNext();
            return;
        }

        Token next = lexer.lookahead();

        throw new SyntaxErrorException(type, next.getType(), next.getPosition());
    }
}
