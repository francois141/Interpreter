import IO.FileReader;

public class main {

	public static void main(String[] args) {
		FileReader input = new FileReader("ebnf.txt");
		System.out.println(input.read());
		System.out.println(input);

	}

}
