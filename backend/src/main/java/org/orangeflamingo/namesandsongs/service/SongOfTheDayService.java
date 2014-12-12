package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.Song;
import org.orangeflamingo.namesandsongs.domain.SongOfTheDay;

public interface SongOfTheDayService {

    public List<SongOfTheDay> getAll();

    public Object get(Integer id);

    public void initialize();

    public Song getSongOfTheDay();
}