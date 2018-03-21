package bertosh.dao.implementations;

import bertosh.dao.GenericDao;
import bertosh.exceptions.DbException;
import bertosh.entities.Offer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OfferDao implements GenericDao<Offer, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = LogManager.getLogger(OfferDao.class);

    public OfferDao() {
    }

    @Override
    public Offer create(Offer offer) throws DbException {
        try {
            entityManager.persist(offer);
            return offer;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new offer");
        }
    }

    @Override
    public Offer update(Offer offer) throws DbException {
        try {
            return entityManager.merge(offer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating offer with id = " + offer.getId());
        }
    }

    @Override
    public void delete(Offer offer) throws DbException {
        try {
            entityManager.remove(offer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting offer with id = " + offer.getId());
        }
    }

    @Override
    public List<Offer> getAll() throws DbException {
        try {
            return entityManager.createQuery("from Offer c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all offers");
        }
    }

    @Override
    public Offer getById(Long id) throws DbException {
        try {
            return entityManager.find(Offer.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting offer with id = " + id);
        }
    }
}
