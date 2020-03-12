package com.datasource.c3p0;

import com.utils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    使用C3P0工具类
 */
public class C3P0Test {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement pstmt =null;
        try {
            //获取连接
            conn = C3P0Utils.getConnection();
            //定义sql
            String sql="insert into account values(null,?,?)";
            //获取执行sql对象
            pstmt = conn.prepareStatement(sql);
            //给占位符赋值
            pstmt.setString(1,"zengyumei");
            pstmt.setString(2,"3000");
            //执行sql
            int account = pstmt.executeUpdate();
            //判断
            if (account>0){
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Utils.close(null,pstmt,conn);
        }

    }
}
