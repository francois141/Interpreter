package parser;

import java.util.LinkedList;
import java.util.List;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import parser.node.NodeAssign;
import parser.node.NodeBinaryExpression;
import parser.node.NodeDeclaration;
import parser.node.NodeExpression;
import parser.node.NodeIdentifier;
import parser.node.NodeLiteral;
import parser.node.NodePrint;
import parser.node.NodeProgramm;
import parser.node.NodeStatement;
import parser.node.NodeUnaryExpression;

public class Parser {
	
	private Lexer lex;
	private NodeProgramm programm = new NodeProgramm();
	
	private Token currentToken = null;
	
	private int index;
	
	private List<Token> listTokens;
	
	public Parser(Lexer lex) {
		this.lex = lex;
		listTokens = this.lex.toTokens();
		
		index = 0;
		currentToken = listTokens.get(0);
	}
	
	public NodeProgramm read() {
		programm = parseProgramm();
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
	
	public void parseBlock() {
		
	}
	
	public NodeStatement parseStatement() {
		
		NodeStatement output = null;
		
		switch(currentToken.getTokenType()) {
		case TOKEN_VARIABLETYPE: output = parseVariableDecl();   break;
		case TOKEN_IDENTIFIER:	 output = parseIdentifier();     break;
		case TOKEN_PRINT:	     output = parsePrintStatement(); break;
		//case TOKEN_IF: 
		//case TOKEN_WHILE:
		//case TOKEN_RETURN:
		//case TOKEN_BRACKETSOPEN:
		}
		
		consumeToken();
		
		if(current() != TokenType.TOKEN_ENDINSTRUCTION) {
			error("Missing EndInstruction " + current());
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
			return new NodeLiteral<Integer>(Integer.parseInt(currentToken.getValue()));
		}
		
		if(type == TokenType.TOKEN_BOOLEAN) {
			return new NodeLiteral<Boolean>(Boolean.parseBoolean(currentToken.getValue()));
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
