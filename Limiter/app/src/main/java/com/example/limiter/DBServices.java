package com.example.limiter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBServices {

    public static Connection connectDatabase () throws SQLException
    {

//        jdbc:mysql://localhost:3306/?user=root

        String url = "jdbc:mysql://MYSQL8001.site4now.net";
        String user = "a84e37_limiter";
        String password = "mahismati5";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, user, password);
    }

  public static int  countEmail(String email)
    {
        int rowCount=0;
        try {

            Connection conn=connectDatabase();
            PreparedStatement stmt=conn.prepareStatement("select count (email) from Person where email='?' ");
            stmt.setString(1,email);

            ResultSet rs = stmt.executeQuery();
            rowCount = rs.last() ? rs.getRow() : 0;
            return rowCount;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return -1;
        }

    }

    public static int addUser(User u)
    {


        try {

            Connection conn=connectDatabase();
            PreparedStatement stmt=conn.prepareStatement("insert INTO Person values('?','?','?','?','?' )");
            stmt.setString(1,u.first_name);
            stmt.setString(2,u.last_name);
            stmt.setString(3,u.email);
            stmt.setString(4, u.mobileNo);
            stmt.setString(5,u.password);

            int res= stmt.executeUpdate();

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

}
