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
public class FileAlreadyOpenedException extends Exception {

    public FileAlreadyOpenedException(String message) {
        super("err:文件已打开。" + message);
    }

    public FileAlreadyOpenedException() {
        super("err:文件已打开。");
    }
}
