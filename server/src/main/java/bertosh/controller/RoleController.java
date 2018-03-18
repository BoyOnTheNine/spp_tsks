package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.entities.Role;
import bertosh.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    @Autowired
    private RoleService service;

    private final static Logger logger = LogManager.getLogger(RoleController.class);

    @GetMapping("/roles")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        Role role = service.getById(id);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Role role) throws DbException {
        role = service.update(id, role);
        if (role != null) {
            logger.info("Updated role with id = " + id);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> create(@RequestBody Role role) throws DbException {
        role = service.create(role);
        logger.info("Created new role with id = " + role.getId());
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (service.delete(id)) {
            logger.info("Deleted role with id = " + id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(DbException.class)
    public ResponseEntity handleDbException(DbException e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
