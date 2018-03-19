package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.dbException.EntityNotFoundException;
import bertosh.entities.Statistic;
import bertosh.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class StatisticController {

    @Autowired
    private StatisticService service;

    @GetMapping("/statistics")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        Statistic statistic = service.getById(id);
        if (statistic != null) {
            return new ResponseEntity<>(statistic, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find statistic with id = " + id);
        }
    }

    @PutMapping("/statistics/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Statistic statistic) throws DbException {
        statistic = service.update(id, statistic);
        if (statistic != null) {
            return new ResponseEntity<>(statistic, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find statistic with id = " + id);
        }
    }

    @PostMapping("/statistics")
    public ResponseEntity<Statistic> create(@RequestBody Statistic statistic) throws DbException {
        statistic = service.create(statistic);
        return new ResponseEntity<>(statistic, HttpStatus.CREATED);
    }

    @DeleteMapping("/statistics/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (service.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find statistic with id = " + id);
        }
    }
}
