package ch.zli.m223.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.exceptions.NullValueException;
import ch.zli.m223.model.Abos;

@ApplicationScoped
public class AbosService {
    @Inject
    private EntityManager entityManager;

    public List<Abos> findAll() {
        var query = entityManager.createQuery("FROM Abos", Abos.class);
        return query.getResultList();
    }

    public List<Abos> findAll(int benutzerId) {
        var query = entityManager.createQuery("SELECT b FROM Abos b JOIN Benutzer u ON b.benutzer=u WHERE u.id= :benutzerId", Abos.class);
        query.setParameter("benutzerId", benutzerId);
        return query.getResultList();
    }

    @Transactional
    public Abos createAbos(Abos abos) {
        entityManager.persist(abos);
        return abos;
    }

    @Transactional
    public Abos getAbos(Long abosId) {
        return entityManager.find(Abos.class, abosId);
    }


    @Transactional
    public Abos updateAbos(Abos abos) {
        return entityManager.merge(abos);
    }

    @Transactional
    public void deleteAbos(Long id) throws NullValueException {
        var entity = entityManager.find(Abos.class, id);
        if (entity == null) {
            throw new NullValueException("No user with id: " + id + " was found");
        }
        entityManager.remove(entity);
    }
}
