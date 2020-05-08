package parser.node;

public class NodePrint extends NodeStatement {
	
	NodeExpression expr;

	public NodePrint(NodeExpression expr) {
		this.expr = expr;
	}

}
