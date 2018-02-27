package dao.implementations;

import dao.GenericDao;
import dbException.DbException;
import entities.UserOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserOrderDao implements GenericDao<UserOrder, Integer> {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public UserOrder create(UserOrder userOrder) throws DbException {
        try {
            entityManager.persist(userOrder);
            return userOrder;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while persisting new user order");
        }
    }

    @Override
    public UserOrder update(UserOrder userOrder) throws DbException {
        try {
            return entityManager.merge(userOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while updating user order with id = " + userOrder.getId());
        }
    }

    @Override
    public void delete(UserOrder userOrder) throws DbException {
        try {
            entityManager.remove(userOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while deleting user order with id = " + userOrder.getId());
        }
    }

    @Override
    public List<UserOrder> getAll() throws DbException {
        try {
            return entityManager.createQuery("from UserOrder c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting list of all user orders");
        }
    }

    @Override
    public UserOrder getById(Integer id) throws DbException {
        try {
            return entityManager.find(UserOrder.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting user order with id = " + id);
        }
    }
}
