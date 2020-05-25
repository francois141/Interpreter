package parser.node;

public class NodeBinaryExpression extends NodeExpression {
	
	public String op;
	public NodeExpression left;
	public NodeExpression right;

	public NodeBinaryExpression(String op, NodeExpression left, NodeExpression right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}

}
