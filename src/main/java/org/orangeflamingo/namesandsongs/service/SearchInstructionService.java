package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.SearchInstruction;

public interface SearchInstructionService {

    public List<SearchInstruction> getAll();

    public SearchInstruction add(SearchInstruction searchInstruction,
            Integer visitId);

    public Object get(Integer id);
}