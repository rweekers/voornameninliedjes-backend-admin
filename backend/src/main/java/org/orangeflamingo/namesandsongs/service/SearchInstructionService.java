package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.SearchInstruction;
import org.orangeflamingo.namesandsongs.domain.Visit;

public interface SearchInstructionService {

	public List<SearchInstruction> getAll();

	public void add(SearchInstruction searchInstruction, Visit visit);

	public Object get(Integer id);
}