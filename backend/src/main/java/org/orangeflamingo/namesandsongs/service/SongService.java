package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.Song;

public interface SongService {

    public Song getYouCanCallMeAl();

    public List<Song> getAll(Integer count, Integer page, String sortingArtist,
            String sortingTitle, String filterArtist, String filterTitle);

    public List<Song> getAll();

    public List<Song> findByFirstname(String firstname);

    public long getMax();

    public Song add(Song song);

    public void delete(Integer id);

    public Object get(Integer id);

    public void update(Song song);

    long getCount(String filterArtist, String filterTitle);
}