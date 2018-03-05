package bertosh.service;

import bertosh.dao.implementations.UserDao;
import bertosh.dbException.DbException;
import bertosh.entities.User;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserDao dao;

    private final static Logger logger = LogManager.getLogger(UserService.class);
    
    public User create(User user) throws DbException {
        try {
            return dao.create(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new user transaction");
        }
    }
    
    public User update(int id, User updateUser) throws DbException {
        try {
            User user = dao.getById(id);
            user.setCountry(updateUser.getCountry());
            user.setDescription(updateUser.getDescription());
            user.setEmail(updateUser.getEmail());
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhoneNumber(updateUser.getPhoneNumber());
            user.setRating(updateUser.getRating());
            return dao.update(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating user transaction");
        }
    }
    
    public boolean delete(int id) throws DbException {
        try {
            User user = dao.getById(id);
            if (user != null) {
                dao.delete(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting user transaction");
        }
    }
    
    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating user list transaction");
        }
    }
    
    public User getById(int id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting user by id transaction");
        }
    }

    public User getByEmail(String email) throws DbException {
        try {
            return dao.getByEmail(email);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting user by email transaction");
        }
    }
}
