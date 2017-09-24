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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    public Integer getNameLength() {
        return nameLength;
    }

    public void setNameLength(Integer nameLength) {
        this.nameLength = nameLength;
    }

    public Timestamp getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Timestamp dateInserted) {
        this.dateInserted = dateInserted;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public String getUserInserted() {
        return userInserted;
    }

    public void setUserInserted(String userInserted) {
        this.userInserted = userInserted;
    }

    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Song s = (Song) o;
        return this.id == s.getId() && this.artist.equals(s.getArtist())
                && this.title.equals(s.getTitle());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return getArtist() + " - " + getTitle() + " - " + getFirstname();
    }
}