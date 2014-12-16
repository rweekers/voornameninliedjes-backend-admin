package org.orangeflamingo.namesandsongs.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "remark")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Remark implements Serializable {

    private static final long serialVersionUID = 3031153761604281591L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.Summary.class, View.Detail.class})
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @ManyToOne
    @JoinColumn(name = "song_id")
    @JsonView(View.Detail.class)
    private Song song;

    @Column(name = "background")
    @JsonView({View.Summary.class, View.Detail.class})
    private String background;

    @Column(name = "youtube")
    @JsonView({View.Summary.class, View.Detail.class})
    private String youtube;

    @Column(name = "commentary")
    @JsonView({View.Summary.class, View.Detail.class})
    private String commentary;

    @Column(name = "email")
    @JsonView({View.Summary.class, View.Detail.class})
    private String email;

    @Column(name = "observer")
    @JsonView({View.Summary.class, View.Detail.class})
    private String observer;

    @Column(name = "date")
    @JsonView({View.Summary.class, View.Detail.class})
    private Timestamp date;

    @Column(name = "status")
    @JsonView({View.Summary.class, View.Detail.class})
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

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String toString() {
        return getId() + " - " + getCommentary() + " - " + getStatus();
    }

}
