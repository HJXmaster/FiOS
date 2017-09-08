/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import main.Instances;

import tools.JButtons;

/**
 *
 * @author ZoeC
 */
public class GUI  {
    
    private GridPane paneBtFile = new GridPane();
    private Button btShowFAT = new Button("展示FAT");
    private Button btShowDisk = new Button("展示磁盘存储表");
    private Button btShowOpenFileList = new Button("展示已打开文件列表");
    private Button btCreateFile = new Button("创建文件s");
    private Button btReadFile = new Button("读文件");
    private Button btWriteFile = new Button("写文件");
    private TextField tfCreateFile = new TextField("输入绝对路径");
     private Instances instances = new Instances();
    
    public GUI() {
        btShowFAT.setOnAction((ActionEvent exent) -> {
            showFAT();
        });
        btShowDisk.setOnAction((ActionEvent exent) -> {
            showDisk();
        });
        btShowOpenFileList.setOnAction((ActionEvent exent) -> {
            showOpenFileList();
        });
        btCreateFile.setOnAction((ActionEvent event) -> {
            getCreateFileInfo();
        });
        btReadFile.setOnAction((ActionEvent event) -> {
        });
        btWriteFile.setOnAction((ActionEvent event) -> {
        });
        paneBtFile.addColumn(0, tfCreateFile, btCreateFile, btReadFile, btWriteFile);
        paneBtFile.setGridLinesVisible(false);
        

         
        
    }
    
    public GridPane getPaneButton() {
        return paneBtFile;
    }
    
    public void createFile(char[] path, char[] type, byte attribute) {
        try {
            instances.createFile(String.valueOf(path), String.valueOf(type),attribute);
        } catch (Exception ex) {
            int option = JOptionPane.showConfirmDialog(null,
                    "创建文件失败", "Err", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE, null);
            switch (option) {
                case JOptionPane.OK_OPTION:
            }
        }
    }
    
    public void getCreateFileInfo() {
        JFrame inputNewFileInfo=new JFrame();
        char[] path = new char[3];////////////
        char[] type = new char[2];////////////
        byte attribute = 0;//////////////
        inputNewFileInfo.setTitle("请输入新文件信息");
        inputNewFileInfo.setVisible(true);
        inputNewFileInfo.setBounds(490, 270, 350, 150);  
        
        inputNewFileInfo.setLayout(null);
        JLabel labelForName = new JLabel("文件名：");
        labelForName.setBounds(15, 25, 100, 20);
        JTextField newFileNameArea = new JTextField();
        newFileNameArea.setBounds(95, 25, 150, 25);
        
        JLabel labelForType = new JLabel("文件类型名：");
        labelForType.setBounds(15, 55, 100, 20);
        JTextField newFileTypeArea = new JTextField();
        newFileTypeArea.setBounds(95, 55, 150, 25);
        
        JLabel labelForAtt = new JLabel("文件属性：");
        labelForAtt.setBounds(15, 95, 100, 20); 

        JRadioButton attForRead = new JRadioButton("只读");// 创建单选按钮
        JRadioButton attForSys = new JRadioButton("系统");// 创建单选按钮         
        JRadioButton attForOrd = new JRadioButton("普通");// 创建单选按钮
        JRadioButton attForDir = new JRadioButton("目录");// 创建单选按钮
        attForRead.setSelected(true);
        
        ButtonGroup groupForAtt = new ButtonGroup();// 创建单选按钮组
        groupForAtt.add(attForRead);// 
        groupForAtt.add(attForSys);// 
        groupForAtt.add(attForOrd);// 
        groupForAtt.add(attForDir);// 
        
        attForRead.setBounds(90, 95, 60, 20);
        attForSys.setBounds(150, 95, 60, 20);
        attForOrd.setBounds(210, 95, 60, 20);
        attForDir.setBounds(270, 95, 60, 20);
        
         
        
        
        
        
        JButton okForCreate = new JButton("ok");
        okForCreate.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                    int type=0;
                if(newFileNameArea.getText().equals("")||!GUI.isNameLegal(newFileNameArea.getText()))
                    System.out.println("请输入文件的绝对路径！");
                else{
                    
                    System.out.println("文件绝对路径："+newFileNameArea.getText());
                if(attForRead.isSelected())
                    type=type+1;
                if(attForSys.isSelected())
                    type=type+2;
                if(attForOrd.isSelected())
                    type=type+4;
                if(attForDir.isSelected())
                    type=type+8;
                    inputNewFileInfo.setVisible(false);
                    //instances.createFile(newFileNameArea.getText().toCharArray(), , (byte)type);////////////////////////////////////////////
                }
            }
        });
        okForCreate.setBounds(95, 120, 60, 25);
        JButton cancelForCreate = new JButton("取消");
        cancelForCreate.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                inputNewFileInfo.setVisible(false);
            }
        });
        cancelForCreate.setBounds(175, 120, 60, 25);
        
        inputNewFileInfo.add(labelForName);
        inputNewFileInfo.add(labelForType);
        inputNewFileInfo.add(labelForAtt);
        inputNewFileInfo.add(newFileNameArea);
        inputNewFileInfo.add(newFileTypeArea);
        
        inputNewFileInfo.add(attForRead);
        inputNewFileInfo.add(attForSys);
        inputNewFileInfo.add(attForOrd);
        inputNewFileInfo.add(attForDir);    
        
        inputNewFileInfo.add(okForCreate);
        inputNewFileInfo.add(cancelForCreate);
        //createFile(path, type, attribute);
    }
    
    public void showFAT() {
        instances.getFAT().getSingleFAT(0);
        instances.getFAT().getSingleFAT(1);
    }
    public void showDisk() {
        instances.getDisk().getBlocks();
    }
    public void showOpenFileList() {
        instances.getOpenFileList().getOpenFile(0).getRead().getBlockIndex();
        instances.getOpenFileList().getOpenFile(1).getWrite().getByteIndex();
    }

    public static boolean isNameLegal(String name){
        String temp=String.valueOf(name);
        boolean legal=true;
        if(temp.contains("$")){
            legal=false;
        }
        if(temp.contains("\\.")){
            legal=false;
        }
        if(temp.contains("/")){
            legal=false;
        }
        return legal;
    }
    
}
