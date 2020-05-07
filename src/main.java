import java.util.List;

import IO.FileReader;
import lexer.Lexer;
import lexer.Token;

public class main {

	public static void main(String[] args) {
		
		FileReader input = new FileReader("input.txt");
		String text = input.read();
		Lexer lex = new Lexer(text);
		List<Token> list = lex.toTokens();
		System.out.println(list);
	}

}
