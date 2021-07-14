package com.parallel.dao;

import com.parallel.comm.DataBase;
import com.parallel.entity.User;
import com.parallel.utils.UUIDUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author parallel
 * @create 2021-06-29-13:35
 */
//数据访问层，操作数据库
public class UserDao extends DataBase {

    /**
     *   登陆
     * @param name  姓名
     * @param pwd   密码
     * @return
     */
    public User login(String name, String pwd){
        String sql = "SELECT * FROM user WHERE name='"+name+"' AND PASSWORD='"+pwd+"'";//拼接SQL
        ResultSet rs = query(sql);//执行SQL返回结果
        User stu = null;
        try {
            if(rs.next()){
                stu = new User();
                //stu赋值
                stu.setUid(rs.getString("uid"));
                stu.setName(rs.getString("name"));
                stu.setPassword(rs.getString("password"));
                stu.setSex(rs.getString("sex"));
                stu.setPhone(rs.getString("phone"));
                stu.setState(rs.getString("state"));
                stu.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stu;
    }

    /**
     *   增加用戶（注册）
     * @param stu  用戶对象
     * @return
     */
    public boolean saveUser(User stu){
        String sql = "INSERT INTO user(uid,name,PASSWORD,sex,phone,address) VALUES('"+stu.getUid()
                +"','"+stu.getName()+"','"+stu.getPassword()+
                "','"+stu.getSex()+"','"+stu.getPhone()+"','"+stu.getAddress()+"')";
        int row = update(sql);
        return row > 0;
    }

    /**
     * 查询所有信息
     * @return
     */
    public List<User> findByUser(){
        String sql = "select * from user";
        ResultSet rs = super.query(sql);
        List<User> list = new ArrayList<>();
        User stu = null;
        try {
            while(rs.next()){
                stu = new User();
                //stu赋值
                stu.setUid(rs.getString("uid"));
                stu.setName(rs.getString("name"));
                stu.setPassword(rs.getString("password"));
                stu.setSex(rs.getString("sex"));
                stu.setPhone(rs.getString("phone"));
                stu.setState(rs.getString("state"));
                stu.setAddress(rs.getString("address"));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 修改信息
     * @param stu
     * @return
     */
    public boolean updateInformation(User stu){
        if("1".equals(stu.getState())){//如果信息已录入，返回false!
            return false;
        }
        String sql = "UPDATE user SET sex='"+stu.getSex()+"',phone='"+stu.getPhone()
                +"',address='"+stu.getAddress()+"',state=1 where uid='"+stu.getUid()+"'";
        int row = super.update(sql);
        return row > 0;
    }

    /**
     * 删除
     * @param uid
     * @return
     */
    public boolean deleteInformation(String uid){
        String sql = "delete from user where uid='"+uid+"'";
        return super.update(sql) > 0;
    }

    //通过SID查询
    //模糊查询（姓名查询）
    public List<User> findByName(String content){
        String sql = "select * from user where name like '%"+content+"%'";
        ResultSet rs = super.query(sql);
        List<User> list = new ArrayList<>();
        User stu = null;
        try {
            while(rs.next()){
                stu = new User();
                //stu赋值
                stu.setUid(rs.getString("uid"));
                stu.setName(rs.getString("name"));
                stu.setPassword(rs.getString("password"));
                stu.setSex(rs.getString("sex"));
                stu.setPhone(rs.getString("phone"));
                stu.setState(rs.getString("state"));
                stu.setAddress(rs.getString("address"));

                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //通过SID查询
    public User findBySid(String uid){
        String sql = "select * from user where uid='"+uid+"'";
        ResultSet rs = super.query(sql);
        User stu = null;
        try {
            if(rs.next()){
                stu = new User();
                //stu赋值
                stu.setUid(rs.getString("uid"));
                stu.setName(rs.getString("name"));
                stu.setPassword(rs.getString("password"));
                stu.setSex(rs.getString("sex"));
                stu.setPhone(rs.getString("phone"));
                stu.setState(rs.getString("state"));
                stu.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  stu;
    }

    public static void main(String[] args) {
        UserDao dao = new UserDao();

        List<User> list = dao.findByName("刘");
        for (User student : list) {
            System.out.println(student.toString());
        }

    }

}
