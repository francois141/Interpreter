package parser.node;

public class NodeAssign extends NodeStatement{
	
	private String tag;
	private NodeExpression expr;

	public NodeAssign(String tag,NodeExpression expr) {
		this.tag = tag;
		this.expr = expr;
	}

}
