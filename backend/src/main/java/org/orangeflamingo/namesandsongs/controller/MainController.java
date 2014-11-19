package org.orangeflamingo.namesandsongs.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.orangeflamingo.namesandsongs.domain.SearchInstruction;
import org.orangeflamingo.namesandsongs.domain.Song;
import org.orangeflamingo.namesandsongs.domain.SongOfTheDay;
import org.orangeflamingo.namesandsongs.domain.Visit;
import org.orangeflamingo.namesandsongs.service.SearchInstructionService;
import org.orangeflamingo.namesandsongs.service.SongOfTheDayService;
import org.orangeflamingo.namesandsongs.service.SongService;
import org.orangeflamingo.namesandsongs.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles and retrieves song request
 */
@Controller
@RequestMapping("api")
public class MainController {

	/**
	 * The logger
	 */
	protected static final Logger logger = Logger
			.getLogger(MainController.class);

	@Autowired
	SongService songService;

	@Autowired
	SearchInstructionService searchInstructionService;

	@Autowired
	VisitService visitService;

	@Autowired
	SongOfTheDayService songOfTheDayService;

	/**
	 * Gets a random song
	 * 
	 * @return the random song
	 */
	@RequestMapping(value = "song/random", method = RequestMethod.GET)
	@ResponseBody
	public Song randomSong() {
		return songOfTheDayService.getSongOfTheDay();
	}

	/**
	 * Gets a dummy song
	 * 
	 * @return the dummy song
	 */
	@RequestMapping(value = "song/dummy", method = RequestMethod.GET)
	@ResponseBody
	public Song dummySong() {
		Song dummy = new Song();
		dummy.setArtist("Zanger");
		dummy.setTitle("Voornaam in titel");
		dummy.setFirstname("Voornaam");
		dummy.setBackground("Testje");
		dummy.setYoutube("Geen youtube");
		return dummy;
	}

	/**
	 * Gets a random song
	 * 
	 * @return the random song
	 */
	@RequestMapping(value = "song/al", method = RequestMethod.GET)
	@ResponseBody
	public Song songAll2() {
		logger.debug("Calling dummy random song function for song/al");
		return songService.getYouCanCallMeAl();
	}

	/**
	 * Gets the max number
	 * 
	 * @return the max number of songs
	 */
	@RequestMapping(value = "admin/song/max", method = RequestMethod.GET)
	@ResponseBody
	public long maxSong() {
		return songService.getMax();
	}

	/**
	 * Finds a song by firstname
	 * 
	 * @param firstname
	 *            the firstname to search
	 * @return a list of songs with (part of) the firstname
	 */
	@RequestMapping(value = "admin/song/find", method = RequestMethod.GET)
	@ResponseBody
	public List<Song> findSongs(@RequestParam("firstname") String firstname) {
		return songService.findByFirstname(firstname);
	}

	/**
	 * Returns all songs with pagination
	 * 
	 * @return all songs
	 */
	@RequestMapping(value = "song", method = RequestMethod.GET)
	@ResponseBody
	public List<Song> allSongsLimited(
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "sortingArtist", required = false) String sortingArtist,
			@RequestParam(value = "sortingTitle", required = false) String sortingTitle,
			@RequestParam(value = "filterArtist", required = false) String filterArtist,
			@RequestParam(value = "filterTitle", required = false) String filterTitle) {
		return songService.getAll(count, page, sortingArtist, sortingTitle,
				filterArtist, filterTitle);
	}

	/**
	 * Returns the number of songs for the given filtercriteria
	 * 
	 * @return number of songs
	 */
	@RequestMapping(value = "song/count", method = RequestMethod.GET)
	@ResponseBody
	public long songCount(
			@RequestParam(value = "filterArtist", required = false) String filterArtist,
			@RequestParam(value = "filterTitle", required = false) String filterTitle) {
		return songService.getCount(filterArtist, filterTitle);
	}

	/**
	 * Returns all songs
	 * 
	 * @return all songs
	 */
	@RequestMapping(value = "admin/song", method = RequestMethod.GET)
	@ResponseBody
	public List<Song> allSongs() {
		return songService.getAll();
	}

	/**
	 * Returns all visits
	 * 
	 * @return all visits
	 */
	@RequestMapping(value = "admin/visit", method = RequestMethod.GET)
	@ResponseBody
	public List<Visit> allVisits(
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "page", required = false) Integer page) {
		logger.debug("Retrieving visits...");
		return visitService.getAll();
	}

	/**
	 * Gets a visit by id
	 * 
	 * @param id
	 *            the id of the visit
	 * @return the visit
	 */
	@RequestMapping("admin/visit/{id}")
	@ResponseBody
	public Visit getVisitById(@PathVariable int id) {
		return (Visit) visitService.get(id);
	}

	/**
	 * Gets a song by id
	 * 
	 * @param id
	 *            the id of the song
	 * @return the song
	 */
	@RequestMapping("song/{id}")
	@ResponseBody
	public Song getSongById(@PathVariable int id) {
		return (Song) songService.get(id);
	}

	/**
	 * Gets a song by id
	 * 
	 * @param id
	 *            the id of the song
	 * @return the song
	 */
	@RequestMapping("admin/song/{id}")
	@ResponseBody
	public Song getById(@PathVariable int id) {
		return (Song) songService.get(id);
	}

	@RequestMapping(value = "admin/song", method = RequestMethod.POST)
	@ResponseBody
	public void addSong(
			@RequestParam(value = "artist", required = true) String artist,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "firstname", required = true) String firstname,
			@RequestParam(value = "nameIndex", required = true) Integer nameIndex,
			@RequestParam(value = "nameLength", required = true) Integer nameLength,
			@RequestParam(value = "userInserted", required = true) String userInserted) {
		Song song = new Song();
		song.setArtist(artist);
		song.setTitle(title);
		song.setFirstname(firstname);
		song.setNameIndex(nameIndex);
		song.setNameLength(nameLength);
		song.setDateInserted(new Timestamp(System.currentTimeMillis()));
		song.setUserInserted(userInserted);
		songService.add(song);
	}

	@RequestMapping(value = "admin/song/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Song updateSong(@PathVariable int id,
			@RequestBody(required = true) Song song) {
		logger.info("Update request for admin/songs/" + song.getId()
				+ " with description: " + song);
		songService.update(song);
		return song;
	}

	/**
	 * Adds a new searchInstruction by delegating the processing to
	 * SearchInstructionService.
	 * 
	 * @param argument
	 *            the search argument
	 */
	@RequestMapping(value = "searchInstruction/add", method = RequestMethod.POST)
	@ResponseBody
	public SearchInstruction addSearchInstruction(
			@RequestParam(value = "argument") String argument,
			@RequestParam(value = "userAgent", defaultValue = "") String userAgent,
			HttpServletRequest request) {
		logger.debug("Received request to add new searchInstruction");
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		SearchInstruction searchInstruction = new SearchInstruction();
		searchInstruction.setArgument(argument);
		searchInstruction.setIpAddress(ipAddress);
		searchInstruction.setUserAgent(userAgent);
		searchInstruction.setDateInserted(new Timestamp(System
				.currentTimeMillis()));

		Visit visit = visitService.findVisit(searchInstruction);
		logger.info("Visit from country " + visit.getCity() + " and country "
				+ visit.getCountry() + " found for searchInstruction.");

		searchInstructionService.add(searchInstruction);
		return searchInstruction;
	}

	/**
	 * Adds a new visit by delegating the processing to VisitService.
	 * 
	 * @param argument
	 *            the search argument
	 */
	@RequestMapping(value = "visit/add", method = RequestMethod.POST)
	@ResponseBody
	public Visit addVisit(
			@RequestParam(value = "userAgent", defaultValue = "") String userAgent,
			HttpServletRequest request) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Visit visit = new Visit();
		visit.setIpAddress(ipAddress);
		visit.setUserAgent(userAgent);
		visit.setDateInserted(new Timestamp(System.currentTimeMillis()));
		visitService.add(visit, request);
		return visit;
	}

	/**
	 * Returns all songs
	 * 
	 * @return all songs
	 */
	@RequestMapping(value = "admin/songOfTheDay/all", method = RequestMethod.GET)
	@ResponseBody
	public List<SongOfTheDay> allSongsOfTheDay() {
		return songOfTheDayService.getAll();
	}

	/**
	 * Initializes the list with random songs
	 */
	@RequestMapping(value = "admin/songOfTheDay/initialize", method = RequestMethod.GET)
	@ResponseBody
	public void initializeSongsOfTheDay() {
		songOfTheDayService.initialize();
	}

	/**
	 * Gets all searchInstructions
	 * 
	 * @return list with all searchInstructions
	 */
	@RequestMapping(value = "admin/searchInstruction", method = RequestMethod.GET)
	@ResponseBody
	public List<SearchInstruction> allSearchInstructions() {
		return searchInstructionService.getAll();
	}

	/**
	 * Gets a searchInstruction by id
	 * 
	 * @param id
	 *            the id of the searchInstruction
	 * @return the searchInstruction
	 */
	@RequestMapping("admin/searchInstruction/{id}")
	@ResponseBody
	public SearchInstruction getSearchInstruction(@PathVariable int id) {
		return (SearchInstruction) searchInstructionService.get(id);
	}
}
