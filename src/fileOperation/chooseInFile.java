/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileOperation;

import dirOperation.createDir;
import java.awt.Font;
import main.Instances;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class chooseInFile {

    Button btn_Back=new Button();
    Button btn_File=new Button();
    Button btn_Create=new Button("新建文件");
    Button btn_Write=new Button("写文件");
    Button btn_Read=new Button("读文件");
    Button btn_Delete=new Button("删除文件");
    Button btn_Show=new Button("展示文件");
    Button btn_Change=new Button("修改文件");
    Button btn_Close=new Button("关闭文件");
    Instances instances; 

    public chooseInFile(final Stage stg,Instances instance) {
        btn_File = new Button();
        
        this.instances = instance;

        btn_Back = new Button("", new ImageView("Icons/back.png"));
         btn_Back.setScaleX(0.3);
         btn_Back.setScaleY(0.3);   
         btn_Back.setId("btnForBack");
         
        btn_File = new Button("", new ImageView("Icons/icon_File2.png"));
         btn_File.setScaleX(0.7);
         btn_File.setScaleY(0.7);  
         

        final Stage stage = new Stage();
        stage.setWidth(650);
        stage.setHeight(330); 
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
        stage.initOwner(stg);
        stage.setTitle("请选择");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250,Color.valueOf("#ff99cc"));
        stage.setResizable(false); 
        
       scene.getStylesheets().add("mm1.css"); 
        Line line = new Line(240, 85,240, 225); 
        //                   x始  y始  x束 y束 
         

       
        
        btn_Back.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.close();
            }
        });      
        
        
        
        
        btn_File.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) { 
                            
            }
        });
        btn_Back.setLayoutX(-115);
        btn_Back.setLayoutY(-80);
        
        btn_File.setLayoutX(0);
        btn_File.setLayoutY(25);
        
        
        
      btn_Create.setLayoutX(260);
            btn_Create.setLayoutY(75);
      btn_Delete.setLayoutX(380);
            btn_Delete.setLayoutY(75);
      btn_Write.setLayoutX(260);
            btn_Write.setLayoutY(150);
      btn_Read.setLayoutX(380);
            btn_Read.setLayoutY(150);
      btn_Show.setLayoutX(260);
            btn_Show.setLayoutY(225);
      btn_Change.setLayoutX(380);
            btn_Change.setLayoutY(225);
      btn_Close.setLayoutX(500);
            btn_Close.setLayoutY(225);
            
            
        btn_Create.setOnAction(e->new createFile(stg,instance));
        btn_Write.setOnAction(e->new writeFile(stg,instance));
        btn_Read.setOnAction(e->new readFile(stg,instance));
        btn_Delete.setOnAction(e->new deleteFile(stg,instance));
        btn_Show.setOnAction(e->new showFile(stg,instance));
        btn_Change.setOnAction(e->new changeAttribute(stg,instance));
        btn_Close.setOnAction(e->new closeFile(stg,instance));
            
            
            
//        button6.setOnAction(e->new writeFile(primaryStage,instance));
            
//        ,btn_Create,btn_Write,btn_Read,btn_Delete,btn_Show,btn_Change

        root.getChildren().addAll(btn_Back,btn_File,line,btn_Create,btn_Write,btn_Read,btn_Delete,btn_Show,btn_Change,btn_Close);
        stage.setScene(scene);
        stage.show();

    }

}
