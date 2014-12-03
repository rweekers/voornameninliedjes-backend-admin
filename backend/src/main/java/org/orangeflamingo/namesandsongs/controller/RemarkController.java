package org.orangeflamingo.namesandsongs.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.orangeflamingo.namesandsongs.domain.Remark;
import org.orangeflamingo.namesandsongs.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles and retrieves song request
 */
@Controller
@RequestMapping("api")
public class RemarkController {

	/**
	 * The logger
	 */
	protected static final Logger logger = Logger
			.getLogger(RemarkController.class);

	@Autowired
	RemarkService remarkService;

	/**
	 * Returns all remarks
	 * 
	 * @return all remarks
	 */
	@RequestMapping(value = "admin/remark", method = RequestMethod.GET)
	@ResponseBody
	public List<Remark> allRemarks() {
		logger.info("Calling remark service...");
		return remarkService.getAll();
	}
	
	/**
	 * Gets a remark by id
	 * 
	 * @param id
	 *            the id of the remark
	 * @return the remark
	 */
	@RequestMapping("admin/remark/{id}")
	@ResponseBody
	public Remark getById(@PathVariable int id) {
		return (Remark) remarkService.get(id);
	}
}
