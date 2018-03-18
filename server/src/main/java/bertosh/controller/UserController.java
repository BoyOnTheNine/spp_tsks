package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bertosh.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserService service;

    private final static Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/users")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        User user = service.getById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/byemail/{email}")
    public ResponseEntity getByEmail(@PathVariable String email) throws DbException {
        User user = service.getByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) throws DbException {
        user = service.create(user);
        logger.info("Created new user with id = " + user.getId());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody User user) throws DbException {
        user = service.update(id, user);
        if (user != null) {
            logger.info("Updated user with id = " + id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if(service.delete(id)) {
            logger.info("Deleted user with id = " + id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/login/{login}")
    public ResponseEntity getByLogin(@PathVariable String login) throws DbException {
        User user = service.getByLogin(login);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(DbException.class)
    public ResponseEntity handleDbException(DbException e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
