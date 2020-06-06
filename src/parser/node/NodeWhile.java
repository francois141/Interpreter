package parser.node;

public class NodeWhile extends NodeStatement {
	
	public NodeBlock block;
	public NodeExpression expr;

	public NodeWhile(NodeExpression expr, NodeBlock block) {
		this.block = block;
		this.expr = expr;
	}

}
