import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    /*
     * //Application应用程序
     *   Stage：窗口
     *   Scene: 场景
     *   Node:  节点
     * */
    @Override //主窗口
    public void start(Stage stage) throws Exception {//开始的方法
        //加载fxml文件
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/com/parallel/module/login/login.fxml"));
        Pane pane = (Pane) fxmlLoader.load();
        Scene myScene = new Scene(pane);//实例场景
        stage.setScene(myScene);//添加场景
        stage.setTitle("登录页面");//设置标题
        stage.getIcons().add(new Image("com/parallel/image/address_book.png"));//设置图片
        stage.setResizable(false);//禁用
        stage.show();//显示
    }
    public static void main(String[] args) {
        launch(args);//启动器
    }


}

