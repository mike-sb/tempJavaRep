package com.company.DAO;

import java.sql.Connection;

public class LessonDAO {
    private Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
