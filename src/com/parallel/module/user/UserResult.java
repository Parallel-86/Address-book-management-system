package com.parallel.module.user;

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
 * @create 2021-07-01-1:56
 */
public class UserResult implements Initializable {
    @FXML
    private Label name;

    @FXML
    private Label sid;

    @FXML
    private TextField sex;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User stu = (User) MapUtils.map.get("selectStu");
        name.setText(stu.getName());
        sid.setText(stu.getUid());
        sex.setText(stu.getSex());
        phone.setText(stu.getPhone());
        address.setText(stu.getAddress());

    }
    @FXML //返回主页面
    void backMain(ActionEvent event) throws Exception{
        //加载当前场景
        Scene scene = new Scene(FXMLLoader.load(getClass()
                .getResource("/com/parallel/module/user/userMain.fxml")));
        //获取 当前窗口
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();//隐藏
        stage.setTitle("用戶页面");
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
                    .getResource("/com/parallel/module/user/userMain.fxml")));//user的首页
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.hide();
            stage.setTitle("管理員主页");
            stage.setScene(scene);
            stage.show();
        }

    }

}