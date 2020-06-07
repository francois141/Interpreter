package parser;

import java.util.LinkedList;
import java.util.List;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
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

public class Parser {
	
	private Lexer lex;
	public NodeProgramm programm = new NodeProgramm();
	
	private Token currentToken = null;
	
	private int index;
	
	private List<Token> listTokens;
	
	private XMLVisitor xmlVisitor = new XMLVisitor();
	
	public Parser(Lexer lex) {
		this.lex = lex;
		listTokens = this.lex.toTokens();
		
		index = 0;
		currentToken = listTokens.get(0);
	}
	
	public NodeProgramm read() {
		programm = parseProgramm();
		xmlVisitor.setProgramm(this.programm); // give the programm to the XMLManager
		xmlVisitor.createXML("xml.txt");
		return programm;
	}
	
	
	public void consumeToken() {	
		index++;	
		if(index >= listTokens.size()) {
			return;
		}
		currentToken = listTokens.get(index);
	}
	
	public TokenType getNext() {
		if(index + 1 < listTokens.size()) {
			return listTokens.get(index+1).getTokenType(); 
		}
		return null;
	}
	
	public TokenType current() {
		return currentToken.getTokenType();
	}
	
	public NodeProgramm parseProgramm() {
		NodeProgramm node = new NodeProgramm();
		
		while(currentToken.getTokenType() != TokenType.TOKEN_END) {
			node.statements.add(parseStatement());
			consumeToken();
		}
		
		return node;
	}
	
	public NodeBlock parseBlock() {
		
		NodeBlock node = new NodeBlock();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_BRACKETSOPEN) {
			error("No brackets");
		}
		
		consumeToken();
		
		while(currentToken.getTokenType() != TokenType.TOKEN_BRACKETSCLOSE) {
			node.statements.add(parseStatement());
			consumeToken();
		}
		
		return node;
	}
	
	public NodeStatement parseStatement() {
		
		NodeStatement output = null;
		
		boolean needEnd = true;
		
		switch(currentToken.getTokenType()) {
		case TOKEN_VARIABLETYPE: needEnd = true;  output = parseVariableDecl();   break;
		case TOKEN_IDENTIFIER:	 needEnd = true;  output = parseIdentifier();     break;
		case TOKEN_PRINT:	     needEnd = true;  output = parsePrintStatement(); break;
		case TOKEN_IF:           needEnd = false; output = parseIf();			  break;
		case TOKEN_WHILE:        needEnd = false; output = parseWhile();          break;
		case TOKEN_RETURN:	     needEnd = true;  output = parseReturn();		  break;
		case TOKEN_BRACKETSOPEN: needEnd = false; output = parseBlock();          break;
		}
		
		if(needEnd) {
			consumeToken();
		}
		
		if(current() != TokenType.TOKEN_ENDINSTRUCTION && needEnd) { 
			error("Missing EndInstruction" + current());
		}
		
		return output;
	}
	
	public void error(String in) {
		System.out.println(in);
	}
	
	public NodeDeclaration parseVariableDecl(){
		
		String type;
		String identifier;
		NodeExpression expression;
		
		if(currentToken.getTokenType() != TokenType.TOKEN_VARIABLETYPE) {
			error("Error VariableType");
		}
		
		type = currentToken.getValue();
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_IDENTIFIER) {
			error("Error Identifier " + current());
		}
		
		identifier = currentToken.getValue();

		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_ASSIGN) {
			error("Error assign missing " + current());
		}
		
		expression = parseExpression();
		
		return new NodeDeclaration(type,identifier,expression);
	}
	
	public NodeIf parseIf() {
		
		
		NodeExpression expr = null;
		NodeBlock block = null;
		
		if(currentToken.getTokenType() != TokenType.TOKEN_IF) {
			error("No if");
		}
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_OPEN) {
			error("No open token");
		}
		
		expr = parseExpression();
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_CLOSE) {
			error("no close token");
		}
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_BRACKETSOPEN) {
			error("No block");
		}
		
		block = parseBlock();
		return new NodeIf(expr,block,null);
	}
	
	public NodeWhile parseWhile() {
		
		NodeExpression expr = null;
		NodeBlock block = null;
		
		if(currentToken.getTokenType() != TokenType.TOKEN_WHILE) {
			error("No WHILE");
		}
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_OPEN) {
			error("No open token");
		}
		
		expr = parseExpression();
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_CLOSE) {
			error("no close token");
		}
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_BRACKETSOPEN) {
			error("No block");
		}
		
		block = parseBlock();
		
		return new NodeWhile(expr,block);
	}
	
	public NodeReturn parseReturn() {
		if(currentToken.getTokenType() != TokenType.TOKEN_RETURN) {
			error("No Token Return");
		}
		return new NodeReturn(parseExpression());
	}
	
	public NodePrint parsePrintStatement() {
		NodeExpression expr = parseExpression();
		return new NodePrint(expr);
		
	}
	
	public NodeAssign parseIdentifier(){
		
		String identifier;
		NodeExpression expr;
		
		if(currentToken.getTokenType() != TokenType.TOKEN_IDENTIFIER) {
			error("Error Identifier " + current());
			return null;
		}
		
		identifier = currentToken.getValue();
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_ASSIGN) {
			error("Error Assign");
			return null;
		}

		expr = parseExpression();
		
		return new NodeAssign(identifier,expr);
	}
	
	public NodeExpression parseExpression() {
		NodeExpression simpleExpression = parseSimpleExpression();
		
		if(getNext() == TokenType.TOKEN_COMPOP) {
			consumeToken();
			return new NodeBinaryExpression(currentToken.getValue(),simpleExpression,parseExpression());
		}
		
		return simpleExpression;
	}
	
	public NodeExpression parseSimpleExpression() {
		NodeExpression expressionTerm= parseTerm();
		
		if(getNext() == TokenType.TOKEN_ADDITIVEOP) {
			consumeToken();
			return new NodeBinaryExpression(currentToken.getValue(),expressionTerm,parseSimpleExpression());
		}
		return expressionTerm;
	}
	
	public NodeExpression parseTerm() {
		NodeExpression expressionFactor = parseFactor();
		
		if(getNext() == TokenType.TOKEN_MULTIPLICATIVEOP) {
			consumeToken();
			return new NodeBinaryExpression(currentToken.getValue(),expressionFactor,parseFactor());
		}
		
		return expressionFactor;
	}
	
	public NodeExpression parseFactor() {
		
		consumeToken();
		
		TokenType type = currentToken.getTokenType();
		
		if(type == TokenType.TOKEN_INTEGER) {
			return new NodeLiteral(currentToken.getValue(),"Integer");
		}
		
		if(type == TokenType.TOKEN_BOOLEAN) {
			return new NodeLiteral(currentToken.getValue(),"Boolean");
		}
		
		if(type == TokenType.TOKEN_IDENTIFIER) {
			return new NodeIdentifier(currentToken.getValue());
		}
		
		if(type == TokenType.TOKEN_OPEN) {
			NodeExpression subExpression = parseExpression();
			consumeToken();
			if(current() != TokenType.TOKEN_CLOSE) {
				System.out.println("Error");
			}
			return subExpression;
		}
			
		if(type == TokenType.TOKEN_UNARYOP) {
			String op = currentToken.getValue();
			NodeExpression expr = parseExpression();
			return new NodeUnaryExpression(op,expr);
		}
		
		System.out.println("Error BAD PARSING  "  + currentToken.getTokenType());
			
		return null;
	}

}
