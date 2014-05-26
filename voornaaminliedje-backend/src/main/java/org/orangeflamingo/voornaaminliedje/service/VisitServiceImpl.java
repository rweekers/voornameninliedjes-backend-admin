package org.orangeflamingo.voornaaminliedje.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.voornaaminliedje.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

/**
 * Service for processing Songs
 * 
 */
@Service
@Transactional
public class VisitServiceImpl implements VisitService {

	protected static final Logger logger = Logger.getLogger(VisitServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Retrieves all visits
	 * 
	 * @return a list of visits
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Visit> getAll() {
		logger.info("Retrieving all visits");

		// Create a Hibernate query (HQL)
		Query query = getCurrentSession().createQuery(
				"FROM  Visit visit order by visit.id desc");

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single visit
	 * 
	 * @return the visit
	 * @param id
	 *            the id of the visit
	 */
	public Visit get(Integer id) {
		// Retrieve existing visit first
		logger.debug("Calling get with the id " + id);
		Visit visit = (Visit) getCurrentSession().get(Visit.class, id);
		logger.debug("Gotten visit " + visit.getIpAddress());
		return visit;
	}

	/**
	 * Adds a new visit
	 * 
	 * @param visit
	 *            the visit to add
	 */
	public void add(Visit visit, HttpServletRequest request) {
		logger.debug("Adding new visit");
		UserAgentStringParser parser = UADetectorServiceFactory
				.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse(visit.getUserAgent());
		visit.setBrowser(agent.getProducer() + " " + agent.getName() + " "
				+ agent.getVersionNumber().toVersionString());
		visit.setOperatingSystem(agent.getOperatingSystem().getProducer() + " "
				+ agent.getOperatingSystem().getName());
		location(visit, request);
		// Retrieve session from Hibernate
		Session session = getCurrentSession();
		// Save
		session.save(visit);
	}

	private void location(Visit visit, HttpServletRequest request) {
		// A File object pointing to your GeoIP2 or GeoLite2 database
		File database = new File(request.getServletContext().getRealPath("/")
				+ "GeoLite2-City.mmdb");

		// This creates the DatabaseReader object, which should be reused across
		// lookups.
		DatabaseReader reader;
		try {
			reader = new DatabaseReader.Builder(database).build();
			// Replace "city" with the appropriate method for your database,
			// e.g.,
			// "country".
			// CityResponse response =
			// reader.city(InetAddress.getByName("128.101.101.101"));
			logger.info("Het ipadres is " + visit.getIpAddress());
			CityResponse response;
			if (visit.getIpAddress().equals("127.0.0.1")) {
				response = reader
						.city(InetAddress.getByName("128.101.101.101"));
				logger.error("Ipaddres bepaling mislukt! Voor positiebepaling dummy 128.101.101.101 genomen.");
			} else {
				response = reader.city(InetAddress.getByName(visit
						.getIpAddress()));
			}

			visit.setCountryCode(response.getCountry().getIsoCode());
			visit.setCountry(response.getCountry().getName());
			visit.setCity(response.getCity().getName());
			visit.setLatitude(new BigDecimal(response.getLocation()
					.getLatitude()));
			visit.setLongitude(new BigDecimal(response.getLocation()
					.getLongitude()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IO voor GeoIp2Location mislukt " + e.getMessage());
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			logger.error("GeoIp2Location mislukt " + e.getMessage());
			e.printStackTrace();
		}

	}
}
