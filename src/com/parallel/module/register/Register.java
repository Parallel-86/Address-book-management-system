package com.parallel.module.register;

import com.parallel.dao.UserDao;
import com.parallel.entity.User;
import com.parallel.utils.AlertUtils;
import com.parallel.utils.UUIDUtils;
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

public class Register implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private TextField pwd;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private RadioButton man;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton woman;


    private String gander = "男";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        man.setOnAction(event -> gander = "男");
        woman.setOnAction(event -> gander = "女");
    }
    @FXML//返回登录界面
    void login(ActionEvent event) throws Exception{
        //加载当前场景
        Scene scene = new Scene(FXMLLoader.load(getClass()
                .getResource("/com/parallel/module/login/login.fxml")));

        //获取 当前窗口
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();//隐藏
        stage.setTitle("登录页面");
        stage.setScene(scene);
        stage.show();
    }

    @FXML  //注册
    void register(ActionEvent event) throws Exception{
        //获取数据
        User stu = new User();
        stu.setName(name.getText());
        stu.setPassword(pwd.getText());
        stu.setSex(gander);
        stu.setPhone(phone.getText());
        stu.setUid(UUIDUtils.uuid());
        stu.setAddress(address.getText());
        //调用DAO层的方法，保存到数据库
        UserDao dao = new UserDao();
        boolean temp = dao.saveUser(stu);//保存
        if(temp){//返回true注册成功
            //确认提示框
            boolean result = AlertUtils.showConfirm("提示信息", "注册成功！", "你的ID：" + stu.getUid());
            if(result){//确认
                //加载当前场景
                Scene scene = new Scene(FXMLLoader.load(getClass()
                        .getResource("/com/parallel/module/login/login.fxml")));

                //获取 当前窗口
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.hide();//隐藏
                stage.setTitle("登录页面");
                stage.setScene(scene);
                stage.show();
            }else{//取消
              return;
            }
        }else{
            AlertUtils.showAlert("ERROR","请检查你输入","注册失败！");
        }

    }

}
