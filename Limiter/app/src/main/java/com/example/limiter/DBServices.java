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
        String url = "SQL_URL";
        String database = "DATA_BASE";
        String user = "USER_NAME";
        String password = "PASSWORD";
        String driver = "com.mysql.jdbc.Driver";
        try {
            StrictMode.ThreadPolicy thr = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(thr);
            Class.forName(driver).getDeclaredConstructor().newInstance();
            con=DriverManager.getConnection(url+database,user,password);
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

    public static boolean checkEmail(String email)
    {
        boolean isPresent=true;
        String query="select email from Limiter.User where email='"+email+"'" ;


        try {
            Connection conn = DBServices.openDB();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(query);

            if(!rs.next())
                isPresent=false;
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return isPresent;

    }
    public static User checkLogin(String email)
    {
        String first_name,last_name,dbPass,mobile_no;
        int uid;
        User u=null;
        ResultSet rs;
        String query="select uid,pass,first_name,last_name,mobile_no from limiter.user" +
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
                uid = rs.getInt("uid");
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
