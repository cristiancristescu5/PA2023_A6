package org.example.DAOClasses;

import org.example.Classes.Entity;
import org.example.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

public abstract class DataDAO {
    public abstract Entity findByName(String name, Database database) throws SQLException;
    public abstract Entity findById(int id, Database database) throws SQLException;
}
