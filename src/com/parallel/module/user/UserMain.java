package com.parallel.module.user;

import com.parallel.dao.UserDao;
import com.parallel.entity.User;
import com.parallel.utils.AlertConfirm;
import com.parallel.utils.AlertWindow;
import com.parallel.utils.MapUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserMain implements Initializable {

    @FXML
    private TextField keyWord;

    @FXML
    private Label stuLab;

    @FXML
    private TableView<User> stuTab;

    @FXML
    private TableColumn<User, String> s_sid;

    @FXML
    private TableColumn<User, String> s_name;

    @FXML
    private TableColumn<User, String> s_sex;

    @FXML
    private TableColumn<User, String> s_address;

    @FXML
    private TableColumn<User, String> s_phone;

    @FXML
    private TableColumn<User, String> s_state;

    ObservableList<User> stuList ;//保存用戶信息
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User stu = (User) MapUtils.map.get("sessionStu");
        stuLab.setText("尊敬的"+stu.getName()+stu.getSex()+"士:\n"+"       你好： 欢迎你使用通讯录查询系统\n    你的号码为："+stu.getPhone()
                +"\n    您所在的地址为："+stu.getAddress());

        s_sid.setCellValueFactory(new PropertyValueFactory<User,String>("uid"));
        s_sid.setMinWidth(300);//宽度
        s_name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        s_name.setMinWidth(100);//宽度

        s_sex.setCellValueFactory(new PropertyValueFactory<User,String>("sex"));
        s_sex.setMinWidth(30);//宽度
        s_phone.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        s_phone.setMinWidth(200);//宽度

        s_state.setCellValueFactory(new PropertyValueFactory<User,String>("state"));
        s_state.setMinWidth(30);//宽度
        s_address.setCellValueFactory(new PropertyValueFactory<User,String>("address"));
        s_address.setMinWidth(30);//宽度


        List<User> sList = new UserDao().findByUser();//调用控制层自定义方法
        stuList  = FXCollections.observableList(sList);//迁移数据

        stuTab.setItems(stuList);//添加到窗体中
    }
    @FXML//返回
    void backMain(ActionEvent event) throws Exception{
        Boolean temp = AlertWindow.display("提示信息", "是否真的要退出系统吗？");
        if(temp){
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
        }else{
            return;
        }
    }
    @FXML  //模糊查询
    void findByName(ActionEvent event) {
        String text = keyWord.getText();
        List<User> sList = new UserDao().findByName(text);
        stuList  = FXCollections.observableList(sList);//迁移数据
        stuTab.setItems(stuList);//添加到窗体中
    }

    @FXML//用户界面修改数据
    void updateInformation(ActionEvent event) throws Exception {
        User selectStu = stuTab.getSelectionModel().getSelectedItem();
        if(selectStu == null){
            AlertConfirm.display("提示信息","没有选择列表，请选择用戶：");
            return;
        }
        MapUtils.map.put("selectStu",selectStu);
        Scene scene = new Scene(FXMLLoader.load(getClass()
                .getResource("/com/parallel/module/user/userResult.fxml")));//user的首页
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();
        stage.setTitle("信息修改");
        stage.setScene(scene);
        stage.show();
    }
}

