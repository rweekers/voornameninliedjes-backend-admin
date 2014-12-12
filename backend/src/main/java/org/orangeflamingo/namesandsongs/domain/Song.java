package org.orangeflamingo.namesandsongs.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * For a complete reference see <a href=
 * "http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/"
 * > Hibernate Annotations Communit Documentations</a>
 */
@Entity
@Table(name = "song")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Song implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Summary.class)
    private Integer id;

    @OneToMany(mappedBy = "song")
    private List<Remark> remarks;

    @Column(name = "artist")
    @JsonView(View.Summary.class)
    private String artist;

    @Column(name = "title")
    @JsonView(View.Summary.class)
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
    private String background;

    @Column(name = "youtube")
    private String youtube;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
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

    public String toString() {
        return getArtist() + " - " + getTitle() + " - " + getFirstname();
    }
}
