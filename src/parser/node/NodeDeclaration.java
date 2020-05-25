package parser.node;

public class NodeDeclaration extends NodeStatement {
	
	public String type;
	public String tag;
	public NodeExpression expr;

	public NodeDeclaration(String type,String tag,NodeExpression expr) {
		this.type = type;
		this.tag = tag;
		this.expr = expr;
	}
	
	

}
