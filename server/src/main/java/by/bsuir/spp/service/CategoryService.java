package by.bsuir.spp.service;

import by.bsuir.spp.dao.implementations.CategoryDao;
import by.bsuir.spp.dao.implementations.OfferDao;
import by.bsuir.spp.exceptions.DbException;
import by.bsuir.spp.entities.Category;
import by.bsuir.spp.entities.Offer;
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
    @Autowired
    private OfferDao offerDao;

    private final static Logger logger = LogManager.getLogger(CategoryService.class);

    public Category create(Category category) throws DbException {
        try {
            return dao.create(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new category transaction");
        }
    }

    public Category update(Long id, Category updateCategory) throws DbException {
        try {
            Category category = dao.getById(id);
            if (updateCategory.getName() != null && !Objects.equals(updateCategory.getName(), category.getName())) {
                category.setName(updateCategory.getName());
            }
            return dao.update(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating category transaction");
        }
    }

    public boolean delete(Long id) throws DbException {
        try {
            Category category = dao.getById(id);
            if (category != null) {
                List<Offer> offerList = offerDao.getAll();
                for (Offer offer : offerList) {
                    offer.getCategories().removeIf(category1 -> category1.getId() == id);
                }
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

    public Category getById(Long id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting category by id transaction");
        }
    }
}
