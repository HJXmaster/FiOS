/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hjxPag;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class ShowFi extends Stage{
    TextArea area=new TextArea();
    
    public ShowFi(String name,String neirong){
        this.area.setText(neirong);
        this.area.setWrapText(true);
        this.area.setScaleX(1);
        this.area.setLayoutX(110);
        this.area.setLayoutY(100);
        this.area.setPrefSize(700, 400);
        this.area.setEditable(false);
        
        setTitle(name);
        setScene(new Scene(area,600,600));
        this.show();
    }
}
