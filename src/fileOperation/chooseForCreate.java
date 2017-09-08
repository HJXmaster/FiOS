/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileOperation;

import dirOperation.*;
import java.awt.Font;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class chooseForCreate {

    Button btn_Back;
    Button btn_File;
    Button btn_Dir;
    Instances instances; 

    public chooseForCreate(final Stage stg,Instances instance) {
        btn_File = new Button();
        btn_Dir = new Button();
        this.instances = instance;
        
       ImageView ourIcon = new ImageView(new Image("Icons/ourIcon.png"));
//        btn_File.getStyleClass().add("hsy");  
//        
//         Label fileIcon = new Label();
//         fileIcon.setGraphic(new ImageView("icon_Dir.png"));
         
//
//        fileIcon.setLayoutX(0);
//        fileIcon.setLayoutY(0);

        FadeTransition ft2=new FadeTransition(Duration.millis(500),ourIcon);
        ft2.setFromValue(3.0);  
        ft2.setToValue(0.3);  
        ft2.setCycleCount(Timeline.INDEFINITE);  
        ft2.setAutoReverse(true);  
        ft2.play();  
        
        btn_Back = new Button("", new ImageView("Icons/back.png"));
         btn_Back.setScaleX(0.3);
         btn_Back.setScaleY(0.3);   
         btn_Back.setId("btnForBack");
         
        btn_File = new Button("", new ImageView("Icons/icon_File2.png"));
         btn_File.setScaleX(0.7);
         btn_File.setScaleY(0.7);  
        btn_Dir = new Button("", new ImageView("Icons/icon_Dir2.png"));
         btn_Dir.setScaleX(0.7);
         btn_Dir.setScaleY(0.7);
         

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
       scene.getStylesheets().add("mm1.css"); 
        Line line = new Line(280, 85,280, 225); 
        //                   x始  y始  x束 y束 
         

            
      ourIcon.setLayoutX(500);
            ourIcon.setLayoutY(200);
        
         ourIcon.setScaleX(0.7);
         ourIcon.setScaleY(0.7);   
       
        
        btn_Back.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                          stage.close();
                stg.show();
            }
        });      
        
        
        
        
        btn_File.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new chooseInFile(stg,instances);
                            
            }
        });
        btn_Dir.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new chooseInDir(stg,instances);
                            
            }
        });

        
        btn_Back.setLayoutX(-115);
        btn_Back.setLayoutY(-80);
        
        btn_File.setLayoutX(0);
        btn_File.setLayoutY(25);
        
        btn_Dir.setLayoutX(272);
        btn_Dir.setLayoutY(25);

        root.getChildren().addAll(btn_Back,btn_File,btn_Dir,line,ourIcon);
        stage.setScene(scene);
        stage.show();

    }

}
