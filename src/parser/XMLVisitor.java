package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import parser.node.NodeProgramm;

public class XMLVisitor {
	
	private NodeProgramm programm = null;
	
	public XMLVisitor() {
		
	}
	
	public void setProgramm(NodeProgramm programm) {
		this.programm = programm;
	}
	
	public void createXML(String path) {
		if(programm != null) {
			String xml =  programm.visitProgramm(programm,0);
			File output = new File(path);
			try {
				PrintStream out = new PrintStream(output);
				out.println(xml);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}

}
