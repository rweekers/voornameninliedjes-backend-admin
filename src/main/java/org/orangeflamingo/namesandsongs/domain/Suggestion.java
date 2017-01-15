package org.orangeflamingo.namesandsongs.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * For a complete reference see <a href=
 * "http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/"
 * > Hibernate Annotations Communit Documentations</a>
 */
@Entity
@Table(name = "suggestion")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Suggestion implements Serializable {

	private static final long serialVersionUID = 686856611627570487L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ View.Summary.class, View.AdminDetail.class })
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "visit_id")
	@JsonView(View.AdminDetail.class)
	private Visit visit;

	@ManyToOne
	@JoinColumn(name = "song_id")
	@JsonView(View.AdminDetail.class)
	private Song song;

	@Column(name = "artist")
	@JsonView({ View.Summary.class, View.Detail.class })
	private String artist;

	@Column(name = "title")
	@JsonView({ View.Summary.class, View.Detail.class })
	private String title;

	@Column(name = "background")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String background;

	@Column(name = "youtube")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String youtube;

	@Column(name = "comment")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String comment;

	@Column(name = "email")
	@JsonView({ View.Summary.class, View.Detail.class })
	private String email;

	@Column(name = "observer")
	@JsonView({ View.Summary.class, View.Detail.class })
	private String observer;

	@Column(name = "date")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private Timestamp date;

	@Column(name = "response")
	@JsonView({ View.Detail.class, View.AdminDetail.class })
	private String response;

	@Column(name = "responsedate")
	@JsonView({ View.Detail.class, View.AdminDetail.class })
	private Timestamp responsedate;

	@Column(name = "status")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return observer;
	}

	public void setUser(String user) {
		this.observer = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Timestamp getResponsedate() {
		return responsedate;
	}

	public void setResponsedate(Timestamp responsedate) {
		this.responsedate = responsedate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return getId() + " - " + getComment() + " - " + getStatus();
	}
}