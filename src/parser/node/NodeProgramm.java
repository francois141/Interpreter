package parser.node;

import java.util.LinkedList;

public class NodeProgramm extends Node {
	
	public LinkedList<NodeStatement> statements = new LinkedList<NodeStatement>();

	public NodeProgramm() {
		
	}
	
	public void add(NodeStatement node) {
		statements.add(node);
	}

}
