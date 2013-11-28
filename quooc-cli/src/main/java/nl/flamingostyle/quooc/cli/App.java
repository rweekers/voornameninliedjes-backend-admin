package nl.flamingostyle.quooc.cli;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class App {

	public static void main(String[] argv) {

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/voornaaminliedje",
					"vil_cli", "91e6e4397bcabc7b17b17388daaa23f2");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SONG");
			while (rs.next()) {
				String artist = rs.getString("artist");
				String title = rs.getString("title");
				// System.out.println("Nummer " + title + " van artiest " +
				// artist);
			}

			Statement stmt2 = connection.createStatement();
			// stmt2.executeUpdate("insert into song (artist, title, firstname, name_index, name_length, background, date_inserted, user_inserted) values ('bla', 'diebla', 'die', 0, 3, null, current_timestamp, 'parser')");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		String fileName = argv[0];
		System.out.println("Reading excel file " + fileName);

		// Use a file
		try {
			Workbook wb = WorkbookFactory.create(new File(fileName));
			Sheet sheet = wb.getSheetAt(0);
		    for (Row row : sheet) {
		      for (Cell cell : row) {
		    	  System.out.println("Waarde is " + cell.getStringCellValue());
		      }
		    }
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}