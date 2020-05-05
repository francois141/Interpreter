package lexer;

enum tokens{
	TOKEN_PLUS,
	TOKEN_MINUS,
	TOKEN_MULT,
	TOKEN_DIV,
	TOKEN_MODULO
}

public class Token {
	
	private String toTokenize;
	private String token;
	
	public Token(String toTokenize) {
		this.toTokenize = toTokenize;
		tokenize();
	}
	
	public tokens tokenize() {
		if(toTokenize == "+") {
			return tokens.TOKEN_PLUS;
		}
		if(toTokenize == "-") {
			return tokens.TOKEN_MINUS;
		}
		if(toTokenize == "*") {
			return tokens.TOKEN_MULT;
		}
		if(toTokenize == "/") {
			return tokens.TOKEN_DIV;
		}
		if(toTokenize == "%") {
			return tokens.TOKEN_MODULO;
		}
		return null;
	}
	


}
