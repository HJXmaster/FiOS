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
public class FileOpenedInWriteException extends Exception {

    public FileOpenedInWriteException(String message) {
        super("err:文件已以写形式打开。" + message);
    }

    public FileOpenedInWriteException() {
        super("err:文件已以写形式打开。");
    }
}
