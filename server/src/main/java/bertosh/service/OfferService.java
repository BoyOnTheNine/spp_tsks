package bertosh.service;

import bertosh.dao.implementations.OfferDao;
import bertosh.dbException.DbException;
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

    private final static Logger logger = LogManager.getLogger(OfferService.class);

    public Offer create(Offer offer) throws DbException {
        try {
            return dao.create(offer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new offer transaction");
        }
    }

    public Offer update(int id, Offer updateOffer) throws DbException {
        try {
            Offer offer = dao.getById(id);
            offer.setName(updateOffer.getName());
            offer.setCategory(updateOffer.getCategory());
            offer.setDate(updateOffer.getDate());
            offer.setDescription(updateOffer.getDescription());
            return dao.update(offer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating offer transaction");
        }
    }

    public boolean delete(int id) throws DbException {
        try {
            Offer offer = dao.getById(id);
            if (offer != null) {
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

    public Offer getById(int id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting offer by id transaction");
        }
    }
}
