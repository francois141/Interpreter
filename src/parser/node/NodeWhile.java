package parser.node;

public class NodeWhile extends NodeStatement {
	
	NodeBlock block;
	NodeExpression expr;

	public NodeWhile(NodeExpression expr, NodeBlock block) {
		this.block = block;
		this.expr = expr;
	}

}
