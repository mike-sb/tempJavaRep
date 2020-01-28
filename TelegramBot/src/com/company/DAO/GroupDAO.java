package com.company.DAO;

import com.company.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDAO {
    private Connection connection;

    public GroupDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addGroup(Group group)
    {
        try {

            String sql_str = "INSERT INTO group(name,semester) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, group.getGroup_name());
            ps.setInt(2, group.getSemester());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void updateGroup(Group group, int id)
    {
        try {
            String sql_str = "UPDATE group(name,semester) VALUES(?,?) WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, group.getGroup_name());
            ps.setInt(2, group.getSemester());
            ps.setInt(3, id);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void addGroup(Group group, String user_name) {
        addGroup(group);//adding group to te db
        addUser(group,user_name);
        //adding group to the concrete faculty
        try {
            String sql_str = "SELECT * FROM faculty_user WHERE user_name=?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, user_name);
            ResultSet res =  ps.executeQuery();
            Faculty faculty = null;

            while (res.next())//find uni where user equals temporary chatty
            {
                faculty =new Faculty(res.getString(2));
            }

            if(faculty!=null) {
               FacultyDAO facultyDAO = new FacultyDAO(connection);
               facultyDAO.setGroup(faculty,group.getGroup_name());
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    private void addUser(Group group, String user_name) {
        try {
            String sql_str = "INSERT INTO group_users(group_name,user_name) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, group.getGroup_name());
            ps.setString(2, user_name);
            ps.execute();
        }
        catch (SQLException e) {
                System.out.println("Connection Failed");
                e.printStackTrace();
                return;
            }
        }

    public String getGroupName(String username) {
        try {
            String sql_str = "SELECT  * FROM group_users WHERE user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, username);
            String name= "";
           ResultSet res = ps.executeQuery();
           while (res.next())
           {
               name = res.getString(2);
           }
           return name;
        }
        catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return "";
        }
    }
}
