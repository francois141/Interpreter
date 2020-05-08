package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lexer {
	
	String toTokenize;
	
	private static final String endCharString = "+-/%*(){} \n\t;";
	private static final String semiEndCharString = "=!<>";
	
	List<Token> listOfTokens = new LinkedList<Token>();
	
	Set<Character> endChars = new HashSet<Character>();
	Set<Character> semiEndChars = new HashSet<Character>();
	
	public Lexer(String input) {
		this.toTokenize = input;
		initializeEndChars();
		initializeSemiEndChars();
	}
	
	private void initializeEndChars() {
		for(int i = 0;i < endCharString.length();i++) {
			endChars.add(endCharString.charAt(i));
		}
	}
	
	private void initializeSemiEndChars() {
		for(int i = 0;i < semiEndCharString.length();i++) {
			semiEndChars.add(semiEndCharString.charAt(i));
		}
	}
	
	public List<Token> toTokens(){
		
		if(listOfTokens.size() > 0) {
			return listOfTokens;
		}
		
		toTokenize += " ";
		
		int currentIndex = 0;
		
		String sequenceToTokenize = "";
		
		while(currentIndex < toTokenize.length()) {
			
			if(isSemiEndChar(currentIndex)) {
				if(Token.tokenize(sequenceToTokenize) != TokenType.TOKEN_NULL) {
					listOfTokens.add(convertToToken(sequenceToTokenize));
					sequenceToTokenize = "";
				}
				
				Token token = null;
				
				if(toTokenize.charAt(currentIndex) == '=') {
					switch (next(currentIndex)){
					case'=': token = convertToToken(currentIndex,currentIndex+2); currentIndex++; break;
					case'!': token = convertToToken(currentIndex,currentIndex+2); currentIndex++; break;
					default: token = convertToToken(currentIndex,currentIndex+1);
					}
				}
				
				else if(toTokenize.charAt(currentIndex) == '!') {
					switch (next(currentIndex)){
					case'=': token = convertToToken(currentIndex,currentIndex+2); currentIndex++; break;
					default: token = convertToToken(currentIndex,currentIndex+1);
					}
				}
				
				else if(toTokenize.charAt(currentIndex) == '<') {
					switch (next(currentIndex)){
					case'=': token = convertToToken(currentIndex,currentIndex+2); currentIndex++; break;
					default: token = convertToToken(currentIndex,currentIndex+1);
					}
				}
				
				else if(toTokenize.charAt(currentIndex) == '>') {
					switch (next(currentIndex)){
					case'=': token = convertToToken(currentIndex,currentIndex+2); currentIndex++; break;
					default: token = convertToToken(currentIndex,currentIndex+1);
					}
				}
				
				listOfTokens.add(token);
				
			}
			
			else if(isEndChar(currentIndex)) {
				if(Token.tokenize(sequenceToTokenize) != TokenType.TOKEN_NULL) {
					listOfTokens.add(convertToToken(sequenceToTokenize));
					sequenceToTokenize = "";
				}
				String endString = toTokenize.substring(currentIndex, currentIndex+1);
				if(Token.tokenize(endString) != TokenType.TOKEN_NULL) {
					listOfTokens.add(convertToToken(endString));
				}
				
			}
			
			else {
				sequenceToTokenize += toTokenize.charAt(currentIndex);
			}
			
			currentIndex++;
		}
		
		listOfTokens.add(new Token(TokenType.TOKEN_END,""));
		
		return listOfTokens;
	}
	
	public char next(int index) {
		if(index >= toTokenize.length() -1) {
			return ' ';
		}
		return toTokenize.charAt(index+1);
	}
	
	private Token convertToToken(String in) {
		return new Token(Token.tokenize(in),in); // Return a Token with the current value
	}
	
	private Token convertToToken(int start,int end) {
		return new Token(Token.tokenize(toTokenize.substring(start,end)),toTokenize.substring(start, end)); // Create a token with a substring
	}
	
	private boolean isEndChar(int index) {
		if(endChars.contains(toTokenize.charAt(index))) {
			return true;
		}
		return false;
	}
	
	private boolean isSemiEndChar(int index) {
		if(semiEndChars.contains(toTokenize.charAt(index))) {
			return true;
		}
		return false;
		
	}
	
	public String toString() { // TODO: Implement this function
		String output = "";
		
		return output;
	}

}
