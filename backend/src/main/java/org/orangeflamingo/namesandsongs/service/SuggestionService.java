package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.Suggestion;

public interface SuggestionService {

    public Suggestion addSuggestion(Suggestion suggestion, int visitId);

    public List<Suggestion> getAll();

    public Suggestion get(Integer id);

    public void update(Suggestion suggestion, Integer songId);
	
}
