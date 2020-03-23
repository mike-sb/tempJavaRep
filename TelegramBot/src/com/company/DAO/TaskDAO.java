package com.company.DAO;

import com.company.model.Day;
import com.company.model.PersonalTask;
import com.company.model.Schedule;
import com.company.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDAO {
    private Connection connection;

    public TaskDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addTask(Task task) {
        try {
            String sql_str = "INSERT INTO bot_db.bot_schema.task(task, date, lesson) VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(2, task.getDate());
            ps.setString(3, task.getLesson());
            ps.setString(1, task.getDescription());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void updateTask(Task task, int id) {
        try {

            String sql_str = "UPDATE  bot_db.bot_schema.task(task, date, lesson) VALUES(?,?,?)  WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(2, task.getDate());
            ps.setString(3, task.getLesson());
            ps.setString(1, task.getDescription());
            ps.setInt(4, id);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }
    public void addTask(Task task,String group_name) {
        addTask(task);
        try {

            String sql_str = "INSERT INTO bot_db.bot_schema.group_task(group_name, task) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, group_name);
            ps.setString(2, task.getDescription());

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void addPersonalTask(PersonalTask task,String user_name) {
        addTask(task);
        try {
            String sql_str = "INSERT INTO bot_db.bot_schema.user_task(user_name, task_name) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, user_name);
            ps.setString(2, task.getDescription());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }
}
