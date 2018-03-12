package bertosh.dao.implementations;

import bertosh.dao.GenericDao;
import bertosh.dbException.DbException;
import bertosh.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao implements GenericDao<User, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = LogManager.getLogger(UserDao.class);

    public UserDao() {
    }

    @Override
    public User create(User user) throws DbException {
        try {
            entityManager.persist(user);
            return user;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new user");
        }
    }

    @Override
    public User update(User user) throws DbException {
        try {
            return entityManager.merge(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating user with id = " + user.getId());
        }
    }

    @Override
    public void  delete(User user) throws DbException {
        try {
            entityManager.remove(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting user with id = " + user.getId());
        }
    }

    @Override
    public List<User> getAll() throws DbException {
        try {
            return entityManager.createQuery("from User c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all users");
        }
    }

    @Override
    public User getById(Long id) throws DbException {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting user with id = " + id);
        }
    }

    public User getByEmail(String email) throws DbException {
        try {
            //this code works correctly, because there are no 2 same email addresses
            return (User)entityManager.createQuery("from User c where c.email=:email")
                    .setParameter("email", email)
                    .getResultList()
                    .get(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting user with email = " + email);
        }
    }

    public User getByLogin(String login) throws DbException {
        try {
            return (User)entityManager.createQuery("from User c where c.login=:login")
                    .setParameter("login", login)
                    .getResultList()
                    .get(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting user with login = " + login);
        }
    }
}