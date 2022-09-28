package ch.zli.m223.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;

import ch.zli.m223.exceptions.InvalidLoginException;
import ch.zli.m223.exceptions.NullValueException;
import ch.zli.m223.model.Benutzer;
import ch.zli.m223.model.Login;

@ApplicationScoped
public class BenutzerService {
    @Inject
    private EntityManager entityManager;

    @Inject
    private TokenService tokenService;

    public List<Benutzer> findAll() {
        var query = entityManager.createQuery("FROM Benutzer", Benutzer.class);
        return query.getResultList();
    }

    @Transactional
    public Benutzer createBenutzer(Benutzer benutzer) throws EntityExistsException, Exception {
        try {
            entityManager.persist(benutzer);
        } catch (EntityExistsException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

        return benutzer;
    }

    @Transactional
    public Benutzer updateBenutzer(Benutzer benutzer) throws IllegalArgumentException, TransactionRequiredException {
        try {
            return entityManager.merge(benutzer);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (TransactionRequiredException e) {
            throw e;
        }
    }

    public void deleteBenutzer(Long id) throws NullValueException {
        var entity = entityManager.find(Benutzer.class, id);
        if (entity == null) {
            throw new NullValueException("No user with id: " + id + " was found");
        }
        entityManager.remove(entity);
    }

    public String login(Login login) throws InvalidLoginException {
        var query = entityManager.createQuery("SELECT u FROM Benutzer u Where u.email = :email");
        query.setParameter("email", login.getEmail());
        Benutzer entity = (Benutzer) query.getSingleResult();

        if (entity == null) {
            throw new InvalidLoginException("Email doesn't exist");
        } else if (entity.getPassword() == login.getPassword()) {
            throw new InvalidLoginException("Wrong Password: " + entity.getPassword() + " vs " + login.getPassword());
        }

        String derTokenIstSicher = tokenService.createToken(entity);
        return derTokenIstSicher;
    }

    public String register(Benutzer benutzer) throws EntityExistsException, Exception {
        try {
            List<Benutzer> benutzers = this.findAll();

            if (benutzers.isEmpty()) {
                benutzer.setAdmin(true);
            } else {
                benutzer.setAdmin(false);
            }

            createBenutzer(benutzer);
        } catch (EntityExistsException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        String derTokenIstSicher = tokenService.createToken(benutzer);
        return derTokenIstSicher;
    }
}

