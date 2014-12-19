package org.orangeflamingo.namesandsongs.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.orangeflamingo.namesandsongs.domain.Remark;
import org.orangeflamingo.namesandsongs.domain.Song;
import org.orangeflamingo.namesandsongs.domain.View;
import org.orangeflamingo.namesandsongs.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Handles and retrieves song request
 */
@Controller
@RequestMapping("api")
public class RemarkController {

    /**
     * The LOGGER
     */
    private static final Logger LOGGER = Logger
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
    @JsonView(View.Summary.class)
    public List<Remark> allRemarks() {
        LOGGER.debug("Calling remark service...");
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
    @JsonView(View.AdminDetail.class)
    public Remark getById(@PathVariable int id) {
        LOGGER.debug("Getting remark with id " + id);
        Remark remark = (Remark) remarkService.get(id);
        LOGGER.info("Gotten remark with visitId " + remark.getVisit().getId()
                + " and songId " + remark.getSong().getId());
        return remark;
    }

    @RequestMapping(value = "admin/remark/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Remark updateRemark(@RequestBody(required = true) Remark remark) {
        LOGGER.info("Update request for admin/remark/" + remark.getId()
                + " with commentary: " + remark.getCommentary());
        remarkService.update(remark);
        return remark;
    }

    /**
     * Gets a song by remarkId
     * 
     * @param id
     *            the id of the remark
     * @return the song of the remark
     */
    @RequestMapping("admin/remark/song/{id}")
    @ResponseBody
    public Song getSongByRemarkId(@PathVariable int id) {
        LOGGER.debug("Getting song of remark with id " + id);
        return (Song) remarkService.getSong(id);
    }
}
