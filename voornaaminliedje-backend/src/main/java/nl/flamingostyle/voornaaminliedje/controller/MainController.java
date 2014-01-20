package nl.flamingostyle.voornaaminliedje.controller;

import java.sql.Timestamp;
import java.util.List;

import nl.flamingostyle.voornaaminliedje.domain.SearchInstruction;
import nl.flamingostyle.voornaaminliedje.domain.Song;
import nl.flamingostyle.voornaaminliedje.domain.SongOfTheDay;
import nl.flamingostyle.voornaaminliedje.domain.Visit;
import nl.flamingostyle.voornaaminliedje.service.SearchInstructionService;
import nl.flamingostyle.voornaaminliedje.service.SongOfTheDayService;
import nl.flamingostyle.voornaaminliedje.service.SongService;
import nl.flamingostyle.voornaaminliedje.service.VisitService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	protected static final Logger logger = Logger.getLogger("controller");

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
	 * Returns all songs
	 * 
	 * @return all songs
	 */
	@RequestMapping(value = "song/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Song> allSongs(
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
	 * Returns all visits
	 * 
	 * @return all visits
	 */
	@RequestMapping(value = "admin/visit/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Visit> allSongs(
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "page", required = false) Integer page) {
		return visitService.getAll();
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
	
	@RequestMapping("song/add")
	@ResponseBody
	public void addSong(Song song){
		songService.add(song);
	}

	/**
	 * Adds a new searchInstruction by delegating the processing to
	 * SearchInstructionService.
	 * 
	 * @param argument
	 *            the search argument
	 */
	@RequestMapping(value = "admin/searchInstruction/add", method = RequestMethod.GET)
	@ResponseBody
	public void addSearchInstruction(
			@RequestParam(value = "argument") String argument,
			@RequestParam(value = "ipAddress", defaultValue = "") String ipAddress) {
		logger.debug("Received request to add new searchInstruction");
		SearchInstruction searchInstruction = new SearchInstruction();
		searchInstruction.setArgument(argument);
		searchInstruction.setIpAddress(ipAddress);
		searchInstruction.setDateInserted(new Timestamp(System
				.currentTimeMillis()));
		searchInstructionService.add(searchInstruction);
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
			@RequestParam(value = "browser", defaultValue = "") String browser,
			@RequestParam(value = "ipAddress", defaultValue = "") String ipAddress,
			@RequestParam(value = "country", defaultValue = "") String country,
			@RequestParam(value = "city", defaultValue = "") String city,
			@RequestParam(value = "operatingSystem", defaultValue = "") String operatingSystem) {
		logger.debug("Received request to add new visit");
		Visit visit = new Visit();
		visit.setBrowser(browser);
		visit.setIpAddress(ipAddress);
		visit.setCountry(country);
		visit.setCity(city);
		visit.setOperatingSystem(operatingSystem);
		visit.setDateInserted(new Timestamp(System.currentTimeMillis()));
		visitService.add(visit);
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
	 * Gets all visits
	 * 
	 * @return list with all visits
	 */
	/*
	 * @RequestMapping(value = "admin/visit/all", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List<Visit> allVisits() { return
	 * visitService.getAll(); }
	 */

	/**
	 * Gets all searchInstructions
	 * 
	 * @return list with all searchInstructions
	 */
	@RequestMapping(value = "admin/searchInstruction/all", method = RequestMethod.GET)
	@ResponseBody
	public List<SearchInstruction> allSearchInstructions() {
		return searchInstructionService.getAll();
	}
}
