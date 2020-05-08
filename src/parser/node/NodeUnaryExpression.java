package parser.node;

public class NodeUnaryExpression extends NodeExpression {
	
	private String op;
	private NodeExpression expression;

	public NodeUnaryExpression(String op, NodeExpression expression) {
		this.op = op;
		this.expression = expression;
	}

}
