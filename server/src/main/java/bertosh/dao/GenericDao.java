package bertosh.dao;

import bertosh.exceptions.DbException;

import java.io.Serializable;
import java.util.*;

public interface GenericDao<T, PK extends Serializable> {

    T create(T object) throws DbException;

    T update(T object) throws DbException;

    void delete(T object) throws DbException;

    List<T> getAll() throws DbException;

    T getById(PK id) throws DbException;
}
