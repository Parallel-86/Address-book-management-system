package com.parallel.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertUtils {
    //标题、头信息、提示信息
    public static void showAlert(String title,String header,String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//INFORMATION消息框
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();//显示
    }





    public static boolean showConfirm(String title,String header,String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//确认框
        alert.setTitle(title);//设置对话框标题
        alert.setHeaderText(header);//设置内容
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();//显示对话框
        //如果点击OK
        if(result.get() == ButtonType.OK){
            return true;
        }else{
            return false;
        }
    }
}
