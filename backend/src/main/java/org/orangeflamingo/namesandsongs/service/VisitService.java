package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.orangeflamingo.namesandsongs.domain.SearchInstruction;
import org.orangeflamingo.namesandsongs.domain.Visit;

public interface VisitService {

	public List<Visit> getAll();

	public Visit add(Visit visit, HttpServletRequest request);
	
	public Visit findVisit(SearchInstruction searchInstruction);

	public Object get(Integer id);
}