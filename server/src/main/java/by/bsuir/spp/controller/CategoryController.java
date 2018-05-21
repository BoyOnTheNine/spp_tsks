package by.bsuir.spp.controller;

import by.bsuir.spp.exceptions.DbException;
import by.bsuir.spp.exceptions.EntityNotFoundException;
import by.bsuir.spp.entities.Category;
import by.bsuir.spp.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService service;

    private final static Logger logger = LogManager.getLogger(CategoryController.class);

    @GetMapping("/categories")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        Category category = service.getById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find category with id = " + id);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Category category) throws DbException {
        category = service.update(id, category);
        if (category != null) {
            logger.info("Category " + category.getName() + " was updated");
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find category with id = " + id);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category) throws DbException {
        category = service.create(category);
        logger.info("Category " + category.getName() + " was created");
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (service.delete(id)) {
            logger.info("Category with id = " + id + " was deleted");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find category with id = " + id);
        }
    }
}
