package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.Remark;
import org.orangeflamingo.namesandsongs.domain.Song;

public interface RemarkService {
    public Remark addRemark(Remark remark, int visitId, int songId);

    public List<Remark> getAll();

    public Remark get(Integer id);
    
    public Song getSong(Integer id);

    public void update(Remark remark);
}
