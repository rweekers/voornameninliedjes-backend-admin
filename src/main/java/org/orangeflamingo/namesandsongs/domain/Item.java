package org.orangeflamingo.namesandsongs.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "item")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Item implements Serializable {


	private static final long serialVersionUID = 2659137838813510971L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ View.Summary.class, View.AdminDetail.class })
	private Integer id;

	@ManyToMany
	@JoinTable(name = "item_song", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "song_id") })
	@JsonView(View.AdminDetail.class)
	private List<Song> songs;

	@Column(name = "story")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String story;

	@Column(name = "type")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String type;
	
	@Column(name = "title")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String title;

	@Column(name = "user_inserted")
	@JsonView({ View.Summary.class, View.Detail.class })
	private String userInserted;

	@Column(name = "date")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private Timestamp date;

	@Column(name = "status")
	@JsonView({ View.Summary.class, View.Detail.class, View.AdminDetail.class })
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Song> getSongs() {
		if (this.songs == null) {
			return new ArrayList<>();
		} else {
			return this.songs;
		}
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUserInserted() {
		return userInserted;
	}

	public void setUserInserted(String userInserted) {
		this.userInserted = userInserted;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return getId() + " - " + getTitle() + " - " + getStatus();
	}
}