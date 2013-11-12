package nl.flamingostyle.quooc.service;

import java.util.List;
import nl.flamingostyle.quooc.domain.Visit;

public interface VisitService {

	public List<Visit> getAll();

	public void add(Visit visit);

	public Object get(Integer id);
}