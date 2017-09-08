/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hjxPag;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Instances;
import main.NewStarter;
import structures.Record;

/**
 *
 * @author pc
 */
public class ShowRecord extends HBox {

    private ImageView showFloder = new ImageView(new Image("icon_Dir.png"));
    private ImageView showFile = new ImageView(new Image("icon_File.png"));
    private static String path;
    private static Instances instance;

    public ShowRecord(String path, Record record,Instances instance) {
        this.path = path;
        this.instance=instance;
        showFloder.setFitHeight(50);
        showFloder.setFitWidth(50);
        showFile.setFitHeight(50);
        showFile.setFitWidth(50);
        Label name = new Label(String.valueOf(record.getName()));
        name.setMinSize(40, 100);
        name.setAlignment(Pos.TOP_CENTER);
        if (record.getAttribute() > 8) {
            this.getChildren().addAll(showFloder, name);
        } else {
            name.setText(String.valueOf(record.getName())+"." + String.valueOf(record.getType()));
            this.getChildren().addAll(showFile,name);
        }

        this.setOnMouseClicked(e -> {
            if(e.getButton()==MouseButton.SECONDARY){
                
                RigthClickMenu rigthClickMenu=new RigthClickMenu(instance,e.getScreenX(),e.getScreenY());
            }
            if (record.getAttribute() >= 8) {
                if (e.getClickCount()== 2) {
                    System.out.println("打开文件夹");
                    try {
                        String newPath=path+"/"+getString(record.getName());
                        ArrayList<Record> records=instance.showFolder(newPath);
                        NewStarter.show.setRecord(newPath, records);
                        NewStarter.path.setText(newPath);
                    } catch (Exception ex) {
                        Logger.getLogger(ShowRecord.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }else {
                if (e.getClickCount()== 2) {
                    
                    try {
                        String newPath=path+"/"+getString(record.getName())+"."+getString(record.getType());
                        System.out.println("打开文件");
                        new OperateFile(newPath, instance);
                    } catch (Exception ex) {
                        Logger.getLogger(ShowRecord.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }
        });
    }
    private String getString(char[] string) {
        String newString = "";
        for (int i = 0; i < string.length; i++) {
            if (string[i] != 0 && string[0] != '$') {
                newString = newString + string[i];
            }
        }
        return newString;
    }
}
