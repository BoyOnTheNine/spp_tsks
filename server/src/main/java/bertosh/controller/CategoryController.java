package bertosh.controller;

import bertosh.dbException.DbException;
import bertosh.dbException.EntityNotFoundException;
import bertosh.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bertosh.service.CategoryService;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService service;

    /**
     * Return <tt>ResponseEntity</tt> that contains <tt>List<tt/> of all categories
     * from database
     * @return List of all categories from database
     * @throws EntityNotFoundException if category not founded
     * @throws DbException if any other exceptions
     */
    @GetMapping("/categories")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Return <tt>ResponseEntity</tt> that contains <tt>Category</tt> with
     * <tt>HttpStatus</tt>
     * @param id of <tt>Category</tt> to be founded
     * @return <tt>ResponseEntity</tt> with <tt>Category</tt> and <tt>HttpStatus</tt>
     *         OK if <tt>Category</tt> was founded or <tt>ResponseEntity</tt> with
     *         <tt>HttpStatus</tt> NOT_FOUND if not
     * @throws EntityNotFoundException if category not founded
     * @throws DbException if any other exceptions
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        Category category = service.getById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find category with id = " + id);
        }
    }

    /**
     * Return updated <tt>Category</tt>
     * @param id of <tt>Category</tt> to be updated
     * @param category <tt>Category</tt> with fields that should be updated
     * @return <tt>ResponseEntity</tt> with <tt>Category</tt> and <tt>HttpStatus</tt>
     *         OK if <tt>Category</tt> was updated or <tt>ResponseEntity</tt> with
     *         <tt>HttpStatus</tt> NOT_FOUND if not
     * @throws EntityNotFoundException if category not founded
     * @throws DbException if any other exceptions
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Category category) throws DbException {
        category = service.update(id, category);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find category with id = " + id);
        }
    }

    /**
     * Return new <tt>Category</tt> that was created
     * @param category <tt>Category</tt> that should be created
     * @return <tt>ResponseEntity</tt> with <tt>Category</tt> and <tt>HttpStatus</tt>
     *         OK if <tt>Category</tt> was created
     * @@throws EntityNotFoundException if category not founded
     * @throws DbException if any other exceptions
     */
    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category) throws DbException {
        category = service.create(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    /**
     * Delete <tt>Category</tt> this some id
     * @param id of <tt>Category</tt> that should be deleted
     * @return <tt>ResponseEntity</tt> with <tt>HttpStatus</tt OK if <tt>Category</tt>
     *         was deleted or <tt>ResponseEntity</tt> with <tt>HttpStatus</tt> NOT_FOUND
     *         if not
     * @throws EntityNotFoundException if category not founded
     * @throws DbException if any other exceptions
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (service.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Unable to find category with id = " + id);
        }
    }
}
