package com.jdbc;

import com.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDemo8 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //创建数据库连接
            conn = JdbcUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //定义sql
            String sql1 = "update account set balance=balance-? where id=?";
            String sql2 = "update account set balance=balance+? where id=?";
            //获取执行sql对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //设置参数
            pstmt1.setString(1, "500");
            pstmt1.setInt(2, 1);

            pstmt2.setString(1, "500");
            pstmt2.setInt(2, 2);

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            //提交事务
            conn.commit();
        } catch (Exception e) {
            //事务回滚
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.close(pstmt1, conn);
            JdbcUtils.close(pstmt2, null);
        }

    }
}
