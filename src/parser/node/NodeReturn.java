package parser.node;

public class NodeReturn extends NodeStatement {
	
	private NodeExpression expr;

	public NodeReturn(NodeExpression expr) {
		this.expr = expr;
	}

}
