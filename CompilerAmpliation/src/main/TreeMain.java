package main;

import java.io.FileReader;
import java.io.IOException;

import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import parser.Parser;
import scanner.Scanner;

public class TreeMain {

    public static void main(String args[]) throws IOException {
	if (args.length < 1) {
	    System.err.println("Pass me the name of the input file.");
	    return;
	}

	FileReader fr = null;
	try {
	    fr = new FileReader(args[0]);
	} catch (IOException io) {
	    System.err.println("The file " + args[0] + " could not be opened.");
	    return;
	}

	Scanner scanner = new Scanner(fr);
	Parser parser = new Parser(scanner);
	parser.run();
	
	IntrospectorModel modelo = new IntrospectorModel("Program", parser.getAST());
	new IntrospectorTree("Introspector", modelo);
    }
}