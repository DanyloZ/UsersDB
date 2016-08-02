package usersDB.dbdataproccessing;

import usersDB.main.User;
import usersDB.templater.PageGenerator;

import java.sql.*;
import java.util.ArrayList;

public class DBDataProcessing {
    private static final String url = "jdbc:mysql://localhost:3306/test_db?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "genius";
    private static String getUsersQuery = "SELECT Id, Name, Date_of_Birth FROM user";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static ArrayList<User> getUsersFromDB() {

        ArrayList<User> usersFromDB = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(getUsersQuery);
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate());
                usersFromDB.add(user);
            }
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usersFromDB;
    }

    public static void saveUsersToDB() {
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("INSERT INTO user "
                    +"(Id, Name, Date_of_Birth) "
                    +"VALUES (?,?,?)");
            for (User user : (ArrayList<User>)PageGenerator.getPageVariables().get("users")) {
                ps.setInt(1, user.getId());
                ps.setString(2, user.getName());
                ps.setDate(3, java.sql.Date.valueOf(user.getDateOfBirth()));
                ps.executeUpdate();
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
