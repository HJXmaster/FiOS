/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dirOperation;

import fileOperation.*;
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

public class chooseInDir {

    Button btn_Back=new Button();
    Button btn_Dir=new Button();
    Button btn_Create=new Button("新建目录");
    Button btn_Delete=new Button("删除目录");
    Button btn_Show=new Button("显示目录内容");
    Instances instances; 

    public chooseInDir(final Stage stg,Instances instance) {
        btn_Dir = new Button();
        
        this.instances = instance;

        btn_Back = new Button("", new ImageView("Icons/back.png"));
         btn_Back.setScaleX(0.3);
         btn_Back.setScaleY(0.3);   
         btn_Back.setId("btnForBack");
         
        btn_Dir = new Button("", new ImageView("Icons/icon_Dir2.png"));
         btn_Dir.setScaleX(0.7);
         btn_Dir.setScaleY(0.7);  
         

        final Stage stage = new Stage();
        stage.setWidth(650);
        stage.setHeight(330); 
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.initOwner(stg);
        stage.setTitle("请选择");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250,Color.valueOf("#ff99cc"));
         
       scene.getStylesheets().add("mm1.css"); 
        Line line = new Line(280, 85,280, 225); 
        //                   x始  y始  x束 y束 
         

       
        
        btn_Back.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.close();
            }
        });      
        
        
        
        
        btn_Dir.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) { 
                            
            }
        });
        btn_Back.setLayoutX(-115);
        btn_Back.setLayoutY(-80);
        
        btn_Dir.setLayoutX(272);
        btn_Dir.setLayoutY(25);
        
        
        
      btn_Create.setLayoutX(20);
            btn_Create.setLayoutY(75);
      btn_Delete.setLayoutX(140);
            btn_Delete.setLayoutY(75);
      btn_Show.setLayoutX(20);
            btn_Show.setLayoutY(150);
            
            
        btn_Create.setOnAction(e->new createDir(stg,instance));
        btn_Delete.setOnAction(e->new deleteDir(stg,instance));
        btn_Show.setOnAction(e->new showDir(stg,instance));
            
            
            
//        button6.setOnAction(e->new writeFile(primaryStage,instance));
            
//        ,btn_Create,btn_Write,btn_Read,btn_Delete,btn_Show,btn_Change

        root.getChildren().addAll(btn_Back,btn_Dir,line,btn_Create,btn_Delete,btn_Show);
        stage.setScene(scene);
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
        stage.show();

    }

}
