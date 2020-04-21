package com.example.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.data.User;
import com.example.dbUtil.MySqlConnection;

public class UserDB {

    private static final UserDB userDB = new UserDB();
    private static final MySqlConnection mySqlConnection = new MySqlConnection();

    public static UserDB getInstance() {
        return userDB;
    }

    private UserDB(){

    }

    public String getPhone(int userId) {
        User user = getUser(userId);
        return user.getPhoneNumber();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();

        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from userData";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                User user = new User(id, userPhone, userName);
                users.add(user);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return users;
    }

    public User getUser(int userId) {
        User user = null;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from userData where id=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                user = new User(id, userPhone, userName);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return user;

    }


}
