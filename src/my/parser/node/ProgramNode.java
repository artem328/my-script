package my.parser.node;

import java.util.List;

public class ProgramNode implements Node {
    private List<StatementNode> statements;

    public ProgramNode(List<StatementNode> statements) {
        this.statements = statements;
    }
}
