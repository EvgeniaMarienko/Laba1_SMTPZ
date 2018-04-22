import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public interface DAO <E> {
    ArrayList<E> getAll();
    E getEntityByID(String  id);
    E update(E entity);
    boolean delete(String name);
    boolean create(E entity);
}