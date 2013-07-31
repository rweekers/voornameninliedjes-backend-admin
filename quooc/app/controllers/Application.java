package controllers;

import play.*;
import play.mvc.*;
import play.db.*;
import java.sql.*;
import play.data.*;
import models.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;
import play.db.jpa.JPA;

import views.html.*;

public class Application extends Controller {

  static Form<Song> songForm = Form.form(Song.class);

  public static Result index() {
    return redirect(routes.Application.songs());
  }

  @play.db.jpa.Transactional
  public static Result songs() {
	  return ok(
	    views.html.index.render(getAll(), songForm)
	  );
  }

  public static List<Song> getAll(){
    Query query = JPA.em().createQuery("SELECT s FROM Song s", Song.class);
    return (List<Song>) query.getResultList();
  }


    public static Result newSong() {
      return TODO;
  }

}
