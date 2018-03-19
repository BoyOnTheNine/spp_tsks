package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.dbException.EntityNotFoundException;
import bertosh.entities.Skill;
import bertosh.service.SkillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SkillController {

    @Autowired
    private SkillService service;

    private final static Logger logger = LogManager.getLogger(SkillController.class);

    @GetMapping("/skills")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/skills/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        Skill skill = service.getById(id);
        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find skill with id = " + id);
        }
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Skill skill) throws DbException {
        skill = service.update(id, skill);
        if (skill != null) {
            logger.info("Updated skill with id = " + id);
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find skill with id = " + id);
        }
    }

    @PostMapping("/skills")
    public ResponseEntity<Skill> create(@RequestBody Skill skill) throws DbException {
        skill = service.create(skill);
        logger.info("Created new skill with id = " + skill.getId());
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (service.delete(id)) {
            logger.info("Deleted skill with id = " + id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find skill with id = " + id);
        }
    }
}
