package by.bsuir.spp.dao.implementations;

import by.bsuir.spp.dao.GenericDao;
import by.bsuir.spp.exceptions.DbException;
import by.bsuir.spp.entities.UserOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserOrderDao implements GenericDao<UserOrder, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = LogManager.getLogger(UserOrderDao.class);

    public UserOrderDao() {
    }

    @Override
    public UserOrder create(UserOrder userOrder) throws DbException {
        try {
            entityManager.persist(userOrder);
            return userOrder;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new user order");
        }
    }

    @Override
    public UserOrder update(UserOrder userOrder) throws DbException {
        try {
            return entityManager.merge(userOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating user order with id = " + userOrder.getId());
        }
    }

    @Override
    public void delete(UserOrder userOrder) throws DbException {
        try {
            entityManager.remove(userOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting user order with id = " + userOrder.getId());
        }
    }

    @Override
    public List<UserOrder> getAll() throws DbException {
        try {
            return entityManager.createQuery("from UserOrder c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all user orders");
        }
    }

    @Override
    public UserOrder getById(Long id) throws DbException {
        try {
            return entityManager.find(UserOrder.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting user order with id = " + id);
        }
    }
}
