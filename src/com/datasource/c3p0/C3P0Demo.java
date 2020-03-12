package com.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo {
    public static void main(String[] args) throws SQLException {
        //创建核心对象
        DataSource ds = new ComboPooledDataSource();
        //获取数据库连接对象
        Connection conn = ds.getConnection();
        for (int i = 0; i < 10; i++) {
            System.out.println(i+":"+conn);
        }
    }
}
