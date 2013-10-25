package nl.flamingostyle.quooc.controller;

import java.util.List;

import nl.flamingostyle.quooc.domain.Song;
import nl.flamingostyle.quooc.service.SongService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        /**
         * Gets a random song
         * 
         * @return the random song
         */
    @RequestMapping(value = "song/random", method = RequestMethod.GET)
    @ResponseBody
    public Song randomSong() {
    	return songService.getRandom();
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
		return (Song)songService.get(id);
	}
    
    /**
     * Retrieves the add page
     * 
     * @return the name of the JSP page
     * @param model the model
     */
    @RequestMapping(value = "songs/add", method = RequestMethod.GET)
    @ResponseBody
    public String getAdd(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Create new Song and add to model
    	// This is the formBackingOBject
    	model.addAttribute("songAttribute", new Song());

    	// This will resolve to /WEB-INF/jsp/addpage.jsp
    	return "addpage";
	}
 
    /**
     * Adds a new song by delegating the processing to SongServiceImpl.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     * @param song the song
     */
    @RequestMapping(value = "songs/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute("songAttribute") Song song) {
		logger.debug("Received request to add new song");
		
    	// The "songAttribute" model has been passed to the controller from the JSP
    	// We use the name "songAttribute" because the JSP uses that name
		
		// Call SongServiceImpl to do the actual adding
		songService.add(song);

    	// This will resolve to /WEB-INF/jsp/addedpage.jsp
		return "addedpage";
	}
    
    /**
     * Deletes an existing song by delegating the processing to SongServiceImpl.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     * @param id the id
     * @param model the model
     */
    @RequestMapping(value = "songs/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam(value="id", required=true) Integer id, 
    										Model model) {
   
		logger.debug("Received request to delete existing song");
		
		// Call SongServiceImpl to do the actual deleting
		songService.delete(id);
		
		// Add id reference to Model
		model.addAttribute("id", id);
    	
    	// This will resolve to /WEB-INF/jsp/deletedpage.jsp
		return "deletedpage";
	}
    
    /**
     * Retrieves the edit page
     * 
     * @return the name of the JSP page
     * @param id the id
     * @param model the model
     */
    @RequestMapping(value = "songs/edit", method = RequestMethod.GET)
    @ResponseBody
    public String getEdit(@RequestParam(value="id", required=true) Integer id,  
    										Model model) {
    	logger.debug("Received request to show edit page");
    
    	// Retrieve existing Song and add to model
    	// This is the formBackingOBject
    	model.addAttribute("songAttribute", songService.get(id));
    	
    	// This will resolve to /WEB-INF/jsp/editpage.jsp
    	return "editpage";
	}
    
    /**
     * Edits an existing song by delegating the processing to SongServiceImpl.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     * @param song the song
     * @param id the id
     * @param model the model
     */
    @RequestMapping(value = "songs/edit", method = RequestMethod.POST)
    @ResponseBody
    public String saveEdit(@ModelAttribute("songAttribute") Song song, 
    										   @RequestParam(value="id", required=true) Integer id, 
    												Model model) {
    	logger.debug("Received request to update song");
    
    	// The "songAttribute" model has been passed to the controller from the JSP
    	// We use the name "songAttribute" because the JSP uses that name
    	
    	// We manually assign the id because we disabled it in the JSP page
    	// When a field is disabled it will not be included in the ModelAttribute
    	song.setId(id);
    	
    	// Delegate to SongServiceImpl for editing
    	songService.edit(song);
    	
    	// Add id reference to Model
		model.addAttribute("id", id);
		
    	// This will resolve to /WEB-INF/jsp/editedpage.jsp
		return "editedpage";
	}
    
}
