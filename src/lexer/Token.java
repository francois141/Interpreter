package lexer;


public class Token {
	
	private String value;
	private TokenType type;
	
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public static TokenType tokenize(String toTokenTypeize) {
		
		if(toTokenTypeize.equals("+")) {
			return TokenType.TOKEN_PLUS;
		}
		if(toTokenTypeize.equals("-")) {
			return TokenType.TOKEN_MINUS;
		}
		if(toTokenTypeize.equals("*")) {
			return TokenType.TOKEN_MULT;
		}
		if(toTokenTypeize.equals("/")) {
			return TokenType.TOKEN_DIV;
		}
		if(toTokenTypeize.equals("%")) {
			return TokenType.TOKEN_MODULO;
		}
		
		if(toTokenTypeize.equals("=")) {
			return TokenType.TOKEN_ASSIGN;
		}
		
		if(toTokenTypeize.equals("==")) {
			return TokenType.TOKEN_EQUAL;
		}
		if(toTokenTypeize.equals("<")) {
			return TokenType.TOKEN_LESS;
		}
		if(toTokenTypeize.equals("<=")) {
			return TokenType.TOKEN_LESSEQUAL;
		}
		if(toTokenTypeize.equals(">")) {
			return TokenType.TOKEN_GREATER;
		}
		if(toTokenTypeize.equals(">=")) {
			return TokenType.TOKEN_GREATEREQUAL;
		}
		if(toTokenTypeize.equals("!=")) {
			return TokenType.TOKEN_NOTEQUAL;
		}
		
		if(isInteger(toTokenTypeize)) {
			return TokenType.TOKEN_INTEGER;
		}
		if(isBoolean(toTokenTypeize)) {
			return TokenType.TOKEN_BOOLEAN;
		}
		
		return TokenType.TOKEN_NULL;
	}
	
	private static boolean isInteger(String in) {
		if(in.matches("/^[-+]?\\d+$")) {
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
	


}
