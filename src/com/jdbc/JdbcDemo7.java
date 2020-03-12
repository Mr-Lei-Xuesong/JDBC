package com.jdbc;

import com.utils.JdbcUtils;

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo7 {
    public static void main(String[] args) {
        //键盘录入，接受用户名和密码
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = sc.nextLine();
        System.out.println("请输入密码:");
        String password = sc.nextLine();

        //调用方法
        boolean flag = new JdbcDemo7().login2(username, password);

        if (flag){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

    }

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        //连接数据库
        try {
            conn= JdbcUtils.getConnection();
            String sql="select * from user where username='"+username+"' and password='"+password+"'";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,stmt,conn);
        }
        return false;
    }

    //使用PreparedStatement
    public boolean login2(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn=null;
        PreparedStatement pstmt =null;
        ResultSet rs=null;
        //连接数据库
        try {
            conn= JdbcUtils.getConnection();
            String sql="select * from user where username=? and password=?";
            //预编译sql
            pstmt = conn.prepareStatement(sql);
            //给占位符赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //返回结果集
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
        return false;
    }
}
