package parser.node;

import java.util.List;

public class NodeFunctionCall extends NodeExpression {
	
	private String identifier;
	private List<NodeExpression> parameters;

	public NodeFunctionCall(String identifier, List <NodeExpression> parameters) {
		this.identifier = identifier;
		this.parameters = parameters;
	}

}
