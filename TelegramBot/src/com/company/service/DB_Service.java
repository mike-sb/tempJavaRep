package com.company.service;

import com.company.DAO.*;
import com.company.model.*;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class DB_Service {

    private static DB_Service instance;
    private DayDAO day;
    private FacultyDAO faculty;
    private GroupDAO group;
    private LessonDAO lesson;
    private ScheduleDAO schedule;
    private TaskDAO task;
    private UniDAO uni;
    private UserDAO user;
    private WeekTypeDAO week;

    public static DB_Service getInstance() {
        if (instance == null) {
            instance = new DB_Service();
        }
        return instance;
    }

    private String DB_URL;
    private String USER;
    private String PASS;
    private Connection connection;
    private Statement statement;

    public DB_Service() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DB_URL = properties.getProperty("url");
        USER = properties.getProperty("username");
        PASS = properties.getProperty("password");


        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            day = new DayDAO();
            faculty = new FacultyDAO();
            group = new GroupDAO();
            lesson = new LessonDAO();
            schedule = new ScheduleDAO();
            task = new TaskDAO();
            uni = new UniDAO();
            user = new UserDAO();
            week = new WeekTypeDAO();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }


    public DayDAO getDay() {
        return day;
    }

    public FacultyDAO getFaculty() {
        return faculty;
    }

    public GroupDAO getGroup() {
        return group;
    }

    public LessonDAO getLesson() {
        return lesson;
    }

    public ScheduleDAO getSchedule() {
        return schedule;
    }

    public TaskDAO getTask() {
        return task;
    }

    public UniDAO getUni() {
        return uni;
    }

    public UserDAO getUser() {
        return user;
    }

    public WeekTypeDAO getWeek() {
        return week;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}