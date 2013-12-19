package nl.flamingostyle.voornaaminliedje.service;

import java.util.List;

import nl.flamingostyle.voornaaminliedje.domain.SearchInstruction;

public interface SearchInstructionService {

	public List<SearchInstruction> getAll();

	public void add(SearchInstruction searchInstruction);

	public Object get(Integer id);
}