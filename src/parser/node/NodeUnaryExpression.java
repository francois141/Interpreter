package parser.node;

public class NodeUnaryExpression extends NodeExpression {
	
	public String op;
	public NodeExpression expression;

	public NodeUnaryExpression(String op, NodeExpression expression) {
		this.op = op;
		this.expression = expression;
	}

}
