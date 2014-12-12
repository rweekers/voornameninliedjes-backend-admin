package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.orangeflamingo.namesandsongs.domain.Remark;

public interface RemarkService {
    public Remark addRemark(Remark remark);

    public List<Remark> getAll();

    public Remark get(Integer id);

    public void update(Remark remark);
}
