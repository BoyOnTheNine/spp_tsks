package bertosh.service;

import bertosh.dao.implementations.UserOrderDao;
import bertosh.dbException.DbException;
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

    private final static Logger logger = LogManager.getLogger(UserOrderService.class);

    public UserOrder create(UserOrder userOrder) throws DbException {
        try {
            return dao.create(userOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new user order transaction");
        }
    }

    public UserOrder update(Long id, UserOrder updateUserOrder) throws DbException {
        try {
            UserOrder userOrder = dao.getById(id);
            if (updateUserOrder.getDate() != null) {
                userOrder.setDate(updateUserOrder.getDate());
            }
            if (updateUserOrder.getDescription() != null) {
                userOrder.setDescription(updateUserOrder.getDescription());
            }
            if (updateUserOrder.getIdCustomer() != null) {
                userOrder.setIdCustomer(updateUserOrder.getIdCustomer());
            }
            if (updateUserOrder.getIdUser() != null) {
                userOrder.setIdUser(updateUserOrder.getIdUser());
            }
            if (updateUserOrder.getPrice() != 0) {
                userOrder.setPrice(updateUserOrder.getPrice());
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
