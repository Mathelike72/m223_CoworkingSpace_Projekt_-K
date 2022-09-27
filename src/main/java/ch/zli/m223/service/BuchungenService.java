package ch.zli.m223.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.Buchungen;
import ch.zli.m223.model.Benutzer;


@ApplicationScoped
public class BuchungenService {
    @Inject
    private EntityManager entityManager;

    public List<Buchungen> findAll() {
        var query = entityManager.createQuery("FROM Buchungen", Buchungen.class);
        return query.getResultList();
    }

    public List<Buchungen> findAll(int benutzerId) {
        var query = entityManager.createQuery("SELECT b FROM Buchungen b JOIN Benutzer u ON b.benutzer=u WHERE u.id= :benutzerId");
        query.setParameter("benutzerId", benutzerId);
        return query.getResultList();
    }

    @Transactional
    public Buchungen createBuchungen(Buchungen buchungen) {
        entityManager.persist(buchungen);
        return buchungen;
    }

    @Transactional
    public Buchungen getBuchungen(Long buchungenId) {
        return entityManager.find(Buchungen.class, buchungenId);
    }


    @Transactional
    public Buchungen updateBuchungen(Buchungen buchungen) {
        return entityManager.merge(buchungen);
    }

    public void deleteBuchungen(Long id) {
        var entity = entityManager.find(Buchungen.class, id);
        entityManager.remove(entity);
    }
}
