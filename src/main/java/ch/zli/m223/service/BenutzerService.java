package ch.zli.m223.service;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.exceptions.InvalidLoginException;
import ch.zli.m223.model.Benutzer;
import ch.zli.m223.security.jwt.*;

@ApplicationScoped
public class BenutzerService {
    @Inject
    private EntityManager entityManager;

    public List<Benutzer> findAll() {
        var query = entityManager.createQuery("FROM Benutzer", Benutzer.class);
        return query.getResultList();
    }

    @Transactional
    public Benutzer createUser(Benutzer benutzer) {
        entityManager.persist(benutzer);
        return benutzer;
    }

    @Transactional
    public Benutzer updateUser(Benutzer benutzer) {
        return entityManager.merge(benutzer);
    }

    public void deleteUser(Long id) {
        var entity = entityManager.find(Benutzer.class, id);
        entityManager.remove(entity);
    }

    public String login(Login login) throws InvalidLoginException {
        var entity = entityManager.find(Benutzer.class, login.getEmail());
        if (entity == null) {
            throw new InvalidLoginException("Email doesn't exist");
        } else if (entity.getPassword().equals(login.getPassword())) {
            throw new InvalidLoginException("Wrong Password");
        }

        //TODO: return real token
        return "THIS IS A TOKEN";
    }
    
    public String register(Benutzer benutzer) {
        createUser(benutzer);

        //TODO: return real token
        return "This is a token";
    }
}

