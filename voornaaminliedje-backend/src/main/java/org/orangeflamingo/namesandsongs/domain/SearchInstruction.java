package org.orangeflamingo.namesandsongs.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * For a complete reference see
 * <a
 * href="http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/">
 * Hibernate Annotations Communit Documentations</a>
 */
@Entity
@Table(name = "searchInstruction")
public class SearchInstruction implements Serializable {

    private static final long serialVersionUID = -7149957706738879274L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(name = "argument")
    private String argument;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "date_inserted")
    private Timestamp dateInserted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Timestamp getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Timestamp dateInserted) {
        this.dateInserted = dateInserted;
    }
}
