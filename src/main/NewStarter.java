/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import hjxPag.Function;
import hjxPag.ShowFi;
import hjxPag.ShowFloderByFX;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.plaf.basic.BasicBorders;
import structures.Record;
import sun.security.jca.GetInstance;

/**
 *
 * @author pc
 */
public class NewStarter extends Application {

    private static Instances instance = new Instances();
    public static TextField path = new TextField();
    public static ShowFloderByFX show = new ShowFloderByFX(instance);
    private static Function function = new Function(instance);
    Scene scene;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        //scene=new Scene(root);
        VBox pane = new VBox();
        path.setAlignment(Pos.BOTTOM_LEFT);
        path.setOnAction(e -> {
            try {
                ArrayList<Record> records = instance.showFolder(path.getText());
                for (int i = 0; i < records.size(); i++) {
                    System.out.println(String.valueOf(records.get(i).getName()));
                }
                show.setRecord(path.getText(),records);
                
            } catch (Exception ex) {
                Logger.getLogger(NewStarter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        show.setOnMouseClicked(e -> {
            
                if (e.getButton() == MouseButton.SECONDARY) {
                    System.out.println("右击鼠标");
                }
        });
        function = new Function(instance);
        pane.getChildren().addAll(path, function, show);
        //root.getChildren().addAll(path,function,show);
        scene = new Scene(pane);
        primaryStage.setTitle("FiOS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
