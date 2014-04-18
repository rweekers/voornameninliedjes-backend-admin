package org.orangeflamingo.voornaaminliedje.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * For a complete reference see <a href=
 * "http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/"
 * > Hibernate Annotations Communit Documentations</a>
 */
@Entity
@Table(name = "song")
public class Song implements Serializable {

	private static final long serialVersionUID = -5527566248002296042L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "artist")
	private String artist;

	@Column(name = "title")
	private String title;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "name_index")
	private Integer nameIndex;

	@Column(name = "name_length")
	private Integer nameLength;

	@Column(name = "date_inserted")
	private Timestamp dateInserted;

	@Column(name = "user_inserted")
	private String userInserted;

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

	public String getUserInserted() {
		return userInserted;
	}

	public void setUserInserted(String userInserted) {
		this.userInserted = userInserted;
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
}
