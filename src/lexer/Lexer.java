package lexer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lexer {
	
	String toTokenize;
	
	List<Token> listOfTokens = new LinkedList<Token>();
	
	Set<Character> endChars = new HashSet<Character>();
	
	public Lexer(String input) {
		this.toTokenize = input;
		
		File endChars = new File("endChars.txt");
	}
	
	public List<Token> toTokens(){
		
		toTokenize += " ";
		
		int currentIndex = 0;
		
		String sequenceToTokenize = "";
		
		while(currentIndex < toTokenize.length()) {
			
			currentIndex++;
		}
		
		return listOfTokens;
	}
	
	private boolean isEndChar(int index) {
		if(endChars.contains(toTokenize.charAt(index))) {
			return true;
		}
		return false;
	}
	
	
	public String toString() {
		String output = "";
		
		return output;
	}

}
