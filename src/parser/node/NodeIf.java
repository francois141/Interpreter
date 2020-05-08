package parser.node;

public class NodeIf extends NodeStatement {
	
	private NodeExpression expr;
	private NodeBlock ifBlock;
	private NodeBlock elseBlock;

	public NodeIf(NodeExpression expr, NodeBlock ifBlock, NodeBlock elseBlock) {
		this.expr = expr;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}

}
