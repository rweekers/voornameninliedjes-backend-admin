package nl.flamingostyle.voornaaminliedje.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nl.flamingostyle.voornaaminliedje.domain.Visit;

public interface VisitService {

	public List<Visit> getAll();

	public void add(Visit visit, HttpServletRequest request);

	public Object get(Integer id);
}