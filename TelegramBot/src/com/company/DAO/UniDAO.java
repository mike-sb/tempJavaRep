package com.company.DAO;

import com.company.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniDAO {
    private Connection connection;

    public UniDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public void addUni(Uni uni) {

            try {
                String sql_str = "INSERT INTO uni(name) VALUES(?)";
                PreparedStatement ps = connection.prepareStatement(sql_str);
                ps.setString(1, uni.getName());
                ps.execute();

            } catch (SQLException e) {
                System.out.println("Connection Failed");
                e.printStackTrace();
                return;
            }

    }
    public boolean checkIfUniExist(Uni uni)
    {
        try {
            String sql_str = "SELECT * FROM uni WHERE name = ?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, uni.getName());
            ResultSet res = ps.executeQuery();
            String name="";
            while (res.next())
            {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return false;
        }

    }

    public void addFaculty(Uni uni, Faculty faculty)
    {
        try {
            String sql_str = "INSERT INTO uni_faculty(uni_name, faculty_name) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, uni.getName());
            ps.setString(2, faculty.getName());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void updateFaculty(Faculty faculty, int id)
    {
        try {
            String sql_str = "INSERT INTO uni_faculty(faculty_name) VALUES(?,?) WHERE id =?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, faculty.getName());
            ps.setInt(2, id);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void addUser(Uni uni,User user) {
        if(!checkIfUniExist(uni)) {
            addUni(uni);
        }
        UserDAO userDAO = new UserDAO(connection);
        userDAO.addUser(user);
        try {
            String sql_str = "INSERT INTO uni_user(uni_name,user_name) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, uni.getName());
            ps.setString(2, user.getUser_name());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }
}
