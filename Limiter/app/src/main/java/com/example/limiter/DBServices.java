package com.example.limiter;


import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBServices {


//        jdbc:mysql://localhost:3306/?user=root

    public static Connection openDB() throws SQLException
    {
        Connection con=null;
        String url = "jdbc:mysql://limiter-server.mysql.database.azure.com:3306/";
        String database = "tmp";
        String user = "Master@limiter-server";
        String password = "Monkey@123";
        String driver = "com.mysql.jdbc.Driver";
        try {
            StrictMode.ThreadPolicy thr = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(thr);
            Class.forName(driver).getDeclaredConstructor().newInstance();
            con=DriverManager.getConnection(url,user,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    public static void closeDB(Connection con)
    {
        try {
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int countEmail(String email)
    {
        int rowCount=0;
        try {

            Connection conn= DBServices.openDB();
            PreparedStatement stmt=conn.prepareStatement("select count (email) from Person where email='?' ");
            stmt.setString(1,email);

            ResultSet rs = stmt.executeQuery();
            rowCount = rs.last() ? rs.getRow() : 0;
            DBServices.closeDB(conn);
            return rowCount;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return -1;
        }

    }
    public static User checkLogin(String email)
    {
        String first_name,last_name,dbPass,mobile_no;
        int uid;
        User u=null;
        ResultSet rs;
        String query="select pid,pass,first_name,last_name,mobile_no from limiter.user" +
                " where email='"+email+"' ";
        try {
            Connection conn = DBServices.openDB();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            if(!rs.next())
            {
                u=null;
            }
            else {
                first_name = rs.getString("first_name");
                last_name = rs.getString("last_name");
                dbPass = rs.getString("pass");
                uid = rs.getInt("pid");
                mobile_no = rs.getString("mobile_no");
                DBServices.closeDB(conn);
                u = new User( uid,first_name, last_name, email, mobile_no, dbPass);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return u;


    }

    public static int addUser(User u)
    {
        try {
            // ResultSet rs;
            String query="insert INTO limiter.user (first_name" +
                    ",last_name,email,mobile_no,pass) values('"+u.first_name+"','"+u.last_name+"','"
                    +u.email+"','"+u.mobileNo+"','"+u.password+"')";
            Connection conn=DBServices.openDB();
            Statement st=conn.createStatement();

            int res= st.executeUpdate(query);
            DBServices.closeDB(conn);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

}
