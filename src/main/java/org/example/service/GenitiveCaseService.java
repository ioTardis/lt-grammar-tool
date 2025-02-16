package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.GenetiveCase;
import org.example.repository.GenitiveCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenitiveCaseService implements CaseService<GenetiveCase> {

    final private GenitiveCaseRepository repository;

    @Override
    public GenetiveCase save(GenetiveCase entity) {
        return repository.save(entity);
    }

    @Override
    public List<GenetiveCase> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<GenetiveCase> getBySingular(String singular) {
        return Optional.ofNullable(repository.findBySingular(singular));
    }

    @Override
    public List<GenetiveCase> getFixedAmount(int count) {
        return repository.findFixedAmountOfGenitiveCases(count);
    }
}
