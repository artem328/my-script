package my.lexer;

public enum TokenType {
    OPERATOR_PLUS,
    OPERATOR_MINUS,
    OPERATOR_MULTIPLY,
    OPERATOR_DIVIDE,
    SEMICOLON,
    OPEN_PARENTHESIS,
    CLOSE_PARENTHESIS,

    INTEGER,
    FLOAT,

    PRINT,
    PRINTLN,
    IDENTIFIER,

    EOF,
}