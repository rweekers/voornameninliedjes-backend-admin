package nl.flamingostyle.voornaaminliedje.cli;

import java.io.File;

public class App {

	public static void main(String[] argv) {

		String fileName = argv[0];
		System.out.println("Reading excel file " + fileName);

		File file = new File(fileName);
		
		ReadExcel.read(file);

	}

}