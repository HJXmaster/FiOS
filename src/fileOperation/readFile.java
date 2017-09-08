
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

public class readFile {

    Button btnOK;
    Button btnCancel;
    Instances instance;
    public readFile(final Stage stg,Instances instance) {
        btnOK = new Button();
        btnCancel = new Button();

        final Stage stage = new Stage();
        stage.setWidth(900);
        stage.setHeight(650);
//Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
//Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("文件的读取");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        stage.setResizable(false);
        
        
        
        Label path =new Label("绝对路径：");  
        path.setLayoutX(50);
        path.setLayoutY(50);
        
        TextField textForPath = new TextField();
        textForPath.setPromptText("请在这里输入文件的绝对路径...");
        textForPath.setScaleX(1);
        textForPath.setLayoutX(110);
        textForPath.setLayoutY(45);
        textForPath.setPrefSize(400, 20);
        
        
        Label bytes =new Label("读取长度：");  
        bytes.setLayoutX(50);
        bytes.setLayoutY(105);
        Label danwei =new Label("字节");  
        danwei.setLayoutX(160);
        danwei.setLayoutY(105);
        
        TextField textForByte = new TextField();
        textForByte.setPromptText("长度...");
        textForByte.setScaleX(1);
        textForByte.setLayoutX(110);
        textForByte.setLayoutY(100);
        textForByte.setPrefSize(50, 20);
        TextField textForByte2 = new TextField();
        textForByte2.setPromptText("字节");
        
        
        
        Line line = new Line(50, 140,810, 140); 
        //                   x始  y始  x束 y束 
        
        Label content = new Label("文件内容：");
        content.setLayoutX(50);
        content.setLayoutY(150);
        
        TextArea contentForFile = new TextArea();
        TextArea textArea; 
        contentForFile.setText("读取到的文件内容将会显示在这里...");
        contentForFile.setWrapText(true);
        contentForFile.setScaleX(1);
        contentForFile.setLayoutX(110);
        contentForFile.setLayoutY(150);
        contentForFile.setPrefSize(700, 400);
        contentForFile.setEditable(false);
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
         
//                createFile hsy = new createFile(stg);
                String paths=textForPath.getText();
                       if(!paths.equals("")&&(!textForByte.getText().equals(""))){
                int intBytes=Integer.valueOf(textForByte.getText()); 
                
                try {
                         contentForFile.setText(instance.readFile(paths, intBytes));//文本框显示内容
                         contentForFile.setEditable(false);//文本框不可改变内容
                } catch (Exception ex) {
                            ex.printStackTrace();
                  new tipBox(stg,ex.getMessage());     
                }
                }
                       else
                  new tipBox(stg,"输入栏不能为空！");     
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                    stage.close();
            }
        });

        btnOK.setLayoutX(305);
        btnOK.setLayoutY(570);
        btnOK.setText("OK");
        btnCancel.setLayoutX(540);
        btnCancel.setLayoutY(570);
        btnCancel.setText("Cancel");

        root.getChildren().addAll(btnOK,btnCancel,path,content,line,textForPath,danwei,bytes,textForByte,contentForFile);
        stage.setScene(scene);
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
        stage.show();

    }

}
