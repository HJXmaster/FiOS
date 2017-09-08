
package 作废readOperation;

import java.awt.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import sun.security.pkcs.ContentInfo;

public class readFile {

    Button btnOK;
    Button btnCancel;

    public readFile(final Stage stg) {
        btnOK = new Button();
        btnCancel = new Button();

        final Stage stage = new Stage();
        stage.setWidth(570);
        stage.setHeight(300);
//Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
//Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("文件的读取");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        
         
        
        
        Label path =new Label("绝对路径：");  
        path.setLayoutX(50);
        path.setLayoutY(50);
        
        TextField textForPath = new TextField();
        textForPath.setPromptText("请在这里输入文件的绝对路径...");
        textForPath.setScaleX(1);
        textForPath.setLayoutX(110);
        textForPath.setLayoutY(45);
        textForPath.setPrefSize(400, 20);
        
        
        Line line = new Line(50, 80,510, 80); 
        //                   x始  y始  x束 y束 
        
        Label content = new Label("文件内容：");
        content.setLayoutX(50);
        content.setLayoutY(100);
        
        TextField contentForFile = new TextField();
        contentForFile.setText("你改变不了");
        contentForFile.setScaleX(1);
        contentForFile.setLayoutX(110);
        contentForFile.setLayoutY(100);
        contentForFile.setPrefSize(400, 100);
        contentForFile.setEditable(false);
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
//                createFile hsy = new createFile(stg);

                contentForFile.setText("hsy");
        contentForFile.setEditable(false);
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.hide();
            }
        });

        
        btnOK.setLayoutX(180);
        btnOK.setLayoutY(220);
        btnOK.setText("OK");
        btnCancel.setLayoutX(340);
        btnCancel.setLayoutY(220);
        btnCancel.setText("Cancel");

        root.getChildren().addAll(btnOK,btnCancel,path,content,line,textForPath,contentForFile);
        stage.setScene(scene);
        stage.show();

    }

}
