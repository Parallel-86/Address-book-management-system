package com.parallel.module.login;

import com.parallel.dao.AdministratorDao;
import com.parallel.dao.UserDao;
import com.parallel.entity.Administrator;
import com.parallel.entity.User;
import com.parallel.utils.AlertUtils;
import com.parallel.utils.MapUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

//登录
public class Login implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private TextField pwd;

    @FXML
    private RadioButton user;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton administrator;

    private int choose = 1;

    @Override  //加载
    public void initialize(URL location, ResourceBundle resources) {
        user.setOnAction(event -> choose=1);
        administrator.setOnAction(event -> choose=0);
    }


    @FXML //登陆!!
    void login(ActionEvent event) throws Exception {
        //非空判断
        if(name.getText().trim().equals("")){
            AlertUtils.showAlert("错误","请检查你的输入","姓名不能为空！！");
            return;
        }
        if(pwd.getText().trim().equals("")){
            AlertUtils.showAlert("错误","请检查你的输入","密码不能为空！！");
            return;
        }
        //判断
        switch (choose){
            case 1:
                //用戶登录
                UserDao dao = new UserDao();//获取数据访问层对象
                //调用登录的方法
                User stu = dao.login(name.getText(), pwd.getText());
                if(stu != null){
                   //保存
                    MapUtils.map.put("sessionStu",stu);
                    Scene scene = new Scene(FXMLLoader.load(getClass()
                            .getResource("/com/parallel/module/user/userMain.fxml")));//user的首页
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.hide();
                    stage.setTitle("用戶主页");
                    stage.setScene(scene);
                    stage.show();
                }else{
                    AlertUtils.showAlert("ERROR","请检查你的输入",
                            "用户姓名或密码错误，请重新登录！");
                }
                break;
            case 0:
                AdministratorDao tDao =  new AdministratorDao();
                Administrator t = tDao.login(name.getText(), pwd.getText());
                if(t != null){
                    Scene scene = new Scene(FXMLLoader.load(getClass()
                            .getResource("/com/parallel/module/administrator/administratorMain.fxml")));//user的首页
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.hide();
                    stage.setTitle("管理員主页");
                    stage.setScene(scene);
                    stage.show();
                }else{
                    AlertUtils.showConfirm("提示信息","没登录成功","请重新输入信息！");
                }
                break;
        }
    }

    @FXML//注册！！
    void register(ActionEvent event) throws Exception{
        //加载当前场景
        Scene scene = new Scene(FXMLLoader.load(getClass()
                .getResource("/com/parallel/module/register/register.fxml")));
        //获取 当前窗口
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();//隐藏
        stage.setTitle("用戶注册");
        stage.setScene(scene);
        stage.show();

    }

}








