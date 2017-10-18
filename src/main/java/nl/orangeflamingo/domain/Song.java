package nl.orangeflamingo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents a song in the classic way (artist and title).
 * When a song has been released multiple times in the same version from the same artist
 * this will not affect this (as it will still result in only one song)
 */
@Entity
@Table(name = "song")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Song implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
    private Long id;

    @Column(name = "artist")
    @JsonView({ View.Summary.class, View.Detail.class })
    private String artist;

    @Column(name = "title")
    @JsonView({ View.Summary.class, View.Detail.class })
    private String title;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "name_index")
    private Integer nameIndex;

    @Column(name = "name_length")
    private Integer nameLength;

    @Column(name = "date_inserted")
    private Timestamp dateInserted;

    @Column(name = "date_modified")
    private Timestamp dateModified;

    @Column(name = "user_inserted")
    private String userInserted;

    @Column(name = "user_modified")
    private String userModified;

    @Column(name = "background")
    @JsonView({ View.Summary.class, View.Detail.class })
    private String background;

    @Column(name = "youtube")
    @JsonView({ View.Summary.class, View.Detail.class })
    private String youtube;

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }
}