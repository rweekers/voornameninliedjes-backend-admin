package org.orangeflamingo.namesandsongs.controller;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.orangeflamingo.namesandsongs.domain.Item;
import org.orangeflamingo.namesandsongs.domain.View;
import org.orangeflamingo.namesandsongs.service.ItemService;
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
public class ItemController {

	/**
	 * The LOGGER
	 */
	private static final Logger LOGGER = Logger
			.getLogger(ItemController.class);

	@Autowired
	ItemService itemService;

	/**
	 * Returns all items
	 * 
	 * @return all items
	 */
	@RequestMapping(value = "admin/item", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Summary.class)
	public List<Item> allItems() {
		LOGGER.debug("Calling itemService...");
		return itemService.getAll();
	}

	/**
	 * Gets an item by id
	 * 
	 * @param id
	 *            the id of the item
	 * @return the item
	 */
	@RequestMapping("admin/item/{id}")
	@ResponseBody
	@JsonView(View.AdminDetail.class)
	public Item getById(@PathVariable int id) {
		LOGGER.debug("Getting item with id " + id);
		return (Item) itemService.get(id);
	}

	@RequestMapping(value = "admin/item/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Item updateItem(
			@RequestBody(required = true) Item item,
			@RequestParam(value = "songId", required = false) Integer songId) {
		LOGGER.info("Update request for admin/item/" + item.getId()
				+ " with title: " + item.getTitle());
		itemService.update(item, songId);
		return item;
	}
	
	@RequestMapping(value = "admin/itemSong/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Item removeSongFromItem(
			@RequestBody(required = true) Item item,
			@RequestParam(value = "songId", required = true) Integer songId) {
		LOGGER.info("Update request for admin/itemSong/" + item.getId()
				+ " with title: " + item.getTitle());
		itemService.removeSong(item, songId);
		return item;
	}
	
	/**
	 * Saves a new item
	 * 
	 * @param item
	 *            the item to be saved
	 * @return the item
	 */
	@RequestMapping(value = "admin/item", method = RequestMethod.POST)
	@ResponseBody
	public Item addItem(@RequestBody(required = true) Item item) {
		LOGGER.info("Saving item " + item.getTitle() + " from user " + item.getUserInserted());
		item.setDate(new Timestamp(System.currentTimeMillis()));
		return itemService.addItem(item);
	}

}
