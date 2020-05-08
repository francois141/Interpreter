package parser.node;

public class NodeDeclaration extends NodeStatement {
	
	private String type;
	private String tag;
	private NodeExpression expr;

	public NodeDeclaration(String type,String tag,NodeExpression expr) {
		this.type = type;
		this.tag = tag;
		this.expr = expr;
	}

}
