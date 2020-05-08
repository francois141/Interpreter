package parser.node;

public class NodeBinaryExpression extends NodeExpression {
	
	private String op;
	private NodeExpression left;
	private NodeExpression right;

	public NodeBinaryExpression(String op, NodeExpression left, NodeExpression right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}

}
