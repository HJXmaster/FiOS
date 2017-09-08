/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

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

public class tipBox {

    Button btn_Ok=new Button("我知道了");
    Label  tipText=new Label("");

    public tipBox(final Stage stg,String message) {
        
        tipText.setText(message);

        final Stage stage = new Stage();
        stage.setWidth(300);
        stage.setHeight(200); 
        stage.setResizable(false);
        stage.initOwner(stg);
        stage.setTitle("提示");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250,Color.valueOf("white"));
       scene.getStylesheets().add("mm1.css"); 
         
        stage.initModality(Modality.APPLICATION_MODAL);
        
       
        
        btn_Ok.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.close();
            }
        });      
        
         
                
        tipText.setLayoutX(30);
        tipText.setLayoutY(50);
        
        btn_Ok.setLayoutX(100);
        btn_Ok.setLayoutY(110);
        
            

        root.getChildren().addAll(btn_Ok,tipText);
        stage.setScene(scene);
        stage.show();

       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
    }

}
