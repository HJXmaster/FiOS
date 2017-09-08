package dirOperation;

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

public class createDir {

    Button btnOK;
    Button btnCancel;
    Instances instances;
    byte attCode=8;

    public createDir(final Stage stg,Instances instances) {
        btnOK = new Button();
        btnCancel = new Button();
        this.instances=instances;
        
        final Stage stage = new Stage();
        stage.setWidth(570);
        stage.setHeight(285);
//Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
//Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle("目录的新建");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        stage.setResizable(false);
        
        Label name =new Label("目录名：");  
        name.setLayoutX(50);
        name.setLayoutY(50);
        
        TextField textForName = new TextField();
        textForName.setPromptText("请在这里输入你的目录名...");
        textForName.setLayoutX(120);
        textForName.setLayoutY(50);
        textForName.setPrefSize(400, 20);
        
        
        Label attribute = new Label("属性：");
        attribute.setLayoutX(50);
        attribute.setLayoutY(95);
        
        Line line = new Line(50, 165,520, 165); 
        //                   x始  y始  x束 y束 
        
        Label isSystem = new Label("系统文件：");
        isSystem.setLayoutX(50);
        isSystem.setLayoutY(130);
        
        final ToggleGroup groupForAtt = new ToggleGroup();  
  
        RadioButton rb_Read = new RadioButton("只读");  
        rb_Read .setToggleGroup(groupForAtt);  
        rb_Read .setLayoutX(120);
        rb_Read .setLayoutY(95);
        rb_Read .setSelected(true);  

        RadioButton rb_Ordinary = new RadioButton("普通");  
        rb_Ordinary.setToggleGroup(groupForAtt);  
        rb_Ordinary.setLayoutX(190);
        rb_Ordinary.setLayoutY(95);
        
        CheckBox boxForSystem = new CheckBox("是");
        boxForSystem.setLayoutX(120);
        boxForSystem.setLayoutY(130);


        
        
        
        
        
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
//                createFile hsy = new createFile(stg);
// 
        
        String temp;
        temp=textForName.getText();
        if(!temp.equals("")){
            
        
        attCode=8;
        if(rb_Read.isSelected()){
           attCode++;
           System.out.println("+1");
        }
        if(rb_Ordinary.isSelected())
        {
           attCode=(byte) (attCode+4);
        }
        if(boxForSystem.isSelected())
            attCode=(byte) (attCode+2);
        

        System.out.println("attCode="+attCode);
        
        
                try {
                    instances.createFolder(textForName.getText(), attCode);
                  new tipBox(stg,"恭喜你！目录创建成功");   
                stage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        else
                  new tipBox(stg,"目录名不能为空！");   
            
            
            
            }
        });
        
        
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage.close();
                
            }
        });

        
        btnOK.setLayoutX(200);
        btnOK.setLayoutY(200);
        btnOK.setText("OK");
        btnCancel.setLayoutX(350);
        btnCancel.setLayoutY(200);
        btnCancel.setText("Cancel");

        root.getChildren().addAll(btnOK,btnCancel,rb_Read,rb_Ordinary,boxForSystem,name,attribute,isSystem,line,textForName);
        stage.setScene(scene);
       stage.getIcons().add(new Image(("Icons/ourIcon.png")));   
        stage.show();

    }

}
