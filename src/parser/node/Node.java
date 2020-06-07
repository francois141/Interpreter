package parser.node;

import java.util.LinkedList;

import parser.XMLVisitor;

public class Node {
	
	public Node() {
		
	}
	
	public String level(int depth) {
		String out = "";
		for(int i = 0; i < depth;i++) {
			out += "\t";
		}
		return out;
	}
	
	public String endl() {
		return "\n";
	}
	
	public String visitProgramm(NodeProgramm node,int depth) {
		String out = "";
		out += level(depth) + "<programm>" + endl();
		depth++;	
		for(int i = 0; i < node.statements.size();i++) {
			out += visitStatement(node.statements.get(i),depth);
		}	
		depth--;
		out += level(depth) + "<programm>" + endl();	
		return out;
	}
	
	public String visitStatement(NodeStatement node, int depth) { 
		if(node instanceof NodeDeclaration) return visitDeclaration((NodeDeclaration)node,depth);
		if(node instanceof NodeAssign)      return visitAssign((NodeAssign)node,depth);
		if(node instanceof NodePrint)       return visitPrint((NodePrint)node,depth);
		if(node instanceof NodeBlock)       return visitBlock((NodeBlock)node,depth);
		if(node instanceof NodeIf)          return visitIf((NodeIf)node, depth);
		if(node instanceof NodeReturn)      return visitReturn((NodeReturn)node, depth);
		if(node instanceof NodeWhile)       return visitWhile((NodeWhile)node, depth);
		return null;
	}
	
	public String visitIf(NodeIf node, int depth) {
		String out = "";
		out += level(depth) + "<if>" + endl();
		depth++;
		out += visitExpression(node.expr,depth);
		out += visitBlock(node.ifBlock,depth);
		depth--;
		out += level(depth) + "</if>" + endl();
		return out;
	}
	
	public String visitBlock(NodeBlock node, int depth) {
		String out = "";
		out += level(depth) + "<block>" + endl();
		depth++;
		for(int i = 0; i < node.statements.size();i++) {
			out += visitStatement(node.statements.get(i),depth);
		}
		depth--;
		out += level(depth) + "</block>" + endl();
		return out;
	}
	
	public String visitDeclaration(NodeDeclaration node,int depth) {	
		String out = "";	
		out+= level(depth) + "<declaration>" + endl();
		depth++;	
		out += level(depth) + "<id type : " + node.type + "  identifier : " + node.tag + " >" + endl();		
		depth++;	
		out+= visitExpression(node.expr,depth);	
		depth--;	
		depth--;
		out += level(depth) + "</declaration>" + endl();
		return out;
	}
	
	public String visitExpression(NodeExpression expr, int depth) {
		String out = "";
		out += level(depth) + "<expression>" + endl();
		depth++;
		if(expr instanceof NodeBinaryExpression) {out += visitBinaryExpression((NodeBinaryExpression) expr,depth);} // case there is a binary opertor
		if(expr instanceof NodeUnaryExpression)  {out += visitUnaryExpression((NodeUnaryExpression) expr,depth);} // case there is a unary operator
		if(expr instanceof NodeIdentifier)       {out += visitIdentifier((NodeIdentifier) expr,depth);} // case there is an identifier
		if(expr instanceof NodeLiteral)          {out += visitLiteral((NodeLiteral<?>) expr,depth);}
		depth--;
		out += level(depth) + "</expression>" + endl();
		return out;
	}
	
	public String visitBinaryExpression(NodeBinaryExpression expr, int depth) {
		String out = "";
		out += level(depth) + "<binaryExpression>" + endl();
		depth++;
		out += level(depth) +"<oprator " +expr.op + " >"+ endl();
		out += visitExpression(expr.left,depth);
		out += visitExpression(expr.right,depth);
		depth--;
		out += level(depth) + "</binaryExpression>" + endl();
		return out;
	}
	
	public String visitUnaryExpression(NodeUnaryExpression expr, int depth) {
		String out = "";
		out += level(depth) + "<unaryExpression>" + endl();
		depth++;
		out += level(depth) +"<oprator " +expr.op + " >"+ endl();
		out += visitExpression(expr.expression,depth);
		depth--;
		out += level(depth) + "</unaryExpression>" + endl();
		return out;
	}
	
	public String visitIdentifier(NodeIdentifier expr, int depth) {
		String out = "";
		out += level(depth) + "<identifier : " + expr.identifier + " >" + endl();
		return out;
	}
	
	public String visitLiteral(NodeLiteral<?> expr, int depth) {
		String out="";
		out += level(depth) + "<literal value : "+ expr.value + " >" + endl(); 
		return out;
	}
	
	public String visitAssign(NodeAssign node,int depth) {
		String out = "";	
		out+= level(depth) + "<assign>" + endl();
		depth++;	
		out += level(depth) + "<id identifier : " + node.tag + " >" + endl();		
		depth++;	
		out+= visitExpression(node.expr,depth);	
		depth--;	
		depth--;
		out += level(depth) + "</assign>" + endl();
		return out;	
	}
	
	public String visitPrint(NodePrint node,int depth) {
		String out = "";	
		out+= level(depth) + "<print>" + endl();
		depth++;		
		out+= visitExpression(node.expr,depth);		
		depth--;
		out += level(depth) + "</print>" + endl();
		return out;	
	}
	
	public String visitReturn(NodeReturn node, int depth) {
		String out = "";
		out += level(depth) + "<return>" + endl();
		depth++;
		out += visitExpression(node.expr,depth);
		depth--;
		out += level(depth) + "</return>" + endl();
		return out;
	}
	
	public String visitWhile(NodeWhile node, int depth) {
		String out = "";
		out += level(depth) + "<while>" + endl();
		depth++;
		out += visitExpression(node.expr,depth);
		out += visitBlock(node.block,depth);
		depth--;
		out += level(depth) + "</while>" + endl();
		return out;
	}

}
