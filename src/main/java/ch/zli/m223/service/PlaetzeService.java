package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Plaetze;

@ApplicationScoped
public class PlaetzeService {
    @Inject
    private EntityManager entityManager;

    public List<Plaetze> findAll() {
        var query = entityManager.createQuery("FROM Plaetze", Plaetze.class);
        return query.getResultList();
    }

    public List<Plaetze> findAll(int benutzerId) {
        var query = entityManager.createQuery("SELECT p FROM Plaetze p JOIN Benutzer u ON p.benutzer=u WHERE u.id= :benutzerId");
        query.setParameter("benutzerId", benutzerId);
        return query.getResultList();
    }

    @Transactional
    public Plaetze createPlaetze(Plaetze plaetze) {
        entityManager.persist(plaetze);
        return plaetze;
    }

    @Transactional
    public Plaetze getPlaetze(Long plaetzeId) {
        return entityManager.find(Plaetze.class, plaetzeId);
    }


    @Transactional
    public Plaetze updatePlaetze(Plaetze plaetze) {
        return entityManager.merge(plaetze);
    }

    public void deletePlaetze(Long id) {
        var entity = entityManager.find(Plaetze.class, id);
        entityManager.remove(entity);
    }
}
