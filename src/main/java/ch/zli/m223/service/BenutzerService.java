package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


import ch.zli.m223.model.Benutzer;

@ApplicationScoped
public class BenutzerService {
    @Inject
    private EntityManager entityManager;

    public List<Benutzer> findAll() {
        var query = entityManager.createQuery("FROM Benutzer", Benutzer.class);
        return query.getResultList();
    }

}