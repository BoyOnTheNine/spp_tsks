package bertosh.service;

import bertosh.dao.implementations.CategoryDao;
import bertosh.dbException.DbException;
import bertosh.entities.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryDao dao;

    private final static Logger logger = LogManager.getLogger(CategoryService.class);

    public Category create(Category category) throws DbException {
        try {
            return dao.create(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new category transaction");
        }
    }

    public Category update(int id, Category updateCategory) throws DbException {
        try {
            Category category = dao.getById(id);
            category.setName(updateCategory.getName());
            return dao.update(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating category transaction");
        }
    }

    public boolean delete(int id) throws DbException {
        try {
            Category category = dao.getById(id);
            if (category != null) {
                dao.delete(category);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting category transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating category list transaction");
        }
    }

    public Category getById(int id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting category by id transaction");
        }
    }
}
