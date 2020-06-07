package parser.node;

public class NodeLiteral<T> extends NodeExpression {
	
	public String value;
	public String type;

	public NodeLiteral(String value,String type) {
		this.value = value;
		this.type = type;
	}

	
	
}
