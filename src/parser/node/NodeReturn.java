package parser.node;

public class NodeReturn extends NodeStatement {
	
	public NodeExpression expr;

	public NodeReturn(NodeExpression expr) {
		this.expr = expr;
	}

}
