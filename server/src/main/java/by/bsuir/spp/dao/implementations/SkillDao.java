package by.bsuir.spp.dao.implementations;

import by.bsuir.spp.dao.GenericDao;
import by.bsuir.spp.exceptions.DbException;
import by.bsuir.spp.entities.Skill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class SkillDao implements GenericDao<Skill, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private final static Logger logger = LogManager.getLogger(SkillDao.class);

    @Override
    public Skill create(Skill skill) throws DbException {
        try {
            entityManager.persist(skill);
            return skill;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while persisting new skill");
        }
    }

    @Override
    public Skill update(Skill skill) throws DbException {
        try {
            return entityManager.merge(skill);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while updating skill with id = " + skill.getId());
        }
    }

    @Override
    public void delete(Skill skill) throws DbException {
        try {
            entityManager.remove(skill);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while deleting skill with id = " + skill.getId());
        }
    }

    @Override
    public List<Skill> getAll() throws DbException {
        try {
            return entityManager.createQuery("from Skill c").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting list of all skills");
        }
    }

    @Override
    public Skill getById(Long id) throws DbException {
        try {
            return entityManager.find(Skill.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting skill with id = " + id);
        }
    }

    public Skill getByName(String name) throws DbException {
        try {
            return (Skill)entityManager.createQuery("from Skill c where c.name=:name")
                    .setParameter("name", name)
                    .getResultList()
                    .get(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception while getting skill with name = " + name);
        }
    }
}
