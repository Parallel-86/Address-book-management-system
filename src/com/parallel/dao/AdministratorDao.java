package com.parallel.dao;

import com.parallel.comm.DataBase;
import com.parallel.entity.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author parallel
 * @create 2021-06-29-22:43
 */
public class AdministratorDao extends DataBase {

    /**
     *    登陆
     * @param name 姓名
     * @param pwd  密码
     * @return
     */
    public Administrator login(String name, String pwd){
        String sql = "select * from administrator where name='"+name+"' and password='"+pwd+"'";
        ResultSet rs = super.query(sql); //返回结果集对象
        Administrator t = null;
        try {
            if(rs.next()){
                t = new Administrator();
                t.setAid(rs.getString("aid"));
                t.setName(rs.getString("name"));
                t.setPassword(rs.getString("password"));
                t.setPhone(rs.getString("phone"));
                t.setSex(rs.getString("sex"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     *    查询
     * @return
     */
    public List<Administrator> findByAdministrator(){
        String sql = "select * from administrator";
        ResultSet rs = super.query(sql);
        List<Administrator> list = new ArrayList<>();
        Administrator t = null;
        try {
            while(rs.next()){
                t = new Administrator();
                t.setAid(rs.getString("aid"));
                t.setName(rs.getString("name"));
                t.setPassword(rs.getString("password"));
                t.setPhone(rs.getString("phone"));
                t.setSex(rs.getString("sex"));

                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
