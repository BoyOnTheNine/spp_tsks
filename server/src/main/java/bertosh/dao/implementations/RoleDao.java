package bertosh.dao.implementations;

import bertosh.dao.GenericDao;
import bertosh.exceptions.DbException;
import bertosh.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao implements GenericDao<Role, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = LogManager.getLogger(RoleDao.class);

    @Override
    public Role create(Role role) throws DbException {
        try {
            entityManager.persist(role);
            return role;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new role");
        }
    }

    @Override
    public Role update(Role role) throws DbException {
        try {
            return entityManager.merge(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating role with id = " + role.getId());
        }
    }

    @Override
    public void delete(Role role) throws DbException {
        try {
            entityManager.remove(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting role with id = " + role.getId());
        }
    }

    @Override
    public List<Role> getAll() throws DbException {
        try {
            return entityManager.createQuery("from Role c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all roles");
        }
    }

    @Override
    public Role getById(Long id) throws DbException {
        try {
            return entityManager.find(Role.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting role with id = " + id);
        }
    }

    public Role getByName(String name) throws DbException {
        try {
            return (Role)entityManager.createQuery("from Role c where c.name=:name")
                    .setParameter("name", name)
                    .getResultList()
                    .get(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting category with name = " + name);
        }
    }
}
