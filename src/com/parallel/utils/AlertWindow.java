package com.parallel.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

//消息对话框
public class AlertWindow {
    private static Boolean isRun;
    public static Boolean display(String title,String msg){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label(msg);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("System Bold", 18));
        label.setTextFill(Color.web("#0076a3"));//字体颜色

        Button button = new Button("是");
        Button buttonNo = new Button("否");
        button.setOnMouseClicked(event -> {
            isRun = true;
            stage.close();//窗口关闭
        });
        buttonNo.setOnMouseClicked(event -> {
            isRun = false;
            stage.close();
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(button,buttonNo);//可以放置多个
        hBox.setAlignment(Pos.CENTER);//居中
        hBox.setSpacing(50);//间距

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);//上下间距

        Scene scene = new Scene(vBox,300,200);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();//显示并等待
        return isRun;
    }
}
