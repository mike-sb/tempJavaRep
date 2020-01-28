package com.company.DAO;

import java.sql.Connection;

public class WeekTypeDAO {
    private Connection connection;

    public WeekTypeDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
