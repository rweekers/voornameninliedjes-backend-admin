package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.SearchInstruction;
import org.orangeflamingo.namesandsongs.domain.Visit;

public interface SearchInstructionService {

	public List<SearchInstruction> getAll();

	public SearchInstruction add(SearchInstruction searchInstruction, Integer visitId);

	public Object get(Integer id);
}