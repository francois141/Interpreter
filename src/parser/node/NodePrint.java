package parser.node;

public class NodePrint extends NodeStatement {
	
	public NodeExpression expr;

	public NodePrint(NodeExpression expr) {
		this.expr = expr;
	}

}
