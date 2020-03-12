package com.jdbc;

import java.sql.*;

public class JdbcDemo5 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //创建数据库连接
            conn = DriverManager.getConnection("jdbc:mysql:///booksystem", "root", "root");
            //创建sql执行对象
            stmt = conn.createStatement();
            //创建sql
            String sql = "select * from user";
            //执行sql语句
            resultSet = stmt.executeQuery(sql);
            /*resultSet.next();
            int id = resultSet.getInt(1);
            String name = resultSet.getString("name");
            System.out.println(id + "," + name);
            resultSet.next();
            int id1 = resultSet.getInt(1);
            String name1 = resultSet.getString("name");
            System.out.println(id1 + "," + name1);*/
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                System.out.println(id+","+name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
