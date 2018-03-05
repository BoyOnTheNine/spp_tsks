package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.entities.User;
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
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody User user) throws DbException {
        user = service.update(id, user);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if(service.delete(id)) {
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
