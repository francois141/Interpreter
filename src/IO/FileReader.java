package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	
	public String text;
	public File fileInput;
	public String path;
	
	public FileReader(String path) {
		this.path = path;
		fileInput = new File(path);
	}
	
	public String read(){
		
		text = "";
		
		Scanner input;
		try {
			input = new Scanner(fileInput);
			while(input.hasNextLine()) {
				text += input.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return text;
	}

	public String toString() {
		return "FileReader Class \nCurrent path is : " + path;
	}
}
