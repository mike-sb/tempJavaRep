package com.company.DAO;

import com.company.model.*;
import com.google.inject.internal.asm.$ClassTooLargeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonDAO {
    private Connection connection;

    public LessonDAO(Connection connection) {
        setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addLesson(Lesson lesson) {
        try {

            String sql_str = "INSERT INTO bot_db.bot_schema.lessons(name,time,room, week_type, day) VALUES(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, lesson.getName());
            ps.setString(2, lesson.getTime());
            ps.setString(3, lesson.getRoom());
            ps.setString(4, lesson.getWeekType().toString());
            ps.setString(5, lesson.getDay().getName());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public int getLessonId(Lesson lesson) {
        try {
            String sql_str = "SELECT * FROM  bot_db.bot_schema.lessons WHERE name =?, time =?, room= ?, week_type = ?, day = ? ";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, lesson.getName());
            ps.setString(2, lesson.getTime());
            ps.setString(3, lesson.getRoom());
            ps.setString(4, lesson.getWeekType().toString());
            ps.setString(5, lesson.getDay().getName());
            ResultSet res = ps.executeQuery();
            int lesson_id=-1;
            while (res.next())
            {
             lesson_id  = res.getInt(1);
            }
            return lesson_id;
        }
        catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return -1;
        }

    }

    public void addLesson(Lesson lesson, String  group_name) {
        addLesson(lesson);
        try {
            int lesson_id = getLessonId(lesson);
            if (lesson_id != -1) {
                String sql_str = "INSERT INTO bot_db.bot_schema.group_lesson(group_name,lesson_id) VALUES(?,?)";
                PreparedStatement ps = connection.prepareStatement(sql_str);
                ps.setString(1, group_name);
                ps.setInt(2, lesson_id);
                ps.execute();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void updateLesson(Lesson lesson, int id) {
        try {
            String sql_str = "UPDATE bot_db.bot_schema.lessons(name,time,room,week_type,day) VALUES(?,?,?,?,?) WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql_str);
            ps.setString(1, lesson.getName());
            ps.setString(2, lesson.getTime());
            ps.setString(3, lesson.getRoom());
            ps.setString(4, lesson.getWeekType().toString());
            ps.setString(5, lesson.getDay().getName());
            ps.setInt(4, id);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }
}
