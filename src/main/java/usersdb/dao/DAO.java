package usersdb.dao;

import usersdb.buffer.Buffer;
import usersdb.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class DAO {
    private static final String url = "jdbc:mysql://localhost:3306/test_db?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "genius";

    private static Connection con;


    public static ArrayList<User> getUsers() {

        ArrayList<User> usersFromDB = new ArrayList<>();
        try {
            String getUsersQuery = "SELECT Id, Name, Date_of_Birth FROM user";
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getUsersQuery);
            while (rs.next()) {
                User user = new User(String.valueOf(rs.getInt(1)), rs.getString(2), rs.getDate(3).toLocalDate());
                usersFromDB.add(user);
            }
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usersFromDB;
    }

    public static void saveUsers() {
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("INSERT INTO user "
                    +"(Name, Date_of_Birth) "
                    +"VALUES (?,?)");
            for (User user : Buffer.getBuffer().getNewUsers()) {
                ps.setString(1, user.getName());
                ps.setDate(2, java.sql.Date.valueOf(user.getDateOfBirth()));
                ps.executeUpdate();
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeUser(String id) {
        try {
            String removeUserQuery = "DELETE FROM user WHERE Id = " + id;
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(removeUserQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
