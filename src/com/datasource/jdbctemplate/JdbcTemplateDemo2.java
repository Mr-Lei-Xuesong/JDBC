package com.datasource.jdbctemplate;

import com.domain.Student;
import com.utils.DruidUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class JdbcTemplateDemo2 {

    private JdbcTemplate jt = new JdbcTemplate(DruidUtils.getDataSource());

    @Test//添加记录
    public void add() {
        String sql = "insert into account values(null,?,?)";
        int update = jt.update(sql, "linqingxia", "1300");
        System.out.println(update);
    }

    @Test//删除记录
    public void del() {
        String sql = "delete from account where id=?";
        int update = jt.update(sql, 2);
        System.out.println(update);
    }

    @Test//修改记录
    public void update() {
        String sql = "update student set entrance = ? where studentno = ?";
        int update = jt.update(sql, 1000, "18122221324");
        System.out.println(update);
    }

    @Test//查询一条记录
    public void select() {
        String sql = "select * from student where studentno=?";
        Map<String, Object> map = jt.queryForMap(sql, "18122221324");
        System.out.println(map);
    }

    @Test//查询所有记录
    public void select1() {
        String sql = "select * from student";
        List<Map<String, Object>> list = jt.queryForList(sql);
        ListIterator<Map<String, Object>> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void select2() {
        String sql = "select * from student";
        List<Student> list = jt.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
                Student s = new Student();

                String studentno = rs.getString("studentno");
                String sname = rs.getString("sname");
                String sex = rs.getString("sex");
                Date birthdate = rs.getDate("birthdate");
                int entrance = rs.getInt("entrance");
                String phon = rs.getString("phone");
                String email = rs.getString("email");

                s.setStudentno(studentno);
                s.setSname(sname);
                s.setSex(sex);
                s.setBirthdate(birthdate);
                s.setEntrance(entrance);
                s.setPhone(phon);
                s.setEmail(email);

                return s;
            }
        });
        for (Student s:list){
            System.out.println(s);
        }
    }

    @Test
    public void select2_1() {
        String sql = "select * from student";
        List<Student> list = jt.query(sql, new BeanPropertyRowMapper<>(Student.class));
        for (Student s:list){
            System.out.println(s);
        }
    }

    @Test
    public void select3(){
        String sql="select count(studentno) from student";
        Long aLong = jt.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }
}
