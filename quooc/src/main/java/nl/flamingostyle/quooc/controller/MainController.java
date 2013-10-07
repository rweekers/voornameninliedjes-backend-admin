package nl.flamingostyle.quooc.controller;

import java.util.List;

import nl.flamingostyle.quooc.domain.Song;
import nl.flamingostyle.quooc.service.SongServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	protected static Logger logger = Logger.getLogger("controller");
	
	SongServiceImpl songServiceImpl;
	
	@Autowired
	public MainController(SongServiceImpl songServiceImpl){
		this.songServiceImpl = songServiceImpl;
	}
	
    @RequestMapping("song/random")
    @ResponseBody
    public Song randomSong() {
        Song song = new Song();
        song.setArtist("blabla");
        song.setTitle("LaLa Lola");
        song.setFirstname("Lola");
        return song;
    	// return songServiceImpl.getRandom();
    }
	
	/**
	 * Handles and retrieves all songs and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public String getSongs(Model model) {
    	
    	logger.debug("Received request to show all songs");
    	
    	// Retrieve all songs by delegating the call to SongServiceImpl
    	List<Song> songs = songServiceImpl.getAll();
    	
    	// Attach songs to the Model
    	model.addAttribute("songs", songs);
    	
    	// This will resolve to /WEB-INF/jsp/songspage.jsp
    	return "songspage";
	}
    
    /**
     * Retrieves the add page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/songs/add", method = RequestMethod.GET)
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
     */
    @RequestMapping(value = "/songs/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("songAttribute") Song song) {
		logger.debug("Received request to add new song");
		
    	// The "songAttribute" model has been passed to the controller from the JSP
    	// We use the name "songAttribute" because the JSP uses that name
		
		// Call SongServiceImpl to do the actual adding
		songServiceImpl.add(song);

    	// This will resolve to /WEB-INF/jsp/addedpage.jsp
		return "addedpage";
	}
    
    /**
     * Deletes an existing song by delegating the processing to SongServiceImpl.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/songs/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id, 
    										Model model) {
   
		logger.debug("Received request to delete existing song");
		
		// Call SongServiceImpl to do the actual deleting
		songServiceImpl.delete(id);
		
		// Add id reference to Model
		model.addAttribute("id", id);
    	
    	// This will resolve to /WEB-INF/jsp/deletedpage.jsp
		return "deletedpage";
	}
    
    /**
     * Retrieves the edit page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/songs/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) Integer id,  
    										Model model) {
    	logger.debug("Received request to show edit page");
    
    	// Retrieve existing Song and add to model
    	// This is the formBackingOBject
    	model.addAttribute("songAttribute", songServiceImpl.get(id));
    	
    	// This will resolve to /WEB-INF/jsp/editpage.jsp
    	return "editpage";
	}
    
    /**
     * Edits an existing song by delegating the processing to SongServiceImpl.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/songs/edit", method = RequestMethod.POST)
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
    	songServiceImpl.edit(song);
    	
    	// Add id reference to Model
		model.addAttribute("id", id);
		
    	// This will resolve to /WEB-INF/jsp/editedpage.jsp
		return "editedpage";
	}
    
}
