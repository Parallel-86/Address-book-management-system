package com.parallel.module.administrator;

import com.parallel.entity.User;
import com.parallel.utils.MapUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author parallel
 * @create 2021-07-01-2:14
 */
public class SelectUser implements Initializable {
    @FXML
    private Label address;

    @FXML
    private Label sid;

    @FXML
    private Label name;

    @FXML
    private Label sex;

    @FXML
    private Label phone;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User stu = (User) MapUtils.map.get("selectedStu");
        sid.setText(stu.getUid());
        name.setText(stu.getName());
        sex.setText(stu.getSex());
        phone.setText(stu.getPhone());
        address.setText(stu.getAddress());
    }
    @FXML
    void backMain(ActionEvent event) throws Exception{
        Scene scene = new Scene(FXMLLoader.load(getClass()
                .getResource("/com/parallel/module/administrator/administratorMain.fxml")));//user的首页
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();
        stage.setTitle("管理員页面");
        stage.setScene(scene);
        stage.show();
    }


}
