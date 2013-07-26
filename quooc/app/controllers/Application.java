package controllers;

import play.*;
import play.mvc.*;
import play.db.*;
import java.sql.*;

import views.html.*;

public class Application extends Controller {

  public static Result index() {
    System.out.println("Blablabla3");
    try {
      Connection connection = DB.getConnection();
      Statement statement = connection.createStatement();

      String query = "select artist, title from songs";
      ResultSet rs = statement.executeQuery(query);
      while(rs.next()){
	  	  String artist = rs.getString("artist");
		  String title = rs.getString("title");
		  System.out.println(artist + " - " + title);
	  }

	  connection.close();

    } catch (SQLException se) {
			System.out.println(se.getMessage());
	}

    return ok(index.render("Your new application is ready."));
  }

}