package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.Item;

public interface ItemService {

    public Item addItem(Item item, String user);

    public List<Item> getAll();

    public Item get(Integer id);

    public void update(Item item, Integer songId);
	
    public void removeSong(Item item, Integer songId);
    
}
