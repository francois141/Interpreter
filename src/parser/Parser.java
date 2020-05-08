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
import parser.node.NodePrint;
import parser.node.NodeProgramm;
import parser.node.NodeStatement;

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
		
		programm = parseProgramm();
	}
	
	public void consumeToken() {	
		index++;	
		currentToken = listTokens.get(index);
	}
	
	public TokenType getNext() {
		if(index + 1 < listTokens.size()) {
			return listTokens.get(index+1).getTokenType(); 
		}
		return null;
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
		
		switch(currentToken.getTokenType()) {
		case TOKEN_VARIABLETYPE: return parseVariableDecl();
		case TOKEN_IDENTIFIER:	return parseIdentifier();
		case TOKEN_PRINT:	return parsePrintStatement();
		//case TOKEN_IF: 
		//case TOKEN_WHILE:
		//case TOKEN_RETURN:
		//case TOKEN_BRACKETSOPEN:
		}
		
		return null;
	}
	
	public void error() {
		System.out.println("error");
	}
	
	public NodeDeclaration parseVariableDecl(){
		
		String type;
		String identifier;
		NodeExpression expression;
		
		if(currentToken.getTokenType() != TokenType.TOKEN_VARIABLETYPE) {
			error();
		}
		
		type = currentToken.getValue();
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_IDENTIFIER) {
			error();
		}
		
		identifier = currentToken.getValue();
		
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
			error();
			return null;
		}
		
		identifier = currentToken.getValue();
		
		consumeToken();
		
		if(currentToken.getTokenType() != TokenType.TOKEN_ASSIGN) {
			error();
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
		return null;
	}
	
	

}
