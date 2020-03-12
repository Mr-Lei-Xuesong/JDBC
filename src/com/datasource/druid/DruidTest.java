package com.datasource.druid;
/*
    使用Druid工具类
 */

import com.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DruidTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获取连接
            conn = DruidUtils.getConnection();
            //定义sql
            String sql = "insert into account values(null,?,?)";
            //获取执行sql对象
            pstmt = conn.prepareStatement(sql);
            //给占位符赋值
            pstmt.setString(1, "leixuesong");
            pstmt.setString(2, "2000");
            //执行sql
            int account = pstmt.executeUpdate();
            //判断
            if (account > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtils.close(pstmt,conn);
        }

    }
}
