package parser.node;

public class NodeIf extends NodeStatement {
	
	public NodeExpression expr;
	public NodeBlock ifBlock;
	private NodeBlock elseBlock;

	public NodeIf(NodeExpression expr, NodeBlock ifBlock, NodeBlock elseBlock) {
		this.expr = expr;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}

	public NodeIf() {
		// TODO Auto-generated constructor stub
	}

}
