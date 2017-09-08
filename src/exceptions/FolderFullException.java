/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author 黄佳鑫
 */
public class FolderFullException extends Exception {

    public FolderFullException(String message) {
        super("err:该文件夹已满。" + message);
    }

    public FolderFullException() {
        super("err:该文件夹已满。");
    }
}
