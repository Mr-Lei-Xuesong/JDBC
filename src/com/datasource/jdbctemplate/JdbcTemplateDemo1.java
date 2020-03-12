package com.datasource.jdbctemplate;

import com.utils.DruidUtils;
import com.utils.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/*
    JdbcTemplate入门
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        JdbcTemplate jt=new JdbcTemplate(DruidUtils.getDataSource());
        String sql="update account set balance = 5000 where id=?";
        int account = jt.update(sql, 1);
        System.out.println(account);
    }
}
