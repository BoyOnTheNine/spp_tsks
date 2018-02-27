package dao.implementations;

import dao.GenericDao;
import dbException.DbException;
import entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDao implements GenericDao<User, Integer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(User user) throws DbException {
        try {
            entityManager.persist(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while persisting new user");
        }
    }

    @Override
    public User update(User user) throws DbException {
        try {
            return entityManager.merge(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while updating user with id = " + user.getId());
        }
    }

    @Override
    public void  delete(User user) throws DbException {
        try {
            entityManager.remove(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while deleting user with id = " + user.getId());
        }
    }

    @Override
    public List<User> getAll() throws DbException {
        try {
            return entityManager.createQuery("from User c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting list of all users");
        }
    }

    @Override
    public User getById(Integer id) throws DbException {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting user with id = " + id);
        }
    }

    public List getByFirstName(String firstName) throws DbException {
        try {
            Query query = entityManager.createQuery("from User c where c.firstName:=fisrtName");
            query.setParameter("firstName", firstName);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting user with first name = " + firstName);
        }
    }

    public User getByEmail(String email) throws DbException {
        try {
            return entityManager.find(User.class, email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting user with email = " + email);
        }
    }
}