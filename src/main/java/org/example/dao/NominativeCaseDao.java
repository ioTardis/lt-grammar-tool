package org.example.dao;

import org.example.entity.NominativeCase;

public interface NominativeCaseDao {
    void save(NominativeCase nominativeCase);

    NominativeCase findById(long id);

    NominativeCase findBySingular(String singular);
}
