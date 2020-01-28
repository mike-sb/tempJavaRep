package com.company.DAO;

import com.company.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void addUser(User user) {
        try {
            String sql_str = "INSERT INTO users(id) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, user.getUser_name());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }
}
