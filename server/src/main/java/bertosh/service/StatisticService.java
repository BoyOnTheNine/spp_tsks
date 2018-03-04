package bertosh.service;

import bertosh.dao.implementations.StatisticDao;
import bertosh.dbException.DbException;
import bertosh.entities.Statistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class StatisticService {
    
    @Autowired
    private StatisticDao dao;

    private final static Logger logger = LogManager.getLogger(StatisticService.class);
    
    public Statistic create(Statistic statistic) throws DbException {
        try {
            return dao.create(statistic);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new statistic transaction");
        }
    }
    
    public Statistic update(int id, Statistic updateStatistic) throws DbException {
        try {
            Statistic statistic = dao.getById(id);
            statistic.setBeginDate(updateStatistic.getBeginDate());
            statistic.setEndDate(updateStatistic.getEndDate());
            statistic.setCustomer(updateStatistic.getCustomer());
            statistic.setUserOrder(updateStatistic.getUserOrder());
            statistic.setUsers(updateStatistic.getUsers());
            return dao.update(statistic);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating statistic transaction");
        }
    }
    
    public boolean delete(int id) throws DbException {
        try {
            Statistic statistic = dao.getById(id);
            if (statistic != null) {
                dao.delete(statistic);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting statistic transaction");
        }
    }
    
    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating statistic list transaction");
        } 
    }
    
    public Statistic getById(int id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting statistic by id transaction");
        }
    }
}
