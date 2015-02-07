package org.orangeflamingo.namesandsongs.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.orangeflamingo.namesandsongs.domain.Suggestion;
import org.orangeflamingo.namesandsongs.domain.View;
import org.orangeflamingo.namesandsongs.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Handles and retrieves song request
 */
@Controller
@RequestMapping("api")
public class SuggestionController {

	/**
	 * The LOGGER
	 */
	private static final Logger LOGGER = Logger
			.getLogger(SuggestionController.class);

	@Autowired
	SuggestionService suggestionService;

	/**
	 * Returns all suggestion
	 * 
	 * @return all suggestion
	 */
	@RequestMapping(value = "admin/suggestion", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Summary.class)
	public List<Suggestion> allSuggestions() {
		LOGGER.debug("Calling suggestion service...");
		return suggestionService.getAll();
	}

	/**
	 * Gets a suggestion by id
	 * 
	 * @param id
	 *            the id of the suggestion
	 * @return the suggestion
	 */
	@RequestMapping("admin/suggestion/{id}")
	@ResponseBody
	@JsonView(View.AdminDetail.class)
	public Suggestion getById(@PathVariable int id) {
		LOGGER.debug("Getting suggestion with id " + id);
		return (Suggestion) suggestionService.get(id);
	}

	@RequestMapping(value = "admin/suggestion/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Suggestion updateSuggestion(
			@RequestBody(required = true) Suggestion suggestion,
			@RequestParam(value = "songId", required = false) Integer songId) {
		LOGGER.info("Update request for admin/suggestion/" + suggestion.getId()
				+ " with commentary: " + suggestion.getComment());
		suggestionService.update(suggestion, songId);
		return suggestion;
	}

}
