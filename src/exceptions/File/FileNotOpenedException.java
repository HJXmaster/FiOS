/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.File;

/**
 *
 * @author 黄佳鑫
 */
public class FileNotOpenedException extends Exception {

    public FileNotOpenedException(String message) {
        super("err:文件未打开。" + message);
    }

    public FileNotOpenedException() {
        super("err:文件未打开。");
    }
}
