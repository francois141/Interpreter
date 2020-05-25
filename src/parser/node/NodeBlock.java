package parser.node;

import java.util.LinkedList;
import java.util.List;

public class NodeBlock extends NodeStatement {
	
	public List<NodeStatement> statements = new LinkedList<NodeStatement>();

	public NodeBlock() {
		
	}

}
