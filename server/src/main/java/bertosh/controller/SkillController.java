package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.entities.Skill;
import bertosh.service.SkillService;
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
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Skill skill) throws DbException {
        skill = service.update(id, skill);
        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/skills")
    public ResponseEntity<Skill> create(@RequestBody Skill skill) throws DbException {
        skill = service.create(skill);
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (service.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
