package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.orangeflamingo.namesandsongs.domain.Visit;

public interface VisitService {

    public List<Visit> getAll();

    public Visit add(Visit visit, HttpServletRequest request);

    public Visit findVisit(String ipAddress, String userAgent);

    public Object get(Integer id);
}