package com.company.DAO;

import com.company.model.*;
import com.google.inject.internal.cglib.proxy.$Factory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacultyDAO {
    private Connection connection;

    public FacultyDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addFaculty(Faculty faculty) {
        try {

            String sql_str = "INSERT INTO bot_db.bot_schema.faculty(name) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, faculty.getName());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void addFaculty(Faculty faculty, String user_name) {
            if(!checkIfFacultyExist(faculty)) {
            addFaculty(faculty);
            }//adding faculty to te db
            //adding faculty to the concrete Uni
        try {
            String sql_str0 = "SELECT * FROM bot_db.bot_schema.uni_user WHERE user_name=?";
            PreparedStatement ps0 = connection.prepareStatement(sql_str0);
            ps0.setString(1, user_name);
            ps0.execute();

            Uni uni = findUni(user_name);//find uni where user equals temporary chatty

          regUserOnFaculty(faculty,user_name);

            if(uni!=null) {
                UniDAO uniDAO = new UniDAO(connection);
                uniDAO.addFaculty(uni, faculty);
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    private void regUserOnFaculty(Faculty faculty, String user_name) {
        if(!checkIfUserExist(faculty,user_name)) {
            try {
                String sql_str = "INSERT INTO bot_db.bot_schema.faculty_user(faculty_name, user_name) VALUES(?,?)";
                PreparedStatement ps = connection.prepareStatement(sql_str);
                ps.setString(1, faculty.getName());
                ps.setString(2, user_name);
                ps.execute();
            } catch (SQLException e) {
                System.out.println("Connection Failed");
                e.printStackTrace();
                return;
            }
        }
    }


    public boolean checkIfFacultyExist(Faculty faculty) {
        try {
            String sql_str = "SELECT * FROM bot_db.bot_schema.faculty WHERE name =?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, faculty.getName());
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkIfUserExist(Faculty faculty, String username) {
        try {
            String sql_str = "SELECT * FROM bot_db.bot_schema.faculty_user WHERE faculty_name =? AND user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, faculty.getName());
            ps.setString(2, username);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return false;
        }
    }

    private Uni findUni(String user_name)
    {
        try {
        String sql_str = "SELECT * FROM bot_db.bot_schema.uni_user WHERE user_name=?";
        PreparedStatement ps = connection.prepareStatement(sql_str);
        ps.setString(1, user_name);
        ResultSet res =  ps.executeQuery();
        Uni uni = null;

        while (res.next())
        {
            uni =new Uni(res.getString(2));
        }

       return uni;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }

    public void updateFaculty(Faculty faculty, int id) {
        try {
            String sql_str = "UPDATE bot_db.bot_schema.faculty(name) VALUES(?) WHERE id=?";
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

    public void setGroup(Faculty faculty, String group_name) {
        try {
            String sql_str1 = "SELECT * FROM bot_db.bot_schema.faculty WHERE name = ?";
            PreparedStatement ps1 = connection.prepareStatement(sql_str1);
            ps1.setString(1, faculty.getName());
            ResultSet res = ps1.executeQuery();
            int faculty_id = -1;
            while (res.next()) {
                faculty_id = res.getInt(1);
            }

            String sql_str2 = "SELECT * FROM bot_db.bot_schema.group WHERE name = ?";
            PreparedStatement ps2 = connection.prepareStatement(sql_str2);
            ps2.setString(1, group_name);
            res = ps2.executeQuery();
            int group_id=-1;
            while (res.next()) {
                group_id = res.getInt(1);
            }

            if (faculty_id!=-1&&group_id!=-1) {
                String sql_str = "INSERT INTO bot_db.bot_schema.faculty_group(faculty_id,group_id) VALUES(?,?)";
                PreparedStatement ps3 = connection.prepareStatement(sql_str);
                ps3.setInt(1, faculty_id);
                ps3.setInt(2, group_id);
                ps3.execute();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }
    public ArrayList<Faculty> getAllFaculties(String user_name)
    {
        ArrayList<Faculty> faculties = new ArrayList<>();
        try {
           Uni uni= findUni(user_name);

            String sql_str = "SELECT * FROM bot_db.bot_schema.uni_faculty WHERE uni_name =?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, uni.getName());
           ResultSet res = ps.executeQuery();
           while (res.next())
           {
               faculties.add(new Faculty(res.getString(3)));
           }
           return faculties;

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }

    }

}
