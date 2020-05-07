package lexer;


public class Token {
	
	private String value;
	private TokenType type;
	
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public static TokenType tokenize(String toTokenType) {
		
		if(toTokenType.equals("+")) {
			return TokenType.TOKEN_PLUS;
		}
		if(toTokenType.equals("-")) {
			return TokenType.TOKEN_MINUS;
		}
		if(toTokenType.equals("*")) {
			return TokenType.TOKEN_MULT;
		}
		if(toTokenType.equals("/")) {
			return TokenType.TOKEN_DIV;
		}
		if(toTokenType.equals("%")) {
			return TokenType.TOKEN_MODULO;
		}
		
		if(toTokenType.equals("=")) {
			return TokenType.TOKEN_ASSIGN;
		}
		
		if(toTokenType.equals(";")) {
			return TokenType.TOKEN_ENDINSTRUCTION;
		}
		
		if(toTokenType.equals("==")) {
			return TokenType.TOKEN_EQUAL;
		}
		if(toTokenType.equals("<")) {
			return TokenType.TOKEN_LESS;
		}
		if(toTokenType.equals("<=")) {
			return TokenType.TOKEN_LESSEQUAL;
		}
		if(toTokenType.equals(">")) {
			return TokenType.TOKEN_GREATER;
		}
		if(toTokenType.equals(">=")) {
			return TokenType.TOKEN_GREATEREQUAL;
		}
		if(toTokenType.equals("!=")) {
			return TokenType.TOKEN_NOTEQUAL;
		}
		
		if(toTokenType.equals("(")) {
			return TokenType.TOKEN_OPEN;
		}
		if(toTokenType.equals(")")) {
			return TokenType.TOKEN_CLOSE;
		}
		if(toTokenType.equals("{")) {
			return TokenType.TOKEN_BRACKETSOPEN;
		}
		if(toTokenType.equals("}")) {
			return TokenType.TOKEN_BRACKETSCLOSE;
		}
		
		if(isInteger(toTokenType)) {
			return TokenType.TOKEN_INTEGER;
		}
		if(isBoolean(toTokenType)) {
			return TokenType.TOKEN_BOOLEAN;
		}
		
		if(isKeyWord(toTokenType)) {
			switch(toTokenType) {
			case "if": return TokenType.TOKEN_IF; 
			case "for": return TokenType.TOKEN_FOR; 
			case "while": return TokenType.TOKEN_WHILE;
			case "return": return TokenType.TOKEN_RETURN; 
			case "int": return TokenType.TOKEN_INTEGER; 
			case "bool": return TokenType.TOKEN_BOOLEAN; 
			case "else": return TokenType.TOKEN_ELSE; 
			case "print": return TokenType.TOKEN_PRINT;
			}
		}
		
		if(isIdentifier(toTokenType)) {
			return TokenType.TOKEN_IDENTIFIER;
		}
		
		if(isInvalid(toTokenType)) {
			return TokenType.TOKEN_INVALID;
		}
		
		return TokenType.TOKEN_NULL;
	}
	
	private static boolean isInteger(String in) {
		if(in.matches("^\\d+$")) {
			return true;
		}
		return false;
	}
	
	private static boolean isBoolean(String in ) {
		if(in.matches("true|false")) {
			return true;
		}
		return false;
	}
	
	private static boolean isKeyWord(String in) {
		if(in.matches("if|for|while|return|int|bool|else|print")) {
			return true;
		}
		return false;
	}
	
	private static boolean isIdentifier(String in) {
		if(in.matches("(_|[a-zA-z])[_a-zA-z0-9]*")) {
			return true;
		}
		return false;
	}
	
	private static boolean isInvalid(String in) {
		if(in.equals("") | in.equals(" ") | in.equals("\n") | in.equals("\t")) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		String output = type.name() + " Value : " + value ;
		return output;
	}
	


}
