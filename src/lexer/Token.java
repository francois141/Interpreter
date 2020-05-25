package lexer;


public class Token {
	
	private String value;
	private TokenType type;
	
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public TokenType getTokenType() {
		return this.type;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static TokenType tokenize(String toTokenType) {
		
		if(isAdditiveOp(toTokenType)) {
			return TokenType.TOKEN_ADDITIVEOP;
		}
		
		if(isMultiplicativeOp(toTokenType)) {
			return TokenType.TOKEN_MULTIPLICATIVEOP;
		}
		
		if(isUnaryOp(toTokenType)) {
			return TokenType.TOKEN_UNARYOP;
		}
		
		if(isCompOp(toTokenType)) {
			return TokenType.TOKEN_COMPOP;
		}
		
		
		if(toTokenType.equals("=")) {
			return TokenType.TOKEN_ASSIGN;
		}
		
		if(toTokenType.equals(";")) {
			return TokenType.TOKEN_ENDINSTRUCTION;
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
			case "else": return TokenType.TOKEN_ELSE; 
			case "print": return TokenType.TOKEN_PRINT;
			}
		}
		
		if(isVariableType(toTokenType)) {
			return TokenType.TOKEN_VARIABLETYPE;
		}
		
		if(isIdentifier(toTokenType)) {
			return TokenType.TOKEN_IDENTIFIER;
		}
		
		if(isInvalid(toTokenType)) {
			return TokenType.TOKEN_INVALID;
		}
		
		return TokenType.TOKEN_NULL;
	}
	
	private static boolean isAdditiveOp(String in) {
		if(in.equals("+") || in.equals("-")) {
			return true;
		}
		return false;
	}
	
	private static boolean isMultiplicativeOp(String in) {
		if(in.equals("*") | in.equals("/") | in.equals("%") ) {
			return true;
		}
		return false;
	}
	
	private static boolean isUnaryOp(String in) {
		if(in.matches("!") && in.length() == 1) { // Ugrade this with regex
			return true;
		}
		return false;
	}
	
	private static boolean isCompOp(String in) {
		if(in.matches("==|<=|<|>|>=|!=")) {
			return true;
		}
		return false;
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
	
	private static boolean isVariableType(String in) {
		if(in.matches("int|bool")) {
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
