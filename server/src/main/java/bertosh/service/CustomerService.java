package bertosh.service;

import bertosh.dao.implementations.CustomerDao;
import bertosh.dbException.DbException;
import bertosh.entities.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    private final static Logger logger = LogManager.getLogger(CustomerService.class);

    public Customer create(Customer customer) throws DbException {
        try {
            return dao.create(customer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new customer transaction");
        }
    }

    public Customer update(Long id, Customer updateCustomer) throws DbException {
        try {
            Customer customer = dao.getById(id);
            if (customer != null) {
                if (updateCustomer.getName() != null) {
                    customer.setName(updateCustomer.getName());
                }
                if (updateCustomer.getDescription() != null) {
                    customer.setDescription(updateCustomer.getDescription());
                }
                return dao.update(customer);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating customer with id = " + id + " transaction");
        }
    }

    public boolean delete(Long id) throws DbException {
        try {
            Customer customer = dao.getById(id);
            if (customer != null) {
                dao.delete(customer);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting customer with id = " + id + " transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating customer's list transaction");
        }
    }

    public Customer getById(Long id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting customer by id = " + id + " transaction");
        }
    }
}
