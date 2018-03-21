package bertosh.service;

import bertosh.dao.implementations.CategoryDao;
import bertosh.dao.implementations.OfferDao;
import bertosh.dao.implementations.UserDao;
import bertosh.exceptions.DbException;
import bertosh.entities.Category;
import bertosh.entities.Offer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class OfferService {

    @Autowired
    private OfferDao dao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    private final static Logger logger = LogManager.getLogger(OfferService.class);

    public Offer create(Offer offer) throws DbException {
        try {
            List<Category> list = offer.getCategories();
            offer.setCategories(new ArrayList<>());
            for (Category category : list) {
                offer.getCategories().add(categoryDao.getByName(category.getName()));
            }
            offer.setCustomer(userDao.getByLogin(offer.getCustomer().getLogin()));
            return dao.create(offer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new offer transaction");
        }
    }

    public Offer update(Long id, Offer updateOffer) throws DbException {
        try {
            Offer offer = dao.getById(id);
            if (updateOffer.getCategories() != null) {
                List<Category> list = updateOffer.getCategories();
                offer.setCategories(new ArrayList<>());
                for (Category category : list) {
                    offer.getCategories().add(categoryDao.getByName(category.getName()));
                }
            }
            if (updateOffer.getName() != null) {
                offer.setName(updateOffer.getName());
            }
            if (updateOffer.getDate() != null) {
                offer.setDate(updateOffer.getDate());
            }
            if (updateOffer.getDescription() != null) {
                offer.setDescription(updateOffer.getDescription());
            }
            if (updateOffer.getPrice() != 0) {
                offer.setPrice(updateOffer.getPrice());
            }
            return dao.update(offer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating offer transaction");
        }
    }

    public boolean delete(Long id) throws DbException {
        try {
            Offer offer = dao.getById(id);
            if (offer != null) {
                offer.setCategories(null);
                offer.setCustomer(null);
                dao.delete(offer);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting offer transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating offer list transaction");
        }
    }

    public Offer getById(Long id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting offer by id transaction");
        }
    }
}
