package org.example.DAOClasses;

import org.example.Classes.Entity;
import org.example.Database;

import java.sql.SQLException;
import java.util.List;

public abstract class DataDAO {
    public abstract Entity findByName(String name, Database database) throws SQLException;
    public abstract Entity findById(int id, Database database) throws SQLException;
    public abstract List<Entity> findAll(Database database) throws SQLException;
}
