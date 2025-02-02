package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.NominativeCase;
import org.example.repository.NominativeCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NominativeCaseService implements CaseService<NominativeCase> {

    final private NominativeCaseRepository repository;

    @Override
    public NominativeCase save(NominativeCase entity) {
        return repository.save(entity);
    }

    @Override
    public List<NominativeCase> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<NominativeCase> getBySingular(String singular) {
        return Optional.ofNullable(repository.findBySingular(singular));
    }

    @Override
    public List<NominativeCase> getFixedAmount(int count) {
        return repository.findFixedAmountOfNominativeCases(count);
    }
}
