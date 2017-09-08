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
public class FileAlreadyExistException extends Exception {

    public FileAlreadyExistException(String message) {
        super("err:已存在同名文件。" + message);
    }

    public FileAlreadyExistException() {
        super("err:已存在同名文件。");
    }
}
