package interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.node.NodeAssign;
import parser.node.NodeBinaryExpression;
import parser.node.NodeBlock;
import parser.node.NodeDeclaration;
import parser.node.NodeExpression;
import parser.node.NodeIdentifier;
import parser.node.NodeIf;
import parser.node.NodeLiteral;
import parser.node.NodePrint;
import parser.node.NodeProgramm;
import parser.node.NodeReturn;
import parser.node.NodeStatement;
import parser.node.NodeUnaryExpression;
import parser.node.NodeWhile;

public class Interpreter {
	
	private NodeProgramm programm;
	
	private Map<String,String> values = new HashMap<String,String>();
	private Map<String,String> type   = new HashMap<String,String>();
	
	private String valueReturn = null;
	private String typeReturn = null;
	
	public Interpreter(NodeProgramm programm) {
		this.programm = programm;
	}
	
	public void interpret() {
		for(int i = 0; i < programm.statements.size();i++) {
			interpretStatement(programm.statements.get(i));
		}	
	}
	
	public void interpretBlock(NodeBlock currentBlock) {
		for(int i = 0; i < currentBlock.statements.size();i++) {
			interpretStatement(currentBlock.statements.get(i));

		}
	}
	
	public void interpretStatement(NodeStatement currentNode) {
		if(currentNode instanceof NodePrint) interpretPrint((NodePrint)currentNode);
		if(currentNode instanceof NodeDeclaration) interpretDeclaration((NodeDeclaration)currentNode);
		if(currentNode instanceof NodeAssign) interpretAssign((NodeAssign)currentNode);
		if(currentNode instanceof NodeIf)     interpretIfExpression((NodeIf)currentNode);
		if(currentNode instanceof NodeBlock)  interpretBlock((NodeBlock)currentNode); 
		if(currentNode instanceof NodeWhile)  interpretWhile((NodeWhile) currentNode);
	}
	


	private void interpretReturn(NodeReturn currentNode) {
		
	}

	public void interpretWhile(NodeWhile currentNode) {
		while(true) {
			NodeLiteral out = interpretExpression(currentNode.expr);
			if(!out.type.equals("Boolean")) {
				System.out.println("The expression is not a boolean");
			}
			if(out.value.equals("true")) {
				interpretBlock(currentNode.block);
			}
			else {
				return;
			}
		}
	}

	public void interpretAssign(NodeAssign node) {
		if(!values.containsKey(node.tag)) {
			System.out.println("The key is not in the assign");
		}
		
		NodeLiteral expr = interpretExpression(node.expr);
		
		if(!type.get(node.tag).equals(expr.type)){
			System.out.println("Not the same type in the assign");
		}
		else {
			values.put(node.tag, expr.value);
		}
		return;
	}
	
	public void interpretDeclaration(NodeDeclaration node) {
		String typeCurrent = node.type;
		String tag  = node.tag;
		NodeLiteral nodeLiteral = interpretExpression(node.expr);
		
		if(typeCurrent.equals(nodeLiteral.type)) {
			System.out.println("Error not the same type for the declaration ");
		}
		values.put(tag, nodeLiteral.value);
		type.put(tag,nodeLiteral.type);
	}

	public void interpretPrint(NodePrint node) {
		System.out.println("Print : " + interpretExpression(node.expr).value);
	}
	
	public NodeLiteral interpretExpression(NodeExpression node) {
		NodeLiteral output = null;
		if(node instanceof NodeBinaryExpression) output = interpretBinaryExpression((NodeBinaryExpression) node);
		if(node instanceof NodeIdentifier)       output = interpretIdentifier((NodeIdentifier) node);
		if(node instanceof NodeLiteral)          output = interpretLiteral((NodeLiteral)node);
		if(node instanceof NodeUnaryExpression)  output = interpretUnaryExpression((NodeUnaryExpression)node);
		return output;
	}

	private void interpretIfExpression(NodeIf node) {	
		NodeLiteral outTop = interpretExpression(node.expr);
		if(outTop.type != "Boolean") {
			System.out.println("Error not a boolean output");
		}
		if(outTop.value.equals("true")) {
			interpretBlock(node.ifBlock);
		}
	}

	private NodeLiteral interpretLiteral(NodeLiteral node) {
		return node;
	}

	private NodeLiteral interpretUnaryExpression(NodeUnaryExpression node) {
		NodeLiteral output = interpretExpression(node.expression);
		if(output.type != "Boolean") {
			System.out.println("error this is not a boolean");
		}
		if(output.value.equals("true")) {
			output.value = "false";
		}
		else {
			output.value = "true";
		}
		return output;
	}

	private NodeLiteral interpretIdentifier(NodeIdentifier node) {
		if(values.containsKey(node.identifier)) {
			return new NodeLiteral(values.get(node.identifier),type.get(node.identifier));
		}
		System.out.println("Not found in the map");
		return null;	
	}

	private NodeLiteral interpretBinaryExpression(NodeBinaryExpression node) {
		NodeLiteral left = interpretExpression(node.left);
		NodeLiteral right = interpretExpression(node.right);
		
		if(left.type != right.type) {
			System.out.println("Not the same type");
		}
		
		if(left.type == "Integer" && "+-/*%".contains(node.op)) {
			int leftInt = Integer.parseInt(left.value);
			int rightInt = Integer.parseInt(right.value);
			int result = 0;
			switch(node.op) {
				case"+": result = leftInt + rightInt; break;
				case"-": result = leftInt - rightInt; break;
				case"*": result = leftInt * rightInt; break;
				case"/": result = leftInt / rightInt; break;
				case"%": result = leftInt % rightInt; break;
			}
			return new NodeLiteral(Integer.toString(result),"Integer");
		}		
		
		else if(left.type == "Integer") {
			int leftInt = Integer.parseInt(left.value);
			int rightInt = Integer.parseInt(right.value);
			boolean result = false;
			switch(node.op) {
				case"==": result = leftInt == rightInt; break;
				case"!=": result = leftInt != rightInt; break;
				case"<=": result = leftInt <= rightInt; break;
				case">=": result = leftInt >= rightInt; break;
				case"<": result = leftInt < rightInt; break;
				case">": result = leftInt > rightInt; break;
			}
			return new NodeLiteral(Boolean.toString(result),"Boolean");
		}
		
		if(left.type == "Boolean") {
			boolean leftInt = Boolean.parseBoolean(left.value);
			boolean rightInt = Boolean.parseBoolean(right.value);
			boolean result = true;;
			switch(node.op) {
				case"==": result = leftInt == rightInt; break;
				case"!=": result = leftInt != rightInt; break;

			}
			System.out.println("hello");
			return new NodeLiteral(Boolean.toString(result),"Boolean");
		}		
				
		return null;
	}
}
