package org.orangeflamingo.namesandsongs.service;

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
import org.orangeflamingo.namesandsongs.domain.Visit;
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

	private static final Logger LOGGER = Logger
			.getLogger(VisitServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PropertiesService propertiesService;

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
	public List<Visit> getAll(Integer count) {
		LOGGER.debug("Retrieving all visits");
		Session session = sessionFactory.openSession();

		// Create a Hibernate query (HQL)
		Query query = session
				.createQuery("FROM  Visit visit order by visit.id desc");

		if (count != null) {
			query.setMaxResults(count);
		}

		// Retrieve visits
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
		LOGGER.debug("Calling get with the id " + id);
		Session session = sessionFactory.openSession();
		Visit visit = (Visit) session.get(Visit.class, id);
		LOGGER.debug("Gotten visit " + visit.getIpAddress());
		return visit;
	}

	/**
	 * Adds a new visit
	 * 
	 * @param visit
	 *            the visit to add
	 */
	public Visit add(Visit visit, HttpServletRequest request) {
		LOGGER.debug("Adding new visit " + visit.getUserAgent());
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
		return visit;
	}

	@SuppressWarnings("unchecked")
	public Visit findVisit(String ipAddress, String userAgent) {
		String queryString = "FROM  Visit visit WHERE 1 = 1 ";

		queryString = queryString + " AND visit.ipAddress = :ipAddress";
		queryString = queryString + " AND visit.userAgent = :userAgent";

		queryString = queryString + " order by visit.id desc";

		// Create a Hibernate query (HQL)
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryString);
		query.setParameter("ipAddress", ipAddress);
		query.setParameter("userAgent", userAgent);
		query.setMaxResults(1);

		// Retrieve Visit
		List<Visit> visits = query.list();
		if (visits.size() > 0) {
			return visits.get(0);
		} else {
			return null;
		}

	}

	private void location(Visit visit, HttpServletRequest request) {
		// A File object pointing to your GeoIP2 or GeoLite2 database
		File database = new File(request.getServletContext().getRealPath("/")
				+ "/GeoLite2-City.mmdb");

		// This creates the DatabaseReader object, which should be reused across
		// lookups.
		DatabaseReader reader;
		try {
			reader = new DatabaseReader.Builder(database).build();
			LOGGER.debug("Het ipadres is " + visit.getIpAddress());
			CityResponse response;

			if (visit.getIpAddress().equals(propertiesService.get("localhost"))) {
				response = reader.city(InetAddress.getByName(propertiesService
						.get("google")));
				LOGGER.error("Ipaddres bepaling mislukt! Voor positiebepaling dummy "
						+ propertiesService.get("google") + " genomen.");
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
			LOGGER.error("IO voor GeoIp2Location mislukt " + e.getMessage());
		} catch (GeoIp2Exception e) {
			LOGGER.error("GeoIp2Location mislukt " + e.getMessage());
		}

	}
}
