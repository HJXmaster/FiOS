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
public class FileNotExistException extends Exception {

    public FileNotExistException(String message) {
        super("err:文件不存在。" + message);
    }

    public FileNotExistException() {
        super("err:文件不存在。");
    }
}
