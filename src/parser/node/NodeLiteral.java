package parser.node;

public class NodeLiteral<T> extends NodeExpression {
	
	private T value;

	public NodeLiteral(T value) {
		this.value = value;
	}

	public T getValue() {
		return this.value;
	}
	
}
