package fileOperation;

import tools.tipBox;
import main.Instances;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public class createFile {

    Button btnOK;
    Button btnCancel;
    Instances instances;

    public createFile(final Stage stg,Instances instances) {
        btnOK = new Button();
        btnCancel = new Button(); 
        final Stage stage = new Stage();
        stage.setWidth(570);
        stage.setHeight(305);
//Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
//Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("文件的新建");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        stage.setResizable(false);
         
        
        
        Label name =new Label("文件名：");  
        name.setLayoutX(50);
        name.setLayoutY(50);
        
        TextField textForName = new TextField();
        textForName.setPromptText("不超过20个字节...");
        textForName.setLayoutX(120);
        textForName.setLayoutY(50);
        textForName.setPrefSize(400, 20);
        
        Label type =new Label("类型：");
        type.setLayoutX(50);
        type.setLayoutY(95);
        
        TextField textForType = new TextField();
        textForType.setPromptText("不超过2个字节...");
        textForType.setLayoutX(120);
        textForType.setLayoutY(95);
        textForType.setPrefSize(100, 20);
        
        Label attribute = new Label("属性：");
        attribute.setLayoutX(50);
        attribute.setLayoutY(140);
        
        Line line = new Line(50, 205,520, 205); 
        //                   x始  y始  x束 y束 
        
        Label isSystem = new Label("系统文件：");
        isSystem.setLayoutX(50);
        isSystem.setLayoutY(175);
        
        final ToggleGroup groupForAtt = new ToggleGroup();  
  
        RadioButton rb_Read = new RadioButton("只读");  
        rb_Read .setToggleGroup(groupForAtt);  
        rb_Read .setLayoutX(120);
        rb_Read .setLayoutY(140);
        rb_Read .setSelected(true);  

        RadioButton rb_Ordinary = new RadioButton("普通");  
        rb_Ordinary.setToggleGroup(groupForAtt);  
        rb_Ordinary.setLayoutX(200);
        rb_Ordinary.setLayoutY(140);
        
        CheckBox boxForSystem = new CheckBox("是");
        boxForSystem.setLayoutX(120);
        boxForSystem.setLayoutY(175);

        
        
        
        
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                int winFlag=1;
                
                String[] failTip=new String[99];
                failTip[0]="sorryYouFail";
//                createFile hsy = new createFile(stg);
                String[] temp=new String[99];
                temp=splitTheNameAndCheck(textForName.getText());
                //文件名为空
                if(temp[0].equals("")){
//                    System.out.println("为空！第一个字符不能为空！！");
                  new tipBox(stg,"为空！第一个字符不能为空！！");     
                    winFlag=0;
                }
                
                if(textForName.getText().length()>20){
//                    System.out.println("超长！文件名超过了20！！");
                  new tipBox(stg,"超长！文件名超过了20！！");     
                    winFlag=0;
                }
                //文件名不为空
                else
                {
                //文件名格式错误
                if(temp[0].equals(failTip[0])){
//                    System.out.println("你手上什么也没有！"); 
                  new tipBox(stg,"你手上什么也没有！");     

                    winFlag=0;
                }
                //文件名验证通过
                else{
//               System.out.println("【文件名检测通过！！】");  
               
                }
                }
                
                
                if(textForType.getText().length()>2){
//                    System.out.println("超长！类型长度超过2个字符！");
                  new tipBox(stg,"超长！类型长度超过2个字符！");     

                    winFlag=0;
                }
                else
                {
                    String testForType=textForType.getText(); 
                    if(testForType.length()>=1)
                        System.out.println("");     
                    else{
//                        System.out.println("长度！类型长度不够！");
                  new tipBox(stg,"长度！类型长度不够！");
                     winFlag=0;   
                    }
                }
                System.out.println("-------------------------");
                
                
                
                if(winFlag==1){
                    System.out.println("本次检测全关通过！");
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
                          
                           
                           
                        try {
                            instances.createFile(textForName.getText(),textForType.getText(),attCode);
                  new tipBox(stg,"恭喜你！文件创建成功");     
                stage.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
//                            System.out.println(ex.getMessage());
                              
                  new tipBox(stg,ex.getMessage());     
                        }  
                    
                    
                    
                    
                }
            }
        });
        
        
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        
        btnOK.setLayoutX(200);
        btnOK.setLayoutY(220);
        btnOK.setText("OK");
        btnCancel.setLayoutX(350);
        btnCancel.setLayoutY(220);
        btnCancel.setText("Cancel");

        root.getChildren().addAll(btnOK,btnCancel,rb_Read,rb_Ordinary,boxForSystem,name,type,attribute,isSystem,line,textForName,textForType);
        stage.setScene(scene);
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
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
