/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import fileOperation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.DirectoryChooserBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import structures.FAT;
import tools.tipBox;
//import javax.swing.JFileChooser; 

public class Starter extends Application {

    private Button button1 = new Button("展示FAT");
    private Button button2 = new Button("展示磁盘存储表");
    private Button button3 = new Button("展示已开文件列表");
//   private    Button btCreateFile=new Button("创建文件");
//     private  Button button5=new Button("读文件");
//    private   Button button6=new Button("写文件");
//    private   Button button7=new Button("浏览");
    private Button button8 = new Button("播放");

    Button btn_operation;

    private ComboBox<String> cbo = new ComboBox<>();
    Instances instance = new Instances();
    File dir;//dir用来放FileDialog里用户指定的文件夹
    String bufferForDir = "";

    @Override
    //private Stage primaryStage=new Stage();
    public void start(Stage primaryStage) {

        HBox hb = new HBox();

        ImageView showPic = new ImageView(new Image("Icons/title2.png"));
        ImageView operationPic = new ImageView(new Image("Icons/icon_opera.png"));

        ImageView ourIcon = new ImageView(new Image("Icons/ourIcon.png"));

        FadeTransition ft = new FadeTransition(Duration.millis(500), showPic);
        ft.setFromValue(3.0);
        ft.setToValue(0.3);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
        FadeTransition ft2 = new FadeTransition(Duration.millis(500), ourIcon);
        ft2.setFromValue(3.0);
        ft2.setToValue(0.3);
        ft2.setCycleCount(Timeline.INDEFINITE);
        ft2.setAutoReverse(true);
        ft2.play();

        showPic.setScaleX(1.4);
        showPic.setScaleY(1.4);

        btn_operation = new Button("操作");
        hb.setAlignment(Pos.CENTER);
        primaryStage.setWidth(820);
        primaryStage.setHeight(550);

        Group root = new Group();
        root.getChildren().addAll(showPic, btn_operation, button1, button2, button3, button8, cbo, ourIcon);
        showPic.setLayoutX(330);
        showPic.setLayoutY(100);
        button1.setLayoutX(140);
        button1.setLayoutY(250);
        btn_operation.setLayoutX(350);
        btn_operation.setLayoutY(250);

        button3.setLayoutX(140);
        button3.setLayoutY(370);

        button2.setLayoutX(430);
        button2.setLayoutY(370);

        button8.setLayoutX(550);
        button8.setLayoutY(250);

        ourIcon.setLayoutX(100);
        ourIcon.setLayoutY(10);
//      btCreateFile.setLayoutX(200);
//            btCreateFile.setLayoutY(400);
//      btCreateFile.setLayoutX(300);
//            btCreateFile.setLayoutY(370);
//      button5.setLayoutX(450);
//            button5.setLayoutY(370);
//      button6.setLayoutX(600);
//            button6.setLayoutY(370);
//      button7.setLayoutX(590);
//            button7.setLayoutY(10); 

        cbo.setLayoutX(690);
        cbo.setLayoutY(10);

        Scene scene = new Scene(root, 300, 250, Color.valueOf("#66ffff"));
        //#ff99cc

        primaryStage.setTitle("Fios 磁盘管理系统V1.9");
        primaryStage.setScene(scene);
        primaryStage.show();

//        button6.setOnAction(e->new writeFile(primaryStage,instance));
//        
        btn_operation.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.hide();
                new chooseForCreate(primaryStage, instance);
            }
        });

//        button5.setOnAction(e->new readFile(primaryStage,instance));
        Media media = new Media("http://cs.armstrong.edu/liang/common/audio/anthem/anthem4.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        button8.setOnAction(e -> {
            if (button8.getText().equals("播放")) {
                mediaPlayer.play();
                button8.setText("暂停");
            } else {
                mediaPlayer.pause();
                button8.setText("播放");
            }
        });

        button1.setOnAction(e -> showFAT());
        button2.setOnAction(e -> showDISK());
        button3.setOnAction(e -> showOpenFile());


        ObservableList<String> options
                = FXCollections.observableArrayList("帮助", "疑难解答", "检查更新");

        cbo.getItems().addAll(options);
        cbo.setValue("帮助");
        cbo.setOnAction(e -> showCombo(options.indexOf(cbo.getValue())));

        scene.getStylesheets().add("mm2.css");

        primaryStage.getIcons().add(new Image(("Icons/ourIcon.png")));
        primaryStage.setResizable(false);

    }

    public static void main(String[] args) {
        Application.launch(args);

    }

//    public void getFolder() {       
//
//        JFileChooser chooser = new JFileChooser();// 创建文件选择器
//        // 设置选择器只针对“文件夹”生效
//        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int option = chooser.showOpenDialog(this.btCreateFile);// 显示文件打开对话框
//        if (option == JFileChooser.APPROVE_OPTION) {
//            
//            dir = chooser.getSelectedFile();// 获取选择的文件夹
//            tfCreateFile.setText(dir.toString());// 显示文件夹到文本框
//            bufferForDir=dir.toString();            
//        } else {
//                           tfCreateFile.setForeground(Color.black);
//            tfCreateFile.setText(bufferForDir);
//        }
//            tfCreateFile.setVisible(true);
//    }   
//    public void getFolder() { 
//        FileChooser chooser=new FileChooser();
//        chooser.setTitle("你妹");
//        //chooser.showOpenDialog(primaryStage);//
//    }
    public void showCombo(int index) {
        if (index == 0) {
            Stage stage = new Stage();
            HBox hb = new HBox();

            Scene scene = new Scene(hb);
            stage.setTitle("0");
            stage.setScene(scene);
            stage.show();
        }
        if (index == 1) {
            Stage stage = new Stage();
            HBox hb = new HBox();

            Scene scene = new Scene(hb);
            stage.setTitle("1");
            stage.setScene(scene);
            stage.show();
        }

        if (index == 2) {
            Stage stage = new Stage();
            HBox hb = new HBox();

            Scene scene = new Scene(hb);
            stage.setTitle("2");
            stage.setScene(scene);
            stage.show();
        }

    }

    public void showDISK() {
        Stage stage = new Stage();
        stage.setWidth(820);
        stage.setHeight(550);
        HBox searchBox = new HBox();
        searchBox.setPadding(new Insets(10, 20, 10, 20));
        searchBox.setSpacing(15);
        TextField seachField = new TextField();
        seachField.setMinSize(350, 40);
        seachField.setMaxSize(350, 40);
        Button okButton = new Button("确定");
//        okButton.setMaxSize(20, 1);

        searchBox.getChildren().addAll(seachField, okButton);

        VBox vbTotol = new VBox();
        vbTotol.getStyleClass().add("diskCss");
//        byte[][] diskBytes=new byte[128][64];
        byte[][] diskBytes = instance.getDisk().getBlocks();
        VBox vb = new VBox();
        vb.getStyleClass().add("diskCss");
        ArrayList<Button> arraylist = new ArrayList<>();
//        vb.getChildren().add(searchBox);
        HBox hbNum = new HBox();
        ArrayList<HBox> aa = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            HBox hb = new HBox();
            aa.add(hb);
        }
        hbNum.getStyleClass().add("diskCss");
        for (int i = 0; i < diskBytes.length; i++) {
            Button numButton = new Button(String.valueOf(i));
            byte[] blockCon = diskBytes[i];
            String string = String.valueOf(i);
            numButton.setOnAction(e -> blockCon(blockCon, string));
            arraylist.add(numButton);
            hbNum.getChildren().add(numButton);//
            if (i >= 0 && i < 16) {
                aa.get(0).getChildren().add(numButton);
            } else if (i >= 16 && i < 32) {
                aa.get(1).getChildren().add(numButton);
            } else if (i >= 32 && i < 48) {
                aa.get(2).getChildren().add(numButton);
            } else if (i >= 48 && i < 64) {
                aa.get(3).getChildren().add(numButton);
            } else if (i >= 64 && i < 80) {
                aa.get(4).getChildren().add(numButton);
            } else if (i >= 80 && i < 96) {
                aa.get(5).getChildren().add(numButton);
            } else if (i >= 96 && i < 112) {
                aa.get(6).getChildren().add(numButton);
            } else {
                aa.get(7).getChildren().add(numButton);
            }
        }

        okButton.setOnAction(e1 -> {
            boolean flag=false;
            for (int i = 0; i < diskBytes.length; i++) {
                byte[] blockCon = diskBytes[i];
                if (seachField.getText().equals(String.valueOf(i))) {
//                System.out.println("有");
                    blockCon(blockCon, String.valueOf(i));
                    flag=true;
                }
            }
            if(!flag)
            new tipBox(new Stage(),"找不到文件块!\r\n请确保输入的值在0-127！");
        });


        vb.getChildren().add(hbNum);
        for (int i = 0; i < aa.size(); i++) {
            vb.getChildren().add(aa.get(i));
        }
//        vb.setMinHeight(300);
        vb.setSpacing(10);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("diskCss");
        scrollPane.setContent(vb);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setMinHeight(300);
        scrollPane.setPadding(new Insets(10, 0, 0, 0));
        Label label = new Label("磁盘存储表");
        FadeTransition ft = new FadeTransition(Duration.millis(500), label);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
        HBox hb = new HBox();
        hb.getStyleClass().add("diskCss");
        hb.setPadding(new Insets(10, 0, 10, 0));
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(label);
        okButton.getStyleClass().add("okButton");
//        vbTotol.getChildren().add(new Button("开心"));
        vbTotol.getChildren().add(hb);
        vbTotol.getChildren().add(searchBox);
        vbTotol.getChildren().add(scrollPane);
        Scene scene = new Scene(vbTotol);
        scene.getStylesheets().add("disk.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("磁盘存储表");
        stage.show();

    }

    public void blockCon(byte[] blockCon, String string) {
        Stage stage = new Stage();
        HBox hb = new HBox();
        String string1 = "磁盘" + string + "内容";
        Label label = new Label(string1);

        FadeTransition ft = new FadeTransition(Duration.millis(500), label);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();

        hb.getChildren().add(label);
        hb.setPadding(new Insets(5, 0, 5, 0));
        hb.setAlignment(Pos.CENTER);
        VBox vbTotal = new VBox();

        VBox vb = new VBox();
//        HBox[] hBoxs=new HBox[8];
        ArrayList<HBox> hBoxs = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            HBox hboxNum = new HBox();
            Button bt = new Button(String.valueOf(i));

            bt.getStyleClass().add("btNum");
            hboxNum.getChildren().add(bt);
            hBoxs.add(hboxNum);
        }
        for (int i = 0; i < blockCon.length; i++) {
            if (i >= 0 && i < 8) {

                hBoxs.get(0).getChildren().add(new Button(String.valueOf(blockCon[i])));
            } else if (i >= 8 && i < 16) {
                hBoxs.get(1).getChildren().add(new Button(String.valueOf(blockCon[i])));
            } else if (i >= 16 && i < 24) {
                hBoxs.get(2).getChildren().add(new Button(String.valueOf(blockCon[i])));
            } else if (i >= 24 && i < 32) {
                hBoxs.get(3).getChildren().add(new Button(String.valueOf(blockCon[i])));
            } else if (i >= 32 && i < 40) {
                hBoxs.get(4).getChildren().add(new Button(String.valueOf(blockCon[i])));
            } else if (i >= 40 && i < 48) {
                hBoxs.get(5).getChildren().add(new Button(String.valueOf(blockCon[i])));
            } else if (i >= 48 && i < 56) {
                hBoxs.get(6).getChildren().add(new Button(String.valueOf(blockCon[i])));
            } else {
                hBoxs.get(7).getChildren().add(new Button(String.valueOf(blockCon[i])));
            }

        }
        vb.setSpacing(8);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vb);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        for (int i = 0; i < hBoxs.size(); i++) {
            vb.getChildren().add(hBoxs.get(i));
        }
        vbTotal.getChildren().addAll(hb, scrollPane);
        hb.getStyleClass().add("allbox");
        scrollPane.getStyleClass().add("allbox");

        Scene scene = new Scene(vbTotal, 550, 600);
        scene.getStylesheets().add("diskCon.css");
        stage.setScene(scene);
        stage.setTitle("磁盘内容");
        stage.show();
        stage.setResizable(false);

    }
    
    public void showFAT()
    {
        Stage stage=new Stage();
        stage.setWidth(820);
        stage.setHeight(550); 
        
        
        //总box
        VBox vb=new VBox();
       
//        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(50);
        
        VBox fat1Box=new VBox();//fat1表
 
        VBox fat1Title=new VBox();//fat1标题
        Label label1=new Label("Table FAT1");
 
        FadeTransition ft=new FadeTransition(Duration.millis(500),label1);
        ft.setFromValue(1.0);  
        ft.setToValue(0.3);  
        ft.setCycleCount(Timeline.INDEFINITE);  
        ft.setAutoReverse(true);  
        ft.play();  
        
        fat1Title.getChildren().add(label1);
        fat1Title.setAlignment(Pos.CENTER_LEFT);
        
        HBox fat1Num=new HBox();//fat1的序号
        ArrayList<HBox> arrayList01=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            HBox aa1=new HBox();
            arrayList01.add(aa1);
        }
        for(int i=0;i<64;i++){
//        fat1Num.getChildren().add(new Button(String.valueOf(i)));
            String string=String.valueOf(i);
            if(i>=0&&i<16)
            {
                Button buttonkk1=new Button(string);
                buttonkk1.getStyleClass().add("kk");
                arrayList01.get(0).getChildren().add(buttonkk1);
            }else if(i>=16&&i<32){
                 Button buttonkk2=new Button(string);
                  buttonkk2.getStyleClass().add("kk");
                  arrayList01.get(1).getChildren().add(buttonkk2);
            }else if(i>=32&&i<48){
                Button buttonkk3=new Button(string);
                 buttonkk3.getStyleClass().add("kk");
                  arrayList01.get(2).getChildren().add(buttonkk3);
            }else{
                Button buttonkk4=new Button(string);
                 buttonkk4.getStyleClass().add("kk");
                  arrayList01.get(3).getChildren().add(buttonkk4);
            }
        }
        
        HBox fat1Con=new HBox();//fat1的内容
          
//        byte[] fat1Bytes=new byte[64];//
        FAT fat=instance.getFAT();
        byte[] fat1Bytes= fat.getSingleFAT(0);
        ArrayList<HBox> arrayList1=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            arrayList1.add(new HBox());
        }
        for(int i=0;i<fat1Bytes.length;i++)
        {
//            fat1Con.getChildren().add(new Button(String.valueOf(fat1Bytes[i])));
            if(i>=0&&i<16)
            {
                arrayList1.get(0).getChildren().add(new Button(String.valueOf(fat1Bytes[i])));
            }else if(i>=16&&i<32){
                arrayList1.get(1).getChildren().add(new Button(String.valueOf(fat1Bytes[i])));
            }else if(i>=32&&i<48)
            {
                arrayList1.get(2).getChildren().add(new Button(String.valueOf(fat1Bytes[i])));
            }
            else {
                arrayList1.get(3).getChildren().add(new Button(String.valueOf(fat1Bytes[i])));
            }
        }
//        fat1Box.getChildren().add(fat1Num);
       
        for(int i=0;i<arrayList1.size();i++)
        { 
            fat1Box.getChildren().add(arrayList01.get(i));
            fat1Box.getChildren().add(arrayList1.get(i));
        }
        fat1Num.getStyleClass().add("fat1box");
           fat1Con.getStyleClass().add("fat1box");
        
         //fat2表
        VBox fat2Box=new VBox();
        
        VBox fat2Title=new VBox();//fat2标题
        
        Label label2=new Label("Table FAT2");
         FadeTransition ft2=new FadeTransition(Duration.millis(500),label2);
        ft2.setFromValue(1.0);  
        ft2.setToValue(0.3);  
        ft2.setCycleCount(Timeline.INDEFINITE);  
        ft2.setAutoReverse(true);  
        ft2.play();  
        fat2Title.getChildren().add(label2);
        
        HBox fat2Num=new HBox();//fat2的序号
        
//        for(int i=64;i<128;i++){
//        fat2Num.getChildren().add(new Button(String.valueOf(i)));
//        }
        
        ArrayList<HBox> arrayList02=new ArrayList<>();
        for(int i=0;i<4;i++)
            arrayList02.add(new HBox());
        for(int i=0;i<64;i++){
//        fat1Num.getChildren().add(new Button(String.valueOf(i)));
            String string=String.valueOf(i);
            if(i>=0&&i<16)
            {
                Button mm1=new Button(string);
                mm1.getStyleClass().add("kk");
                arrayList02.get(0).getChildren().add(mm1);
            }else if(i>=16&&i<32){
                Button mm2=new Button(string);
                 mm2.getStyleClass().add("kk");
                  arrayList02.get(1).getChildren().add(mm2);
            }else if(i>=32&&i<48){
                Button mm3=new Button(string);
                 mm3.getStyleClass().add("kk");
                  arrayList02.get(2).getChildren().add(mm3);
            }else{
                Button mm4=new Button(string);
                 mm4.getStyleClass().add("kk");
                  arrayList02.get(3).getChildren().add(mm4);
            }
        }
        
        HBox fat2Con=new HBox();//fat2的内容
       
//        byte[] fat2Bytes=new byte[64];
           FAT fat1=instance.getFAT();
        byte[] fat2Bytes= fat1.getSingleFAT(1);
        ArrayList<HBox> arrayList2=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            arrayList2.add(new HBox());
        }
        for(int i=0;i<fat2Bytes.length;i++)
        {
//            fat2Con.getChildren().add(new Button(String.valueOf(fat2Bytes[i])));
          if(i>=0&&i<16)
            {
                arrayList2.get(0).getChildren().add(new Button(String.valueOf(fat2Bytes[i])));
            }else if(i>=16&&i<32){
                arrayList2.get(1).getChildren().add(new Button(String.valueOf(fat2Bytes[i])));
            }else if(i>=32&&i<48)
            {
                arrayList2.get(2).getChildren().add(new Button(String.valueOf(fat2Bytes[i])));
            }
            else {
                arrayList2.get(3).getChildren().add(new Button(String.valueOf(fat2Bytes[i])));
            }
        }
        fat2Box.getChildren().add(fat2Num);
        for(int i=0;i<arrayList2.size();i++)
        {
            fat2Box.getChildren().add(arrayList02.get(i));
            fat2Box.getChildren().add(arrayList2.get(i));
        }
          fat2Num.getStyleClass().add("fat1box");
            fat2Con.getStyleClass().add("fat1box");
       
        
//        label2.getStyleClass().add("mm1");
        vb.getChildren().addAll(fat1Title,fat1Box,fat2Title,fat2Box);
         fat1Title.getStyleClass().add("fat1box");
        fat1Box.getStyleClass().add("fat1box");
        fat2Title.getStyleClass().add("fat1box");
        fat2Box.getStyleClass().add("fat1box");
        vb.getStyleClass().add("fat1box");
               
       
        //滚动窗口
        ScrollPane scrollPane=new ScrollPane();
        scrollPane.getStyleClass().add("fat1box");
        scrollPane.setContent(vb);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Scene scene=new Scene(scrollPane);
        scene.getStylesheets().add("fat.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("展示FAT");
        stage.show();
    }
     public void showOpenFile(){
        Stage stage=new Stage();
        
        //总Box
        VBox vbTotol=new VBox();
        vbTotol.setAlignment(Pos.CENTER);
        vbTotol.setSpacing(20);
        HBox hbTitle=new HBox();
        Label label=new Label("展示已开文件列表");
             FadeTransition ft=new FadeTransition(Duration.millis(500),label);
        ft.setFromValue(1.0);  
        ft.setToValue(0.3);  
        ft.setCycleCount(Timeline.INDEFINITE);  
        ft.setAutoReverse(true);  
        ft.play();  
        hbTitle.getChildren().add(label);
        hbTitle.setPadding(new Insets(30,0,30,0));
        hbTitle.setAlignment(Pos.CENTER);
        
        
        //第一个Box
        VBox vbTotol1=new VBox();
        HBox hbTotol1Top=new HBox();
        HBox hbTotal1Down=new HBox();
        //
        Button bb11=new Button("文件路径名");
        bb11.getStyleClass().add("buttonfir");
        Button bb12=new Button("文件属性");
        Button bb13=new Button("起始盘长度");
        Button bb14=new Button("文件长度");
        Button bb15=new Button("操作类型");
        Button bb16=new Button("读指针块号");
        Button bb17=new Button("读指针块内地址");
        Button bb18=new Button("写指针块号");
        Button bb19=new Button("写指针块内地址");
        //

        Button pp11=new Button("");
         pp11.getStyleClass().add("buttonfir");
        String ss11="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss11=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getPath());
        }
        pp11.setText(ss11);
        
        
        
        
        //------------
        Button pp12=new Button();
        String ss12="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss12=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getAttribute());
        }
        pp12.setText(ss12);
        //-----------
//       Button pp13=new Button(String.valueOf( instance.getOpenFileList().getOpenFile(0).getBlockIndex()));
        Button pp13=new Button();
        String ss13="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss13=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getBlockIndex());
        }
        pp13.setText(ss13);
        //-----------
        Button pp14=new Button();
        //Button pp14=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(0).getLength()));
        String ss14="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss14=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getLength());
        }
        pp14.setText(ss14);
        //----------
        //Button pp15=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(0).isIsOpenInWrite()));
        Button pp15=new Button();
        String ss15="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss15=String.valueOf(  instance.getOpenFileList().getOpenFile(0).isIsOpenInWrite());
        }
        pp15.setText(ss15);
          //----------
       // Button pp16=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(0).getRead().getBlockIndex()));
        Button pp16=new Button();
        String ss16="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss16=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getRead().getBlockIndex());
        }
        pp16.setText(ss16);
          //----------
//        Button pp17=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(0).getRead().getByteIndex()));
        Button pp17=new Button();
        String ss17="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss17=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getRead().getByteIndex());
        }
        pp17.setText(ss17);
          //----------
//        Button pp18=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(0).getWrite().getBlockIndex()));
        Button pp18=new Button();
        String ss18="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss18=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getWrite().getBlockIndex());
        }
        pp18.setText(ss18);
          //----------
//        Button pp19=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(0).getWrite().getByteIndex()));
        Button pp19=new Button();
        String ss19="";
        if(instance.getOpenFileList().getOpenFile(0)!=null)
        {
            ss19=String.valueOf(  instance.getOpenFileList().getOpenFile(0).getWrite().getByteIndex());
        }
        pp19.setText(ss19);
        //
        hbTotol1Top.getChildren().addAll(bb11,bb12,bb13,bb14,bb15,bb16,bb17,bb18,bb19);
        hbTotal1Down.getChildren().addAll(pp11,pp12,pp13,pp14,pp15,pp16,pp17,pp18,pp19);
        vbTotol1.getChildren().addAll(hbTotol1Top,hbTotal1Down);
        
        
        
        
        
        
        
        
        
        //第二个Box
        VBox vbTotol2=new VBox();
        HBox hbTotol2Top=new HBox();
        HBox hbTotal2Down=new HBox();
        //
        Button bb21=new Button("文件路径名");
        bb21.getStyleClass().add("buttonfir");
//        Button pp21=new Button();
//        String ss21="";
//        if(instance.getOpenFileList().getOpenFile(0)!=null)
//        {
//            ss21=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getAttribute());
//        }
//        pp21.setText(ss21);
          //----------
        Button bb22=new Button("文件属性");
//        String ss11="";
//        if(instance.getOpenFileList().getOpenFile(0)!=null)
//        {
//            ss11=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getAttribute());
//        }
//        pp11.setText(ss11);
//          //----------
        Button bb23=new Button("起始盘长度");
//        String ss11="";
//        if(instance.getOpenFileList().getOpenFile(0)!=null)
//        {
//            ss11=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getAttribute());
//        }
//        pp11.setText(ss11);
//          //----------
        Button bb24=new Button("文件长度");
//        String ss11="";
//        if(instance.getOpenFileList().getOpenFile(0)!=null)
//        {
//            ss11=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getAttribute());
//        }
//        pp11.setText(ss11);
          //----------
        Button bb25=new Button("操作类型");
//        String ss11="";
//        if(instance.getOpenFileList().getOpenFile(0)!=null)
//        {
//            ss11=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getAttribute());
//        }
//        pp11.setText(ss11);
          //----------
        Button bb26=new Button("读指针块号");

        Button bb27=new Button("读指针块内地址");

        Button bb28=new Button("写指针块号");

        Button bb29=new Button("写指针块内地址");

        Button pp21=new Button();
          pp21.getStyleClass().add("buttonfir");
                String ss21="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss21=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getPath());
        }
        pp21.setText(ss21);

        Button pp22=new Button();
        String ss22="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss22=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getAttribute());
        }
        pp22.setText(ss22);
          //----------
        
        Button pp23=new Button();
       // Button pp23=new Button(String.valueOf( instance.getOpenFileList().getOpenFile(1).getBlockIndex()));
        String ss23="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss23=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getBlockIndex());
        }
        pp23.setText(ss23);
          //----------
        Button pp24=new Button();
        String ss24="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss24=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getLength());
        }
        pp24.setText(ss24);
          //----------
        Button pp25=new Button();
        //Button pp25=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(1).isIsOpenInWrite()));
        String ss25="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss25=String.valueOf(  instance.getOpenFileList().getOpenFile(1).isIsOpenInWrite());
        }
        pp25.setText(ss25);
          //----------
         Button pp26=new Button();
        //Button pp26=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(1).getRead().getBlockIndex()));
        String ss26="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss26=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getRead().getBlockIndex());
        }
        pp26.setText(ss26);
          //----------
         Button pp27=new Button();
        //Button pp27=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(1).getRead().getByteIndex()));
        String ss27="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss27=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getRead().getByteIndex());
        }
        pp27.setText(ss27);
          //----------
         Button pp28=new Button();
        //Button pp28=new Button(String.valueOf( instance.getOpenFileList().getOpenFile(1).getWrite().getBlockIndex()));
        String ss28="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss28=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getWrite().getBlockIndex());
        }
        pp28.setText(ss28);
          //----------
         Button pp29=new Button();
        //Button pp29=new Button(String.valueOf(instance.getOpenFileList().getOpenFile(1).getWrite().getByteIndex()));
        String ss29="";
        if(instance.getOpenFileList().getOpenFile(1)!=null)
        {
            ss29=String.valueOf(  instance.getOpenFileList().getOpenFile(1).getWrite().getByteIndex());
        }
        pp29.setText(ss29);
        //
        hbTotol2Top.getChildren().addAll(bb21,bb22,bb23,bb24,bb25,bb26,bb27,bb28,bb29);
        hbTotal2Down.getChildren().addAll(pp21,pp22,pp23,pp24,pp25,pp26,pp27,pp28,pp29);
        vbTotol2.getChildren().addAll(hbTotol2Top,hbTotal2Down);
        
        
        
        
        
        
        
        
        
        //第三个Box
        VBox vbTotol3=new VBox();
        HBox hbTotol3Top=new HBox();
        HBox hbTotal3Down=new HBox();
        //
        Button bb31=new Button("文件路径名");
         bb31.getStyleClass().add("buttonfir");
        Button bb32=new Button("文件属性");
        Button bb33=new Button("起始盘长度");
        Button bb34=new Button("文件长度");
        Button bb35=new Button("操作类型");
        Button bb36=new Button("读指针块号");
        Button bb37=new Button("读指针块内地址");
        Button bb38=new Button("写指针块号");
        Button bb39=new Button("写指针块内地址");
        //
//        instance.getOpenFileList().getOpenFile(2).getPath();
//        instance.getOpenFileList().getOpenFile(2).getAttribute();
//        instance.getOpenFileList().getOpenFile(2).getBlockIndex();
//        instance.getOpenFileList().getOpenFile(2).getLength();
//        instance.getOpenFileList().getOpenFile(2).isIsOpenInWrite();
//        instance.getOpenFileList().getOpenFile(2).getRead().getBlockIndex();
//        instance.getOpenFileList().getOpenFile(2).getRead().getByteIndex();
//        instance.getOpenFileList().getOpenFile(2).getWrite().getBlockIndex();
//        instance.getOpenFileList().getOpenFile(2).getWrite().getByteIndex();
 
        Button pp31=new Button();
        pp31.getStyleClass().add("buttonfir");
        String ss31="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss31=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getPath());
        }
        pp31.setText(ss31);
          //----------
        Button pp32=new Button();
        String ss32="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss32=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getAttribute());
        }
        pp32.setText(ss32);
          //----------
        Button pp33=new Button();
        String ss33="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss33=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getBlockIndex());
        }
        pp33.setText(ss33);
          //----------
        Button pp34=new Button();
        String ss34="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss34=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getLength());
        }
        pp34.setText(ss34);
          //----------
        Button pp35=new Button();
        String ss35="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss35=String.valueOf(  instance.getOpenFileList().getOpenFile(2).isIsOpenInWrite());
        }
        pp35.setText(ss35);
          //----------
        Button pp36=new Button();
        String ss36="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss36=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getRead().getBlockIndex());
        }
        pp36.setText(ss36);
          //----------
        Button pp37=new Button();
        String ss37="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss37=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getRead().getByteIndex());
        }
        pp37.setText(ss37); 
          //----------
        Button pp38=new Button();
        String ss38="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss38=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getWrite().getBlockIndex());
        }
        pp38.setText(ss38);
          //----------
        Button pp39=new Button();
        String ss39="";
        if(instance.getOpenFileList().getOpenFile(2)!=null)
        {
            ss39=String.valueOf(  instance.getOpenFileList().getOpenFile(2).getWrite().getByteIndex());
        }
        pp39.setText(ss39);
        //
          //----------
        hbTotol3Top.getChildren().addAll(bb31,bb32,bb33,bb34,bb35,bb36,bb37,bb38,bb39);
        hbTotal3Down.getChildren().addAll(pp31,pp32,pp33,pp34,pp35,pp36,pp37,pp38,pp39);
        vbTotol3.getChildren().addAll(hbTotol3Top,hbTotal3Down);
        
        
        
        
        
        
        
        //第四个Box
        VBox vbTotol4=new VBox();
        HBox hbTotol4Top=new HBox();
        HBox hbTotal4Down=new HBox();
        //
        Button bb41=new Button("文件路径名");
         bb41.getStyleClass().add("buttonfir");
        Button bb42=new Button("文件属性");
        Button bb43=new Button("起始盘长度");
        Button bb44=new Button("文件长度");
        Button bb45=new Button("操作类型");
        Button bb46=new Button("读指针块号");
        Button bb47=new Button("读指针块内地址");
        Button bb48=new Button("写指针块号");
        Button bb49=new Button("写指针块内地址");
        //
//        instance.getOpenFileList().getOpenFile(3).getPath();
//        instance.getOpenFileList().getOpenFile(3).getAttribute();
//        instance.getOpenFileList().getOpenFile(3).getBlockIndex();
//        instance.getOpenFileList().getOpenFile(3).getLength();
//        instance.getOpenFileList().getOpenFile(3).isIsOpenInWrite();
//        instance.getOpenFileList().getOpenFile(3).getRead().getBlockIndex();
//        instance.getOpenFileList().getOpenFile(3).getRead().getByteIndex();
//        instance.getOpenFileList().getOpenFile(3).getWrite().getBlockIndex();
//        instance.getOpenFileList().getOpenFile(3).getWrite().getByteIndex();
        Button pp41=new Button();
        pp41.getStyleClass().add("buttonfir");
        String ss41="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss41=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getPath());
        }
        pp41.setText(ss41);
          //----------
        Button pp42=new Button();
        String ss42="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss42=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getAttribute());
        }
        pp42.setText(ss42);
          //----------
        Button pp43=new Button();
        String ss43="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss43=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getBlockIndex());
        }
        pp43.setText(ss43);
          //----------
        Button pp44=new Button();
        String ss44="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss44=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getLength());
        }
        pp44.setText(ss44);
          //----------
        Button pp45=new Button();
        String ss45="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss45=String.valueOf(  instance.getOpenFileList().getOpenFile(3).isIsOpenInWrite());
        }
        pp45.setText(ss45);
          //----------
        Button pp46=new Button();
        String ss46="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss46=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getRead().getBlockIndex());
        }
        pp46.setText(ss46);
          //----------
        Button pp47=new Button();
        String ss47="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss47=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getRead().getByteIndex());
        }
        pp47.setText(ss47);
          //----------
        Button pp48=new Button();
        String ss48="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss48=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getWrite().getBlockIndex());
        }
        pp48.setText(ss48);
          //----------
        Button pp49=new Button();
        String ss49="";
        if(instance.getOpenFileList().getOpenFile(3)!=null)
        {
            ss49=String.valueOf(  instance.getOpenFileList().getOpenFile(3).getWrite().getByteIndex());
        }
        pp49.setText(ss49);
          //----------
        //
        hbTotol4Top.getChildren().addAll(bb41,bb42,bb43,bb44,bb45,bb46,bb47,bb48,bb49);
        hbTotal4Down.getChildren().addAll(pp41,pp42,pp43,pp44,pp45,pp46,pp47,pp48,pp49);
        vbTotol4.getChildren().addAll(hbTotol4Top,hbTotal4Down);
        
        
        
        
        
        
        //第五个Box
        VBox vbTotol5=new VBox();
        HBox hbTotol5Top=new HBox();
        HBox hbTotal5Down=new HBox();
        //
        Button bb51=new Button("文件路径名");
          bb51.getStyleClass().add("buttonfir");
        Button bb52=new Button("文件属性");
        Button bb53=new Button("起始盘长度");
        Button bb54=new Button("文件长度");
        Button bb55=new Button("操作类型");
        Button bb56=new Button("读指针块号");
        Button bb57=new Button("读指针块内地址");
        Button bb58=new Button("写指针块号");
        Button bb59=new Button("写指针块内地址");
        //
//        instance.getOpenFileList().getOpenFile(4).getPath();
//        instance.getOpenFileList().getOpenFile(4).getAttribute();
//        instance.getOpenFileList().getOpenFile(4).getBlockIndex();
//        instance.getOpenFileList().getOpenFile(4).getLength();
//        instance.getOpenFileList().getOpenFile(0).isIsOpenInWrite();
//        instance.getOpenFileList().getOpenFile(4).getRead().getBlockIndex();
//        instance.getOpenFileList().getOpenFile(4).getRead().getByteIndex();
//        instance.getOpenFileList().getOpenFile(4).getWrite().getBlockIndex();
//        instance.getOpenFileList().getOpenFile(4).getWrite().getByteIndex();
        Button pp51=new Button();
         pp51.getStyleClass().add("buttonfir");
        String ss51="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss51=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getPath());
        }
        pp51.setText(ss51);
          //----------
        Button pp52=new Button();
        String ss52="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss52=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getAttribute());
        }
        pp52.setText(ss52);
          //----------
        Button pp53=new Button();
        String ss53="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss53=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getBlockIndex());
        }
        pp53.setText(ss53);
          //----------
        Button pp54=new Button();
        String ss54="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss54=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getLength());
        }
        pp54.setText(ss54);
          //----------
        Button pp55=new Button();
        String ss55="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss55=String.valueOf(  instance.getOpenFileList().getOpenFile(4).isIsOpenInWrite());
        }
        pp55.setText(ss55);
          //----------
        Button pp56=new Button();
        String ss56="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss56=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getRead().getBlockIndex());
        }
        pp56.setText(ss56);
          //----------
        Button pp57=new Button();
        String ss57="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss57=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getRead().getByteIndex());
        }
        pp57.setText(ss57);
          //----------
        Button pp58=new Button();
        String ss58="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss58=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getWrite().getBlockIndex());
        }
        pp58.setText(ss58);
          //----------
        Button pp59=new Button();
        String ss59="";
        if(instance.getOpenFileList().getOpenFile(4)!=null)
        {
            ss59=String.valueOf(  instance.getOpenFileList().getOpenFile(4).getWrite().getByteIndex());
        }
        pp59.setText(ss59);
          //----------
        //
        hbTotol5Top.getChildren().addAll(bb51,bb52,bb53,bb54,bb55,bb56,bb57,bb58,bb59);
        hbTotal5Down.getChildren().addAll(pp51,pp52,pp53,pp54,pp55,pp56,pp57,pp58,pp59);
        vbTotol5.getChildren().addAll(hbTotol5Top,hbTotal5Down);
        ScrollPane scrollPane=new ScrollPane();
         // scrollPane.getStyleClass().add("diskCss");
       
       
        
        
        
        vbTotol.getChildren().addAll(vbTotol1,vbTotol2,vbTotol3,vbTotol4,vbTotol5);
        vbTotol.getStyleClass().add("vbTotal");
         scrollPane.setContent(vbTotol);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //scrollPane.setMinHeight(300);//
        scrollPane.setPadding(new Insets(20, 0, 0, 0));
       hbTitle.getStyleClass().add("hbTitle");
        scrollPane.getStyleClass().add("scrollPane");
        
        VBox vbrelTotol=new VBox();
        vbrelTotol.getChildren().addAll(hbTitle,scrollPane);
        Scene scene=new Scene(vbrelTotol,975,550);
        
        scene.getStylesheets().add("openfile.css");
        stage.setScene(scene);
        stage.setTitle("已打开文件列表");
        stage.show();
        stage.setResizable(false);
        
    }

    
    
    
//        public void showCombo(int index)
//    {
//        if(index==0)
//        {
//            Stage stage=new Stage();
//            HBox hb=new HBox();
//            
//            Scene scene=new Scene(hb);
//            stage.setTitle("0");
//            stage.setScene(scene);
//            stage.show();
//        }
//        if(index==1)
//        {
//            Stage stage=new Stage();
//            HBox hb=new HBox();
//            
//            Scene scene=new Scene(hb);
//            stage.setTitle("1");
//            stage.setScene(scene);
//            stage.show();
//        }
//        
//        if(index==2)
//        {
//            Stage stage=new Stage();
//            HBox hb=new HBox();
//            
//            Scene scene=new Scene(hb);
//            stage.setTitle("2");
//            stage.setScene(scene);
//            stage.show();
//        }
//        
//    }
}
//