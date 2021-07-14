package com.parallel.comm;

import java.sql.*;

/** 连接数据库
 * @author parallel
 * @create 2021-06-29-13:31
 */

public class DataBase {

    //创建数据库必备的四个常量
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/address_book";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection conn;//连接对象
    public ResultSet rs;//结果集对象
    public Statement stamt;//执行SQL对象

    //获取连接的方法
    public Connection getConn(){
        try {
            //1、加载驱动
            Class.forName(DRIVER);
            //2、获取连接
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //公共查询
    public ResultSet query(String sql){
        Connection conn = getConn();//获取连接
        try {
            //创建执行SQL的对象
            stamt = conn.createStatement();
            //执行SQL
            rs = stamt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //公共增删改的方法
    public int update(String sql){
        Connection conn = getConn();//获取连接
        int row = 0;
        try {
            stamt = conn.createStatement();
            row = stamt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(new DataBase().getConn());//com.mysql.cj.jdbc.ConnectionImpl@7f560810
    }

}
