package bertosh.dao.implementations;

import bertosh.dao.GenericDao;
import bertosh.dbException.DbException;
import bertosh.entities.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDao implements GenericDao<Category, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = LogManager.getLogger(CategoryDao.class);

    public CategoryDao() {
    }

    @Override
    public Category create(Category category) throws DbException {
        try {
            entityManager.persist(category);
            return category;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new category");
        }
    }

    @Override
    public Category update(Category category) throws DbException {
        try {
            return entityManager.merge(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating category with id = " + category.getId());
        }
    }

    @Override
    public void delete(Category category) throws DbException {
        try {
            entityManager.remove(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting category with id = " + category.getId());
        }
    }

    @Override
    public List<Category> getAll() throws DbException {
        try {
            return entityManager.createQuery("from Category c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all categories");
        }
    }

    @Override
    public Category getById(Long id) throws DbException {
        try {
            return entityManager.find(Category.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting category with id = " + id);
        }
    }
}
