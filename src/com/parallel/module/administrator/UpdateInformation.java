package com.parallel.module.administrator;

import com.parallel.dao.UserDao;
import com.parallel.entity.User;
import com.parallel.utils.AlertConfirm;
import com.parallel.utils.MapUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author parallel
 * @create 2021-07-01-0:12
 */
public class UpdateInformation implements Initializable {
    @FXML
    private Label sid;

    @FXML
    private Label name;

    @FXML
    private TextField phone;

    @FXML
    private TextField sex;

    @FXML
    private TextField address;


    @Override
    public void initialize(URL location, ResourceBundle resources) {//初始化数据
        //回显数据
        User stu  = (User) MapUtils.map.get("selectStu");
        name.setText(stu.getName());
        sid.setText(stu.getUid());
        sex.setText(stu.getSex());
        phone.setText(stu.getPhone());
        address.setText(stu.getAddress());
    }
    @FXML//返回主页
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

    @FXML
    void updateInformation(ActionEvent event) throws Exception {
        //封装对象
        User stu = new User();
        stu.setUid(sid.getText());
        stu.setName(name.getText());
        stu.setSex(sex.getText());
        stu.setPhone(phone.getText());
        stu.setAddress(address.getText());

        UserDao dao = new UserDao();
        boolean temp = dao.updateInformation(stu);//修改
        if (temp){
            AlertConfirm.display("提示信息","修改成功！");
            Scene scene = new Scene(FXMLLoader.load(getClass()
                    .getResource("/com/parallel/module/administrator/administratorMain.fxml")));//user的首页
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.hide();
            stage.setTitle("管理員主页");
            stage.setScene(scene);
            stage.show();

        }


    }


}
