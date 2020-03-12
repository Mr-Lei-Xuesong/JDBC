package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
    insert添加一条数据
 */
public class JdbcDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //定义sql
            String sql = "insert into user values(2,'曾玉梅')";
            //获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksystem", "root", "root");
            //获取执行sql对象,Statement
            stmt = conn.createStatement();
            //执行sql
            int count = stmt.executeUpdate(sql);
            //处理结果
            System.out.println(count);
            if (count > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //避免空指针异常
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
