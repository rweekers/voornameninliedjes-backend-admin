package nl.flamingostyle.quooc.controller;

import java.sql.Timestamp;
import java.util.List;

import nl.flamingostyle.quooc.domain.SearchInstruction;
import nl.flamingostyle.quooc.domain.Song;
import nl.flamingostyle.quooc.domain.SongOfTheDay;
import nl.flamingostyle.quooc.domain.Visit;
import nl.flamingostyle.quooc.service.SearchInstructionService;
import nl.flamingostyle.quooc.service.SongOfTheDayService;
import nl.flamingostyle.quooc.service.SongService;
import nl.flamingostyle.quooc.service.VisitService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * Gets a random song
     *
     * @return the random song
     */
    @RequestMapping(value = "song/al", method = RequestMethod.GET)
    @ResponseBody
    public Song songAll() {
        return songService.getYouCanCallMeAl();
    }

    /**
     * Gets the max number
     *
     * @return the max number of songs
     */
    @RequestMapping(value = "songs/max", method = RequestMethod.GET)
    @ResponseBody
    public long maxSong() {
        return songService.getMax();
    }

    /**
     * Finds a song by firstname
     *
     * @param firstname the firstname to search
     * @return a list of songs with (part of) the firstname
     */
    @RequestMapping(value = "song/find/{firstname}", method = RequestMethod.GET)
    @ResponseBody
    public List<Song> findSongs(@PathVariable String firstname) {
        return songService.findByFirstname(firstname);
    }

    /**
     * Returns all songs
     *
     * @return all songs
     */
    @RequestMapping(value = "songs/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Song> allSongs() {
        return songService.getAll();
    }

    /**
     * Returns all songs with offset and max
     *
     * @param max the max number of songs
     * @param offset the offset
     * @return a list of songs according to offset and max
     */
    @RequestMapping(value = "songs/some", method = RequestMethod.GET)
    @ResponseBody
    public List<Song> allSongsPagination(@RequestParam("max") int max, @RequestParam("offset") int offset) {
        return songService.getAllPagination(max, offset);
    }

    /**
     * Handles and retrieves all songs and show it in a JSP page
     *
     * @return the name of the JSP page
     * @param model the model
     */
    @RequestMapping(value = "songs", method = RequestMethod.GET)
    @ResponseBody
    public String getSongs(Model model) {

        logger.debug("Received request to show all songs");

        // Retrieve all songs by delegating the call to SongServiceImpl
        List<Song> songs = songService.getAll();

        // Attach songs to the Model
        model.addAttribute("songs", songs);

        // This will resolve to /WEB-INF/view/songs.jsp
        // return "songs";
        return "songs.html";
    }

    /**
     * Gets a song by id
     *
     * @param id the id of the song
     * @return the song
     */
    @RequestMapping("song/{id}")
    @ResponseBody
    public Song getById(@PathVariable int id) {
        return (Song) songService.get(id);
    }

    /**
     * Adds a new searchInstruction by delegating the processing to
     * SearchInstructionService.
     *
     * @param argument the search argument
     */
    @RequestMapping(value = "searchInstruction/add", method = RequestMethod.GET)
    @ResponseBody
    public void addSearchInstruction(@RequestParam(value = "argument") String argument, @RequestParam(value = "ipAddress", defaultValue = "") String ipAddress) {
        logger.debug("Received request to add new searchInstruction");
        SearchInstruction searchInstruction = new SearchInstruction();
        searchInstruction.setArgument(argument);
        searchInstruction.setIpAddress(ipAddress);
        searchInstruction.setDateInserted(new Timestamp(System.currentTimeMillis()));
        searchInstructionService.add(searchInstruction);
    }
    
      /**
     * Adds a new visit by delegating the processing to
     * VisitService.
     *
     * @param argument the search argument
     */
    @RequestMapping(value = "visit/add", method = RequestMethod.GET)
    @ResponseBody
    public void addVisit(@RequestParam(value = "browser", defaultValue = "") String browser, @RequestParam(value = "ipAddress", defaultValue = "") String ipAddress, @RequestParam(value = "country", defaultValue = "") String country, @RequestParam(value = "city", defaultValue = "") String city, @RequestParam(value = "operatingSystem", defaultValue = "") String operatingSystem) {
        logger.debug("Received request to add new searchInstruction");
        Visit visit = new Visit();
        visit.setBrowser(browser);
        visit.setIpAddress(ipAddress);
        visit.setCountry(country);
        visit.setCity(city);
        visit.setOperatingSystem(operatingSystem);
        visit.setDateInserted(new Timestamp(System.currentTimeMillis()));
        visitService.add(visit);
    }
    
    /**
     * Returns all songs
     *
     * @return all songs
     */
    @RequestMapping(value = "songsOfTheDay/all", method = RequestMethod.GET)
    @ResponseBody
    public List<SongOfTheDay> allSongsOfTheDay() {
        return songOfTheDayService.getAll();
    }
    
    /**
     * Initializes the list with random songs
     */
    @RequestMapping(value = "songsOfTheDay/initialize", method = RequestMethod.GET)
    @ResponseBody
    public void initializeSongsOfTheDay() {
    	songOfTheDayService.initialize();
    }
}
