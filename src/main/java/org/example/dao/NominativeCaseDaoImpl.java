package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.example.entity.NominativeCase;

public class NominativeCaseDaoImpl implements NominativeCaseDao {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    private final EntityManager entityManager = emf.createEntityManager();

    @Override
    @Transactional
    public void save(NominativeCase nominativeCase) {
        entityManager.persist(nominativeCase);
    }

    @Override
    public NominativeCase findById(long id) {
        return entityManager.find(NominativeCase.class, id);
    }

    @Override
    public NominativeCase findBySingular(String singular) {
        return entityManager.createQuery("SELECT n FROM NominativeCase n WHERE n.singular = :singular", NominativeCase.class)
                .setParameter("singular", singular)
                .getSingleResult();
    }
}
