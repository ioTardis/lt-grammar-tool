package org.example.service;

import org.example.entity.Case;

import java.util.List;
import java.util.Optional;

public interface CaseService<T extends Case> {

    T save(T entity);

    List<T> getAll();

    Optional<T> getBySingular(String singular);

    List<T> getFixedAmount(int count);
}
