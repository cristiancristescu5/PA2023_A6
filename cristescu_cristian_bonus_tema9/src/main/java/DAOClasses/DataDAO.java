package DAOClasses;
import EntitiesJDBC.*;
import java.sql.SQLException;
import java.util.List;

public abstract class DataDAO {
    public abstract Entity findByName(String name) throws SQLException;
    public abstract Entity findById(int id) throws SQLException;
    public abstract List<Entity> findAll() throws SQLException;
}
