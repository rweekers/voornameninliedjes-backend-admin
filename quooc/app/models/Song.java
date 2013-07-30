package models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Song {

  @Id
  public Long id;

  public String artist;
  public String title;
  public String surname;
  public int nameIndex;
  public int nameLength;

  @Temporal(TemporalType.TIMESTAMP)
  public Date dateInserted;

  @Temporal(TemporalType.TIMESTAMP)
  public Date dateModified;

  public String userInserted;
  public String userModified;

  public static List<Song> all() {
    return new ArrayList<Song>();
  }

  public static void create(Song song) {
  }

  public static void delete(Long id) {
  }

}