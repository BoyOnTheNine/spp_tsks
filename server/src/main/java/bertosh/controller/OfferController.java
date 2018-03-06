package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.entities.Offer;
import bertosh.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class OfferController {

    @Autowired
    private OfferService service;

    @GetMapping("/offers")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        Offer offer = service.getById(id);
        if (offer != null) {
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Offer offer) throws DbException {
        offer = service.update(id, offer);
        if (offer != null) {
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/offers")
    public ResponseEntity<Offer> create(@RequestBody Offer offer) throws DbException {
        offer = service.create(offer);
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
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
