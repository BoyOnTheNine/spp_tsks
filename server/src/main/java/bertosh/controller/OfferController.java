package bertosh.controller;

import bertosh.exceptions.DbException;
import bertosh.exceptions.EntityNotFoundException;
import bertosh.entities.Offer;
import bertosh.service.OfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final static Logger logger = LogManager.getLogger(OfferController.class);

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
            throw new EntityNotFoundException("Unable to find offer with id = " + id);
        }
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Offer offer) throws DbException {
        offer = service.update(id, offer);
        if (offer != null) {
            logger.info("Updated offer with id = " + id);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find offer with id = " + id);
        }
    }

    @PostMapping("/offers")
    public ResponseEntity<Offer> create(@RequestBody Offer offer) throws DbException {
        offer = service.create(offer);
        logger.info("Created new Offer with id = " + offer.getId());
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (service.delete(id)) {
            logger.info("Deleted offer with id = " + id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find offer with id = " + id);
        }
    }
}
