package com.company.DAO;

import com.company.model.Day;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DayDAO {

    private Connection connection;

    public DayDAO( Connection connection) {
        setConnection(connection);

    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addDay(Day day) {
        try {

            String sql_str = "INSERT INTO days(name) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, day.getName());

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

}
