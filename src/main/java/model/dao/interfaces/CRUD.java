package model.dao.interfaces;
import java.util.List;

public interface CRUD<T> {

    public void register(T a) throws Exception;

    public void modify(T a) throws Exception;

    public void remove(T a) throws Exception;

    public List<T> getOne(int id) throws Exception;

    public List<T> getAll() throws Exception;
}