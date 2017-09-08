/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hjxPag;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.GridPane;
import main.Instances;
import structures.Record;

/**
 *
 * @author pc
 */
public class ShowFloderByFX extends GridPane{
    private static ShowRecord[] showRecords=new ShowRecord[8];
    private static String path="";
    private static Instances instance;
    public ShowFloderByFX(ArrayList<Record> record){
        for(int i=0;i<record.size();i++){
            showRecords[i]=new ShowRecord(path,record.get(i),instance);
            this.add(showRecords[i],i%2, i/2);
        }
    }
    
    public ShowFloderByFX(Instances instance){
        try {
            this.instance=instance;
            for(int i=0;i<8;i++){
                Record record=new Record(String.valueOf(i).toCharArray(),(byte)(3+i),(byte)0);
                showRecords[i]=new ShowRecord(path,record,instance);
                this.add(showRecords[i],i%2, i/2);
            }
        } catch (Exception ex) {
            Logger.getLogger(ShowFloderByFX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void setRecord(String path,ArrayList<Record> records){
        this.path=path;
            this.getChildren().clear();
        for(int i=0;i<records.size();i++){
            showRecords[i]=new ShowRecord(path,records.get(i),instance);
            this.add(showRecords[i],i%2, i/2);
        }
    }
}
