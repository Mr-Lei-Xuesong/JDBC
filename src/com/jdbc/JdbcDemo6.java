package com.jdbc;

import com.domain.Student;
import com.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class JdbcDemo6 {
    public static void main(String[] args) {
        /*List<Student> all = new JdbcDemo6().findAll();
        System.out.println(all);*/
        List<Student> all = findAll2();
        ListIterator<Student> list = all.listIterator();
        while (list.hasNext()) {
            Student next = list.next();
            System.out.println(next.getStudentno() + "," + next.getSname() + "," + next.getSex() + "," + next.getBirthdate() + "," + next.getEntrance() + "," + next.getPhone() + "," + next.getEmail());
        }
    }

    public static List<Student> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> list = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //创建数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///booksystem", "root", "root");
            //创建sql执行对象
            stmt = conn.createStatement();
            //创建sql语句
            String sql = "select * from student";
            //执行sql语句
            rs = stmt.executeQuery(sql);
            //遍历结果集，封装对象，装在集合
            list = new ArrayList<>();
            Student s = null;
            //获取数据
            while (rs.next()) {
                String studentno = rs.getString("studentno");
                String sname = rs.getString("sname");
                String sex = rs.getString("sex");
                Date birthdate = rs.getDate("birthdate");
                int entrance = rs.getInt("entrance");
                String phon = rs.getString("phone");
                String email = rs.getString("email");

                s = new Student();

                s.setStudentno(studentno);
                s.setSname(sname);
                s.setSex(sex);
                s.setBirthdate(birthdate);
                s.setEntrance(entrance);
                s.setPhone(phon);
                s.setEmail(email);

                list.add(s);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
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
        return list;
    }

    public static List<Student> findAll2() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> list = null;
        try {
            //注册驱动
            //创建数据库连接对象
            conn = JdbcUtils.getConnection();
            //创建sql执行对象
            stmt = conn.createStatement();
            //创建sql语句
            String sql = "select * from student";
            //执行sql语句
            rs = stmt.executeQuery(sql);
            //遍历结果集，封装对象，装在集合
            list = new ArrayList<>();
            Student s = null;
            //获取数据
            while (rs.next()) {
                String studentno = rs.getString("studentno");
                String sname = rs.getString("sname");
                String sex = rs.getString("sex");
                Date birthdate = rs.getDate("birthdate");
                int entrance = rs.getInt("entrance");
                String phon = rs.getString("phone");
                String email = rs.getString("email");

                s = new Student();

                s.setStudentno(studentno);
                s.setSname(sname);
                s.setSex(sex);
                s.setBirthdate(birthdate);
                s.setEntrance(entrance);
                s.setPhone(phon);
                s.setEmail(email);

                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs,stmt,conn);
        }
        return list;
    }
}
