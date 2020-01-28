package com.company.DAO;

import com.company.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public class ScheduleDAO {
//    private Connection connection;
//
//    public ScheduleDAO(Connection connection) {
//        setConnection(connection);
//    }
//
//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void addSchedule(Schedule schedule) {
//        try {
//
//            for (Day d : schedule.getDays()) {
//
//                int day_id = getDayId(d);
//
//                if (day_id != -1) {
//                    String sql_str = "INSERT INTO schedule(day_id, group_name) VALUES(?,?)";
//                    PreparedStatement ps = connection.prepareStatement(sql_str);
//                    ps.setInt(1, day_id);
//                    ps.setString(2, d.getLessons());
//                    ps.execute();
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Connection Failed");
//            e.printStackTrace();
//            return;
//        }
//    }
//
//    public void addSchedule(Schedule schedule, String group_name) {
//        addSchedule(schedule);
//        try {
//
//            String sql_str = "INSERT INTO schedule_group(group_name, schedule_name) VALUES(?,?)";
//            PreparedStatement ps = connection.prepareStatement(sql_str);
//            ps.setString(1, group_name);
//            ps.setString(2, );
//            ps.execute();
//
//        } catch (SQLException e) {
//            System.out.println("Connection Failed");
//            e.printStackTrace();
//            return;
//        }
//    }
//    private int
//
//    public void updateSchedule(Schedule schedule, int id) {
//        try {
//            for (Day d : schedule.getDays()) {
//
//                int day_id = getDayId(d);
//
//                String sql_str = "UPDATE schedule(day_id,group_id) VALUES(?,?) WHERE id=?";
//                PreparedStatement ps = connection.prepareStatement(sql_str);
//                ps.setInt(1, day_id);
//                ps.setString(2, d.getName());
//
//                ps.setInt(3, id);
//                ps.execute();
//            }
//
//
//        } catch (SQLException e) {
//            System.out.println("Connection Failed");
//            e.printStackTrace();
//            return;
//        }
//    }
//
//    private int getDayId(Day d) {
//        try {
//            String sql_str1 = "SELECT * FROM days WHERE name = ?, weektype = ?";
//            PreparedStatement ps1 = connection.prepareStatement(sql_str1);
//            ps1.setString(1, d.getName());
//            ps1.setString(2, d.getWeekType().toString());
//            ResultSet res = ps1.executeQuery();
//            int day_id = -1;
//            while (res.next()) {
//                day_id = res.getInt(1);
//            }
//            return day_id;
//        } catch (SQLException e) {
//            System.out.println("Connection Failed");
//            e.printStackTrace();
//            return -1;
//        }
//    }
}
