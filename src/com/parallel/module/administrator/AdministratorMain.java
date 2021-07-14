package com.parallel.module.administrator;


import com.parallel.dao.AdministratorDao;
import com.parallel.dao.UserDao;
import com.parallel.entity.Administrator;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdministratorMain implements Initializable {

    @FXML
    private TableView<User> stuTab;//列表框

    @FXML
    private TableColumn<User, String> s_sid;

    @FXML
    private TableColumn<User, String> s_name;

    @FXML
    private TableColumn<User, String> s_sex;

    @FXML
    private TableColumn<User, String> s_phone;

    @FXML
    private TableColumn<User, String> s_password;

    @FXML
    private TableColumn<User, String> s_address;

    @FXML
    private TableColumn<User, String> s_state;
    //管理員---------------------------------------
    @FXML
    private TableView<Administrator> teaTab;//列表框

    @FXML
    private TableColumn<Administrator, String> t_id;

    @FXML
    private TableColumn<Administrator, String> t_name;

    @FXML
    private TableColumn<Administrator, String> t_sex;

    @FXML
    private TableColumn<Administrator, String> t_password;

    @FXML
    private TableColumn<Administrator, String> t_phone;

    @FXML
    private TextField keyWord;
    ObservableList<User> stuList ;//保存用戶信息
    ObservableList<Administrator> teaList ;//保存管理員信息

    @Override //初始化的方法
    public void initialize(URL location, ResourceBundle resources) {
        //通过set方法设置属性
        s_sid.setCellValueFactory(new PropertyValueFactory<User,String>("uid"));
        s_sid.setMinWidth(280);//宽度
        s_name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        s_name.setMinWidth(30);
        s_password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        s_password.setMinWidth(50);
        s_sex.setCellValueFactory(new PropertyValueFactory<User,String>("sex"));
        s_sex.setMinWidth(7);
        s_address.setCellValueFactory(new PropertyValueFactory<User,String>("address"));
        s_address.setMinWidth(200);
        s_phone.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        s_phone.setMinWidth(50);
        s_state.setCellValueFactory(new PropertyValueFactory<User,String>("state"));
        s_state.setMinWidth(10);

        List<User> sList = new UserDao().findByUser();//调用控制层自定义方法
        stuList  = FXCollections.observableList(sList);//迁移数据
        stuTab.setItems(stuList);//添加到窗体中
        //-------------------------------------------------------------
        t_id.setCellValueFactory(new PropertyValueFactory<Administrator,String>("aid"));
        t_id.setMinWidth(100);//宽度
        t_name.setCellValueFactory(new PropertyValueFactory<Administrator,String>("name"));
        t_name.setMinWidth(100);
        t_sex.setCellValueFactory(new PropertyValueFactory<Administrator,String>("sex"));
        t_sex.setMinWidth(30);
        t_phone.setCellValueFactory(new PropertyValueFactory<Administrator,String>("phone"));
        t_phone.setMinWidth(200);
        t_password.setCellValueFactory(new PropertyValueFactory<Administrator,String>("password"));
        t_password.setMinWidth(100);
        List<Administrator> tList = new AdministratorDao().findByAdministrator();//调用控制层代码
        teaList = FXCollections.observableList(tList);//迁移数据
        teaTab.setItems(teaList);
    }
    /**
     * 返回登陸頁面
     * @param event
     * @throws Exception
     */
    @FXML
    void backLogin(ActionEvent event) throws Exception{
        Boolean temp = AlertWindow.display("提示信息", "是否返回登录页面？");
        if(temp){
            Scene scene = new Scene(FXMLLoader.load(getClass()
                    .getResource("/com/parallel/module/login/login.fxml")));//user的首页
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.hide();
            stage.setTitle("登录页面");
            stage.setScene(scene);
            stage.show();
        }else{
            return;
        }
    }

    @FXML //信息录入
    void inputScore(ActionEvent event) throws Exception{
        User stu = stuTab.getSelectionModel().getSelectedItem();//选项
        if(stu == null){
            AlertConfirm.display("提示信息","请选择用戶：");
            return;
        }else{
            MapUtils.map.put("selectStu",stu);//保存用戶信息
            Scene scene = new Scene(FXMLLoader.load(getClass()
                    .getResource("/com/parallel/module/administrator/inputInformation.fxml")));//user的首页
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.hide();
            stage.setTitle("录入信息");
            stage.setScene(scene);
            stage.show();
        }
    }

    //修改信息的方法
    @FXML
    void updateInformation(ActionEvent event) throws Exception{
        User selectStu = stuTab.getSelectionModel().getSelectedItem();
        if(selectStu == null){
            AlertConfirm.display("提示信息","没有选择列表，请选择用戶：");
            return;
        }
        //携带数据
        MapUtils.map.put("selectStu",selectStu);
        Scene scene = new Scene(FXMLLoader.load(getClass()
                .getResource("/com/parallel/module/administrator/updateInformation.fxml")));//user的首页
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();
        stage.setTitle("信息修改");
        stage.setScene(scene);
        stage.show();
    }

    @FXML//删除信息
    void deleteInfor(ActionEvent event) {
        //选择列表
        User selectedStu = stuTab.getSelectionModel().getSelectedItem();
        if(selectedStu == null){
            AlertConfirm.display("提示信息","没有选择列表，请选择用戶：");
            return;
        }else{
            Boolean temp = AlertWindow
                    .display("重要提示", "确认删除 " + selectedStu.getName() + " 用戶吗？");

            if(temp){
                UserDao dao = new UserDao();
                dao.deleteInformation(selectedStu.getUid());
                stuTab.getItems().remove(selectedStu);//删除
            }
        }
    }

    @FXML  //用戶详情
    void selectUser(ActionEvent event) throws Exception{
        //选择列表
        User selectedStu = stuTab.getSelectionModel().getSelectedItem();
        if(selectedStu == null){
            AlertConfirm.display("提示信息","没有选择列表，请选择用戶：");
            return;
        }else{
            MapUtils.map.put("selectedStu",selectedStu);
            //展示
            Scene scene = new Scene(FXMLLoader.load(getClass()
                    .getResource("/com/parallel/module/administrator/selectUser.fxml")));//user的首页
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.hide();
            stage.setTitle("详情页面");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML //模糊查询
    void findByName(ActionEvent event) throws Exception{
        String text = keyWord.getText();
        List<User> sList = new UserDao().findByName(text);
        stuList  = FXCollections.observableList(sList);//迁移数据
        stuTab.setItems(stuList);//添加到窗体中
    }
}
