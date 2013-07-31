package models;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song{

  @Id
  public Long id;

  public String artist;
  public String title;
  public String surname;

  @Column(name="name_index")
  public int nameIndex;
  @Column(name="name_length")
  public int nameLength;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="date_inserted")
  public Date dateInserted;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="date_modified")
  public Date dateModified;
  @Column(name="user_inserted")
  public String userInserted;
  @Column(name="user_modified")
  public String userModified;

  public static void create(Song song) {
  }

  public static void delete(Long id) {
  }

}