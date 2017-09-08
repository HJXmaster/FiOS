package tools;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Font;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author USER
 */
public class JButtons extends JButton {

    private Font font;
    private String str;
    private String fir="0";
    private String mov="0";
    private String cli="0";

    public JButtons() {
        init("0","0","0");
    }
    
    public JButtons(String fir,String mov,String cli) {
        super();
        init(fir,mov,cli);
    }
    void init(String fir,String mov,String cli){
        setMargin(new Insets(0, 0, 0, 0));//设置边距
        setContentAreaFilled(false);//不绘制按钮区域
        setBorderPainted(false);//不绘制边框
        setIcon(new ImageIcon(fir));//设置默认图片
        setRolloverIcon(new ImageIcon(mov));//设置鼠标经过图片
        setPressedIcon(new ImageIcon(cli));//设置鼠标按下图片
    }
}
