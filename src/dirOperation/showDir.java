
package dirOperation;

import fileOperation.*;
import java.awt.Font;
import java.util.ArrayList;
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
import structures.Record;
import sun.security.pkcs.ContentInfo;
import tools.tipBox;

public class showDir {

    Button btnOK;
    Button btnCancel;
    Instances instance;
    public showDir(final Stage stg,Instances instance) {
        btnOK = new Button();
        btnCancel = new Button();

        final Stage stage = new Stage();
        stage.setWidth(900);
        stage.setHeight(600);
//Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
//Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("显示目录内容");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        stage.setResizable(false);
        
        
        
        Label path =new Label("绝对路径：");  
        path.setLayoutX(50);
        path.setLayoutY(50);
        
        TextField textForPath = new TextField();
        textForPath.setPromptText("请在这里输入目录的绝对路径...");
        
        textForPath.setScaleX(1);
        textForPath.setLayoutX(110);
        textForPath.setLayoutY(45);
        textForPath.setPrefSize(700, 20);
        
         
        
        
        Line line = new Line(50, 80,810, 80); 
        //                   x始  y始  x束 y束 
        
        Label content = new Label("目录内容：");
        content.setLayoutX(50);
        content.setLayoutY(100);
        
TextArea contentForFile = new TextArea();
        contentForFile.setPromptText("读取到目录的所有内容将会显示在这里...");
                contentForFile.setWrapText(true);
        contentForFile.setScaleX(1);
        contentForFile.setLayoutX(110);
        contentForFile.setLayoutY(100);
        contentForFile.setPrefSize(700, 400);
        contentForFile.setEditable(false);
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
//                createFile hsy = new createFile(stg);
            if(!textForPath.getText().equals("")){
                String paths=textForPath.getText();
                
                try {
                    ArrayList<Record> records=instance.showFolder(paths);
                    String str="";
                    for (Record record : records) {
                        str=str+"文件名："+String.valueOf(record.getName())+"  类型："+String.valueOf(record.getType())+"\r\n";
                    }
                    System.out.println(str);
                 contentForFile.setText(str);
                } catch (Exception ex) {
                            ex.printStackTrace();
                  new tipBox(stg,ex.getMessage());      
                }
                
            }
            else
                  new tipBox(stg,"路径不能为空！");     
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.hide();
            }
        });

        
        btnOK.setLayoutX(305);
        btnOK.setLayoutY(520);
        btnOK.setText("OK");
        btnCancel.setLayoutX(540);
        btnCancel.setLayoutY(520);
        btnCancel.setText("Cancel");

        root.getChildren().addAll(btnOK,btnCancel,path,content,line,textForPath,contentForFile);
        stage.setScene(scene);
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
        stage.show();

    }

}
