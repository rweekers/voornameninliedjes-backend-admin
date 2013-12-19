package nl.flamingostyle.voornaaminliedje.service;

import java.util.List;

import nl.flamingostyle.voornaaminliedje.domain.Visit;

public interface VisitService {

	public List<Visit> getAll();

	public void add(Visit visit);

	public Object get(Integer id);
}