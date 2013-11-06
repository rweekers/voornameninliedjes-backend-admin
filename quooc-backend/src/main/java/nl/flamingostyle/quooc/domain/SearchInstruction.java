package nl.flamingostyle.quooc.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * For a complete reference see
 * <a
 * href="http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/">
 * Hibernate Annotations Communit Documentations</a>
 */
@Entity
@Table(name = "search")
public class SearchInstruction implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "argument")
    private String argument;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "country")
    private String country;

    @Column(name = "browser")
    private String browser;

    @Column(name = "operatingSysttem")
    private String operatingSystem;

    @Column(name = "date_inserted")
    private Date dateInserted;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Date getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Date dateInserted) {
        this.dateInserted = dateInserted;
    }
}
