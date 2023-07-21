package Controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.User;

public class SystemFunction {
    static DatabaseHandler conn = new DatabaseHandler();
    public static boolean checkEmailPass(String email, String pass){
        conn.connect();
        String query = "SELECT * FROM user";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if (email.equals(rs.getString("email")) && pass.equals(rs.getString("password"))){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkPassAuth(String pass){
        if (pass.length() < 8){return false;}
        return true;
    }

    public static User getUserByEmail(String email){
        conn.connect();
        String query = "SELECT * FROM user WHERE email='" + email + "'";
        try {
            User user = new User();
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhoto(rs.getString("photo"));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean inputNewUser(String name, String email, String password, int gender, Date birthday, String photo){
        conn.connect();
        String query = "INSERT INTO user (name, email, password, gender, birthday, photo) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setInt(4, gender);
            stmt.setDate(5, birthday);
            stmt.setString(6, photo);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
