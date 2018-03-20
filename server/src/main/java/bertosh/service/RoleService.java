package bertosh.service;

import bertosh.dao.implementations.RoleDao;
import bertosh.dao.implementations.UserDao;
import bertosh.exceptions.DbException;
import bertosh.entities.Role;
import bertosh.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleDao dao;
    @Autowired
    private UserDao userDao;

    private final static Logger logger = LogManager.getLogger(RoleService.class);

    public Role create(Role role) throws DbException {
        try {
            return dao.create(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new role transaction");
        }
    }

    public Role update(Long id, Role updateRole) throws DbException {
        try {
            Role role = dao.getById(id);
            if (updateRole.getName() != null) {
                role.setName(updateRole.getName());
            }
            if (updateRole.getDescription() != null) {
                role.setDescription(updateRole.getDescription());
            }
            return dao.update(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating role transaction");
        }
    }

    public boolean delete(Long id) throws DbException {
        try {
            Role role = dao.getById(id);
            if (role != null) {
                List<User> userList = userDao.getAll();
                for (User user : userList) {
                    user.getRoles().removeIf(role1 -> role1.getId() == id);
                }
                dao.delete(role);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting role transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating role list transaction");
        }
    }

    public Role getById(Long id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting role by id transaction");
        }
    }
}
