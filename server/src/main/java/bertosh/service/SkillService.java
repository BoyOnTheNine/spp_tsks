package bertosh.service;

import bertosh.dao.implementations.SkillDao;
import bertosh.dao.implementations.UserDao;
import bertosh.dbException.DbException;
import bertosh.entities.Skill;
import bertosh.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class SkillService {

    @Autowired
    private SkillDao dao;
    @Autowired
    private UserDao userDao;

    private final static Logger logger = LogManager.getLogger(SkillService.class);

    public Skill create(Skill skill) throws DbException {
        try {
            return dao.create(skill);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating new skill transaction");
        }
    }

    public Skill update(Long id, Skill updateSkill) throws DbException {
        try {
            Skill skill = dao.getById(id);
            if (updateSkill.getName() != null && !Objects.equals(updateSkill.getName(), skill.getName())) {
                skill.setName(updateSkill.getName());
            }
            return dao.update(skill);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in updating skill transaction");
        }
    }

    public boolean delete(Long id) throws DbException {
        try {
            Skill skill = dao.getById(id);
            if (skill != null) {
               List<User> list = userDao.getAll();
               for (User user : list) {
                   user.getSkills().removeIf(deletingSkill -> deletingSkill.getId() == id);
               }
               dao.delete(skill);
               return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in deleting skill transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in creating skill list transaction");
        }
    }

    public Skill getById(Long id) throws DbException {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DbException("Exception in getting skill by id transaction");
        }
    }
}
