package bertosh.service;

import bertosh.dao.implementations.OfferDao;
import bertosh.dao.implementations.UserDao;
import bertosh.dao.implementations.UserOrderDao;
import bertosh.exceptions.DbException;
import bertosh.entities.User;
import bertosh.entities.UserOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class UserOrderService {

    @Autowired
    private UserOrderDao dao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OfferDao offerDao;

    private final static Logger logger = LogManager.getLogger(UserOrderService.class);

    public UserOrder create(UserOrder userOrder) throws DbException {
        try {
            Set<User> set = userOrder.getWorkers();
            userOrder.setWorkers(new HashSet<>());
            for (User user : set) {
                userOrder.getWorkers().add(userDao.getByLogin(user.getLogin()));
            }
            userOrder.setOffer(offerDao.getById(userOrder.getOffer().getId()));
            return dao.create(userOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new user order transaction");
        }
    }

    public UserOrder update(Long id, UserOrder updateUserOrder) throws DbException {
        try {
            UserOrder userOrder = dao.getById(id);
            if (updateUserOrder.getWorkers() != null) {
                Set<User> set = updateUserOrder.getWorkers();
                userOrder.setWorkers(new HashSet<>());
                for (User user : set) {
                    userOrder.getWorkers().add(userDao.getByLogin(user.getLogin()));
                }
            }
            if (updateUserOrder.getOffer() != null) {
                userOrder.setOffer(offerDao.getById(updateUserOrder.getOffer().getId()));
            }
            return dao.update(userOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating user order transaction");
        }
    }

    public boolean delete(Long id) throws DbException {
        try {
            UserOrder userOrder = dao.getById(id);
            if (userOrder != null) {
                userOrder.setWorkers(null);
                userOrder.setOffer(null);
                dao.delete(userOrder);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting user order transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating user order list transaction");
        }
    }

    public UserOrder getById(Long id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting user order by id transaction");
        }
    }
}
