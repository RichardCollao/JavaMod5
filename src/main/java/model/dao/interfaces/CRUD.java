package model.dao.interfaces;
import java.util.List;

public interface CRUD<T> {

    public void create(T a) throws Exception;

    public void update(T a) throws Exception;

    public void delete(T a) throws Exception;

    public T readOne(int id) throws Exception;

    public List<T> readAll() throws Exception;
}