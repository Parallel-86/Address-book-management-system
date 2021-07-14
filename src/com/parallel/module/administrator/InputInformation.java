package com.parallel.module.administrator;

import com.parallel.dao.UserDao;
import com.parallel.entity.User;
import com.parallel.utils.AlertConfirm;
import com.parallel.utils.AlertWindow;
import com.parallel.utils.MapUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author parallel
 * @create 2021-06-30-8:12
 */
public class InputInformation implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField sid;

    @FXML
    private TextField sex;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;






//    @FXML
//    private TextField java;
//
//    @FXML
//    private TextField system;
//
//    @FXML
//    private TextField english;
//
//    @FXML
//    private TextField math;
//
//    @FXML
//    private TextField cpp;
//
//    @FXML
//    private TextField sport;




    private User selectStu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectStu = (User) MapUtils.map.get("selectStu");
        name.setText(selectStu.getName());//文本框赋值
        sid.setText(selectStu.getUid());
    }
    @FXML//返回
    void backMain(ActionEvent event) throws Exception{
        Scene scene = new Scene(FXMLLoader.load(getClass()
                .getResource("/com/parallel/module/administrator/administratorMain.fxml")));//user的首页
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();
        stage.setTitle("管理員主页");
        stage.setScene(scene);
        stage.show();
    }

    @FXML//录入信息
    void inputScore(ActionEvent event) throws Exception {
        //封装学生
        User stu = new User();
        stu.setUid(sid.getText());
        stu.setName(name.getText());
        stu.setSex(sex.getText());
        stu.setPhone(phone.getText());
        stu.setAddress(address.getText());
        stu.setState(selectStu.getState());

        UserDao dao  = new UserDao();
        boolean temp = dao.updateInformation(stu);
        //判断
        if(temp){
            AlertConfirm.display("提示信息","信息录入成功！");
            Scene scene = new Scene(FXMLLoader.load(getClass()
                    .getResource("/com/parallel/module/administrator/administratorMain.fxml")));//user的首页
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.hide();
            stage.setTitle("管理員主页");
            stage.setScene(scene);
            stage.show();
        }else{
            AlertWindow.display("提示信息","该用戶的信息已存在！！");
        }


    }


}