
package fileOperation;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import main.Instances;
import sun.security.pkcs.ContentInfo;
import tools.tipBox;

public class closeFile {

    Button btnOK;
    Button btnCancel;
    Instances instance;
    public closeFile(final Stage stg,Instances instance) {
        btnOK = new Button();
        btnCancel = new Button();

        final Stage stage = new Stage();
        stage.setWidth(570);
        stage.setHeight(155);
//Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
//Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("文件的关闭");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        stage.setResizable(false);
        
        
        
        Label path =new Label("绝对路径：");  
        path.setLayoutX(50);
        path.setLayoutY(30);
        
        TextField textForPath = new TextField();
        textForPath.setPromptText("请在这里输入文件的绝对路径...");
        textForPath.setScaleX(1);
        textForPath.setLayoutX(110);
        textForPath.setLayoutY(25);
        textForPath.setPrefSize(400, 20);
        
         
        Line line = new Line(50, 60,510, 60); 
        //                   x始  y始  x束 y束 
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
//                createFile hsy = new createFile(stg);
                String paths=textForPath.getText();
                if(!paths.equals(""))
                        
                {
                try {
                    instance.closeFile(paths);
                    new tipBox(stg,"关闭成功");   
                stage.close();
                } catch (Exception ex) {
                            ex.printStackTrace();
                         
                  new tipBox(stg,ex.getMessage());     
                }
            }
                else
                  new tipBox(stg,"文件名不能为空！");     
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        
        btnOK.setLayoutX(180);
        btnOK.setLayoutY(70);
        btnOK.setText("OK");
        btnCancel.setLayoutX(340);
        btnCancel.setLayoutY(70);
        btnCancel.setText("Cancel");

        root.getChildren().addAll(line,btnOK,btnCancel,path,textForPath);
        stage.setScene(scene);
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
        stage.show();

    }

}
