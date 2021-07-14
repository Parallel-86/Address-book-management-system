package com.parallel.utils;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AlertConfirm {
    public static void display(String title,String msg){
        Stage stage = new Stage();

        Label label = new Label();
        label.setText(msg);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("宋体", 20));
        label.setTextFill(Color.web("#0076a3"));//字体颜色

        Button button = new Button("确 定");
        button.setCursor(Cursor.HAND);
        button.setFont(Font.font("黑体", FontWeight.BOLD,18));
        button.setMinWidth(80);
        button.setMinHeight(40);
        button.setOnMouseClicked(event -> {
            stage.close();//窗口关闭
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,button);//可以放置多个
        vBox.setAlignment(Pos.CENTER);//居中
        vBox.setSpacing(50);//间距

        Scene scene = new Scene(vBox,300,200);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();//显示并等待
    }

    /*public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        display("提示信息！","没有选择列表");
    }*/
}
