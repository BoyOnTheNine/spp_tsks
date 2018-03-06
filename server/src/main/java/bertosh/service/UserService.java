package bertosh.service;

import bertosh.dao.implementations.RoleDao;
import bertosh.dao.implementations.UserDao;
import bertosh.dbException.DbException;
import bertosh.entities.Role;
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

    @Autowired
    private RoleDao roleDao;

    private final static Logger logger = LogManager.getLogger(UserService.class);
    
    public User create(User user) throws DbException {
        try {
            for (Role role : user.getRoles()) {
                roleDao.create(role);
            }
            return dao.create(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new user transaction");
        }
    }
    
    public User update(Long id, User updateUser) throws DbException {
        try {
            User user = dao.getById(id);
            if (updateUser.getCountry() != null) {
                user.setCountry(updateUser.getCountry());
            }
            if (updateUser.getDescription() != null) {
                user.setDescription(updateUser.getDescription());
            }
            if (updateUser.getEmail() != null) {
                user.setEmail(updateUser.getEmail());
            }
            if (updateUser.getFirstName() != null) {
                user.setFirstName(updateUser.getFirstName());
            }
            if (updateUser.getLastName() != null) {
                user.setLastName(updateUser.getLastName());
            }
            if (updateUser.getPhoneNumber() != null) {
                user.setPhoneNumber(updateUser.getPhoneNumber());
            }
            if (updateUser.getRating() != 0) {
                user.setRating(updateUser.getRating());
            }
            return dao.update(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating user transaction");
        }
    }
    
    public boolean delete(Long id) throws DbException {
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
    
    public User getById(Long id) throws DbException {
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
