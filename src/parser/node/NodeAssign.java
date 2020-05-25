package parser.node;

public class NodeAssign extends NodeStatement{
	
	public String tag;
	public NodeExpression expr;

	public NodeAssign(String tag,NodeExpression expr) {
		this.tag = tag;
		this.expr = expr;
	}

}
