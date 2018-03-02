package bertosh.service;

import bertosh.dao.implementations.UserOrderDao;
import bertosh.dbException.DbException;
import bertosh.entities.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class UserOrderService {

    @Autowired
    private UserOrderDao dao;

    public UserOrder create(UserOrder userOrder) throws DbException {
        try {
            return dao.create(userOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in creating new user order transaction");
        }
    }

    public UserOrder update(int id, UserOrder updateUserOrder) throws DbException {
        try {
            UserOrder userOrder = dao.getById(id);
            userOrder.setDate(updateUserOrder.getDate());
            userOrder.setDescription(updateUserOrder.getDescription());
            userOrder.setIdCustomer(updateUserOrder.getIdCustomer());
            userOrder.setIdUser(updateUserOrder.getIdUser());
            userOrder.setPrice(updateUserOrder.getPrice());
            return dao.update(userOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in updating user order transaction");
        }
    }

    public boolean delete(int id) throws DbException {
        try {
            UserOrder userOrder = dao.getById(id);
            if (userOrder != null) {
                dao.delete(userOrder);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in deleting user order transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in creating user order list transaction");
        }
    }

    public UserOrder getById(int id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in getting user order by id transaction");
        }
    }
}
