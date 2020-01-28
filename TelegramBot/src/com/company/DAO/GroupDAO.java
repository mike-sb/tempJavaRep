package com.company.DAO;

import com.company.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDAO {
    private Connection connection;

    public GroupDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addGroup(Group group) {
        try {

            String sql_str = "INSERT INTO bot_db.bot_schema.group (name,semester) VALUES(?,?)";
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

    public void updateGroup(Group group, int id) {
        try {
            String sql_str = "UPDATE bot_db.bot_schema.group(name,semester) VALUES(?,?) WHERE id=?";
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

    public boolean checkIfGroupExist(Group group) {
        try {
            String sql_str = "SELECT * FROM bot_db.bot_schema.group WHERE name =?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, group.getGroup_name());
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

    public Faculty findFaculty(String user_name) {

        try {
            String sql_str = "SELECT * FROM bot_db.bot_schema.faculty_user WHERE user_name=?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, user_name);
            ResultSet res = ps.executeQuery();
            Faculty faculty = null;

            while (res.next())//find uni where user equals temporary chatty
            {
                faculty = new Faculty(res.getString(2));
                faculty.setId(res.getInt(1));
            }

            return faculty;

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }

    }

    public void addGroup(Group group, String user_name) {
        addUser(group, user_name);
        if (!checkIfGroupExist(group)) {
            addGroup(group);//adding group to te db
            addAdmin(group, user_name);
            //adding group to the concrete faculty
            Faculty faculty = findFaculty(user_name);

            if (faculty != null) {
                FacultyDAO facultyDAO = new FacultyDAO(connection);
                facultyDAO.setGroup(faculty, group.getGroup_name());
            }
        }

    }

    private void addAdmin(Group group, String user_name) {
        try {
            String sql_str = "INSERT INTO bot_db.bot_schema.group_admin(group_name,user_name) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, group.getGroup_name());
            ps.setString(2, user_name);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    private void addUser(Group group, String user_name) {
        try {
            String sql_str = "INSERT INTO bot_db.bot_schema.group_users(group_name,user_name) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, group.getGroup_name());
            ps.setString(2, user_name);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public String getGroupName(String username) {
        try {
            String sql_str = "SELECT  * FROM bot_db.bot_schema.group_users WHERE user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, username);
            String name = "";
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                name = res.getString(2);
            }
            return name;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return "";
        }
    }

    public ArrayList<Group> getAllGroups(String user_name) {
        try {
            Faculty f = findFaculty(user_name);
            ArrayList<Group> g = new ArrayList<>();

            ArrayList<Integer> group_id = new ArrayList<>();

                String sql_str0 = "SELECT  * FROM bot_db.bot_schema.faculty_group WHERE faculty_id = ?";
                PreparedStatement ps0 = connection.prepareStatement(sql_str0);
                ps0.setInt(1, f.getId());
                ResultSet res0 = ps0.executeQuery();
                while (res0.next()) {
                    group_id.add(res0.getInt(3));
                }

            if (group_id.size() != 0) {
                for (int id : group_id) {
                    g.add(getGroupById(id));
                }
            }

            return g;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }

    private Group getGroupById(int id) {
        try {
            String sql_str = "SELECT  * FROM bot_db.bot_schema.group WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Group g = new Group(res.getString(2));
                g.setSemester(res.getInt(3));
                return g;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
