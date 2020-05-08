package parser.node;

import java.util.List;
import java.util.Map;

public class NodeFuncDefinition extends NodeStatement {
	
	private String identifier;
	private Map<String,String> parameters;
	private List<String> variable_names;
	private String type;
	private NodeBlock block;

	public NodeFuncDefinition(String identifier,Map<String,String> parameters, List<String> variable_names,String type, NodeBlock block) {
		this.identifier = identifier;
		this.parameters = parameters;
		this.variable_names = variable_names;
		this.type = type;
		this.block = block;
	}

}
