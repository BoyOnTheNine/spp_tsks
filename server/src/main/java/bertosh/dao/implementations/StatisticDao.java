package bertosh.dao.implementations;

import bertosh.dao.GenericDao;
import bertosh.exceptions.DbException;
import bertosh.entities.Statistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StatisticDao implements GenericDao<Statistic, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = LogManager.getLogger(StatisticDao.class);

    public StatisticDao() {
    }

    @Override
    public Statistic create(Statistic statistic) throws DbException {
        try {
            entityManager.persist(statistic);
            return statistic;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new statistic");
        }
    }

    @Override
    public Statistic update(Statistic statistic) throws DbException {
        try {
            return entityManager.merge(statistic);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating statistic with id = " + statistic.getId());
        }
    }

    @Override
    public void delete(Statistic statistic) throws DbException {
        try {
            entityManager.remove(statistic);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting statistic with id = " + statistic.getId());
        }
    }

    @Override
    public List<Statistic> getAll() throws DbException {
        try {
            return entityManager.createQuery("from Statistic c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all statistics");
        }
    }

    @Override
    public Statistic getById(Long id) throws DbException {
        try {
            return entityManager.find(Statistic.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting statistic with id = " + id);
        }
    }
}
