/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hjxPag;

import dirOperation.createDir;
import fileOperation.createFile;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Instances;

/**
 *
 * @author pc
 */
public class RigthClickMenu extends Stage {

    private VBox vbox = new VBox();

    public RigthClickMenu(Instances instance,double x,double y) {
        Button floder = new Button("创建文件夹");
        Button file = new Button("创建文件");
        Button attribute = new Button("属性");
        floder.setMinWidth(130);
        file.setMinWidth(130);
        attribute.setMinWidth(130);
        floder.setOnAction(e -> {
            new createDir(new Stage(), instance);
            this.close();
        });
        file.setOnAction(e -> {
            new createFile(new Stage(), instance);
            this.close();
        });
        vbox.getChildren().addAll(floder, file,attribute);

        this.initStyle(StageStyle.TRANSPARENT);
        this.setX(x);
        this.setY(y);
        Scene scene = new Scene(vbox);
        this.setScene(scene);
        this.show();
    }
    
}
