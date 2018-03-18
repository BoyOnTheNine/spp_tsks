package bertosh.dao.implementations;

import bertosh.dao.GenericDao;
import bertosh.dbException.DbException;
import bertosh.entities.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDao implements GenericDao<Customer, Long>{

    private final static Logger logger = LogManager.getLogger(CustomerDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer create(Customer customer) throws DbException {
        try {
            entityManager.persist(customer);
            return customer;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new customer");
        }
    }

    @Override
    public Customer update(Customer customer) throws DbException {
        try {
            return entityManager.merge(customer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating customer with id = " + customer.getId());
        }
    }

    @Override
    public void delete(Customer customer) throws DbException {
        try {
            entityManager.remove(customer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting customer with id = " + customer.getId());
        }
    }

    @Override
    public List<Customer> getAll() throws DbException {
        try {
            return entityManager.createQuery("from Customer c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all customers");
        }
    }

    @Override
    public Customer getById(Long id) throws DbException {
        try {
            return entityManager.find(Customer.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting customer with id = " + id);
        }
    }
}
