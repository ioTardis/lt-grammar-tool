package org.example.service;

import org.example.entity.NominativeCase;
import org.example.repository.NominativeCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominativeCaseService {

    @Autowired
    private NominativeCaseRepository repository;

    public NominativeCase saveNominativeCase(NominativeCase nominativeCase) {
        return repository.save(nominativeCase);
    }

    public List<NominativeCase> getAllNominativeCases() {
        return repository.findAll();
    }

    public NominativeCase getNominativeCaseBySingular(String singular) {
        return repository.findBySingular(singular);
    }
}
