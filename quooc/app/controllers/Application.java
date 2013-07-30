package controllers;

import play.*;
import play.mvc.*;
import play.db.*;
import java.sql.*;
import play.data.*;
import models.*;

import views.html.*;

public class Application extends Controller {

  static Form<Song> songForm = form(Song.class);

  public static Result index() {
    return redirect(routes.Application.songs());
  }

    public static Result songs() {
	  return ok(
	    views.html.index.render(Song.all(), songForm)
	  );
}

    public static Result newSong() {
      return TODO;
  }

}