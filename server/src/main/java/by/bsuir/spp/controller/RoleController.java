package by.bsuir.spp.controller;

import by.bsuir.spp.entities.RoleName;
import by.bsuir.spp.exceptions.DbException;
import by.bsuir.spp.exceptions.EntityNotFoundException;
import by.bsuir.spp.entities.Role;
import by.bsuir.spp.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
            throw new EntityNotFoundException("Unable to find role with id = " + id);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Role role) throws DbException {
        role = service.update(id, role);
        if (role != null) {
            logger.info("Updated role with id = " + id);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find role with id = " + id);
        }
    }

    @PostMapping("/roles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
            throw new EntityNotFoundException("Unable to find role with id = " + id);
        }
    }

    @GetMapping("roles/name/{roleName}")
    public ResponseEntity getByName(@PathVariable RoleName roleName) throws DbException {
        Role role = service.getByName(roleName);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find role with name = " + roleName);
        }
    }
}
