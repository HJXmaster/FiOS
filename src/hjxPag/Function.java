/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hjxPag;

import dirOperation.createDir;
import fileOperation.createFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Instances;
import main.NewStarter;

/**
 *
 * @author pc
 */
public class Function extends HBox{
    Instances instance;
    Button floder=new Button("创建文件夹");
    Button file=new Button("创建文件");
    Button back=new Button("后退");
    public Function(Instances instance){
        this.instance=instance;
        floder.setOnAction(e->{
            new createDir(new Stage(),instance);
        });
        file.setOnAction(e->{
            new createFile(new Stage(),instance);
        });
        back.setOnAction(e->{
            String path=NewStarter.path.getText();
            path=path.substring(0,path.lastIndexOf("/"));
            try {
                NewStarter.show.setRecord(path, instance.showFolder(path));
                NewStarter.path.setText(path);
            } catch (Exception ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.getChildren().addAll(floder,file,back);
    }

    public Function() {
        
    }
    
}
