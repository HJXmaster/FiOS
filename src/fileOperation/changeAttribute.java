package fileOperation;

import main.Instances;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import tools.tipBox;

public class changeAttribute {

    Button btnOK;
    Button btnCancel;
    Instances instances;

    public changeAttribute(final Stage stg,Instances instances) {
        btnOK = new Button();
        btnCancel = new Button(); 
        final Stage stage = new Stage();
        stage.setWidth(570);
        stage.setHeight(290);
//Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
//Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("文件的属性更改");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
         stage.setResizable(false);
        

        
        Label path =new Label("绝对路径：");  
        path.setLayoutX(50);
        path.setLayoutY(50);
        
        TextField textForPath = new TextField();
        textForPath.setPromptText("请在这里输入文件的绝对路径...");
        textForPath.setScaleX(1);
        textForPath.setLayoutX(110);
        textForPath.setLayoutY(45);
        textForPath.setPrefSize(400, 20);
        
        
        Label attribute = new Label("属性：");
        attribute.setLayoutX(50);
        attribute.setLayoutY(100);
        
        Line line = new Line(50, 185,510, 185); 
        //                   x始  y始  x束 y束 
        
        Label isSystem = new Label("系统文件：");
        isSystem.setLayoutX(50);
        isSystem.setLayoutY(145);
        
        final ToggleGroup groupForAtt = new ToggleGroup();  
  
        RadioButton rb_Read = new RadioButton("只读");  
        rb_Read .setToggleGroup(groupForAtt);  
        rb_Read .setLayoutX(120);
        rb_Read .setLayoutY(100);
        rb_Read .setSelected(true);  

        RadioButton rb_Ordinary = new RadioButton("普通");  
        rb_Ordinary.setToggleGroup(groupForAtt);  
        rb_Ordinary.setLayoutX(190);
        rb_Ordinary.setLayoutY(100);
        
        CheckBox boxForSystem = new CheckBox("是");
        boxForSystem.setLayoutX(120);
        boxForSystem.setLayoutY(145);

        
        
        
        
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) { 
                if(!textForPath.getText().equals(""))
                {
                      byte attCode=0;
                         if(rb_Read.isSelected()){
                            attCode++;
                         }
                         if(rb_Ordinary.isSelected())
                         {
                            attCode=(byte) (attCode+4);
                         }
                         if(boxForSystem.isSelected())
                             attCode=(byte) (attCode+2);
                          
                           System.out.println("attCode="+attCode);
                           
                           
                        try {
                            instances.changeAttribute(textForPath.getText(), attCode);
                                     new tipBox(stg,"修改成功！");    
                        } catch (Exception ex) {
                            ex.printStackTrace();
                                     new tipBox(stg,ex.getMessage());     
                        }  
                    
            }
                    else
                                     new tipBox(stg,"路径不能为空！");    
                    
                
            }
        });
        
        
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        
        btnOK.setLayoutX(150);
        btnOK.setLayoutY(200);
        btnOK.setText("OK");
        btnCancel.setLayoutX(350);
        btnCancel.setLayoutY(200);
        btnCancel.setText("Cancel");

        root.getChildren().addAll(path,textForPath,btnOK,btnCancel,rb_Read,rb_Ordinary,boxForSystem,attribute,isSystem,line);
        stage.setScene(scene);
        stage.show();

    }
    
        public String[] splitTheNameAndCheck(String str1){
        int realLen=0;
        String[] failTip=new String[99];
        failTip[0]="sorryYouFail";
//        String str = "hs/hv/s/aw/";  
        String str =str1;  
        String[] num =new String[99];  
        String[] num_string = str.split("/");  
//        System.out.println(num_string[0]);  
        realLen++;
        for(int i=0;i<num_string.length-1;i++){  
            num[i] = (num_string[i+1]);  
            realLen++;
        }
        String[] realString =new String[realLen];
        for(int i=0;i<realString.length;i++)
            if(i==0)
                realString[i]=num_string[0];
        else
                realString[i]=num[i-1];
        
        
                //文件名已经以/分割好，对每小段字符，进行检测
        for(int i=0;i<realString.length;i++){        
           if(realString[i].length()>3)
           {
               System.out.println("长度！"+realString[i]+"超过了每小段≤于3！！");
               return failTip;
           } 
           if(realString[i].contains("$")){
                System.out.println("特殊！ "+realString[i]+" 中存在禁止字符“$”！！");
               return failTip;
           }
           if(realString[i].contains(".")){
                System.out.println("特殊！ "+realString[i]+" 中存在禁止字符“.”！！");
               return failTip;
           }
           
        }
      
               return realString;
    
        
        
         
        }

}
