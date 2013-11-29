package nl.flamingostyle.quooc.cli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

	public static void write(Song song) {

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/voornaaminliedje",
					"vil_cli", "91e6e4397bcabc7b17b17388daaa23f2");
			Statement stmt = connection.createStatement();

			System.out.println("Opslaan van nummer " + song.getTitle()
					+ " van " + song.getArtist() + " met voornaam "
					+ song.getFirstname() + " op " + song.getNameIndex()
					+ " met lengte " + song.getNameLength()
					+ " met achtergrond " + song.getBackground()
					+ " aangemeld door " + song.getReporter());

			String insert = new String(
					"insert into song (artist, title, firstname, name_index, name_length, background, date_inserted, user_inserted)"
							+ "values ('"
							+ song.getArtist()
							+ "', '"
							+ song.getTitle()
							+ "', '"
							+ song.getFirstname()
							+ "', "
							+ song.getNameIndex()
							+ ","
							+ song.getNameLength()
							+ ",'"
							+ song.getBackground()
							+ "',current_timestamp,'"
							+ song.getReporter() + "');");
			
			System.out.println(insert);
			
			stmt.execute(insert);
			
			// TODO Search last SongOfTheDay and add one with day+1

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

	}

}
