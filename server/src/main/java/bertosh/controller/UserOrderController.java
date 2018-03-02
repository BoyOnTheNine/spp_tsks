package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.entities.UserOrder;
import bertosh.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserOrderController {

    @Autowired
    private UserOrderService service;

    @GetMapping("/orders")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity getById(@PathVariable Integer id) throws DbException {
        UserOrder userOrder = service.getById(id);
        if (userOrder != null) {
            return new ResponseEntity<>(userOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody UserOrder userOrder) throws DbException {
        userOrder = service.update(id, userOrder);
        if (userOrder != null) {
            return new ResponseEntity<>(userOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<UserOrder> create(@RequestBody UserOrder userOrder) throws DbException {
        userOrder = service.create(userOrder);
        return new ResponseEntity<>(userOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws DbException {
        if (service.delete(id)) {
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
