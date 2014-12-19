package org.orangeflamingo.namesandsongs.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "visit")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Visit implements Serializable {

    private static final long serialVersionUID = -7149957706738879275L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.AdminDetail.class)
    private Integer id;

    @OneToMany(mappedBy = "visit")
    private List<SearchInstruction> searchInstructions;

    @OneToMany(mappedBy = "visit")
    private List<Remark> remarks;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "countryCode")
    private String countryCode;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "browser")
    private String browser;

    @Column(name = "operatingSystem")
    private String operatingSystem;

    @Column(name = "userAgent")
    private String userAgent;

    @Column(precision = 10, scale = 4)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 4)
    private BigDecimal longitude;

    @Column(name = "date_inserted")
    private Timestamp dateInserted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SearchInstruction> getSearchInstructions() {
        return searchInstructions;
    }

    public void setSearchInstructions(List<SearchInstruction> searchInstructions) {
        this.searchInstructions = searchInstructions;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Timestamp getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Timestamp dateInserted) {
        this.dateInserted = dateInserted;
    }
}
