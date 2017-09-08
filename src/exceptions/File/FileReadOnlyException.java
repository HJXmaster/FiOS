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
public class FileReadOnlyException extends Exception {

    public FileReadOnlyException(String message) {
        super("err:该文件只读。" + message);
    }

    public FileReadOnlyException() {
        super("err:该文件只读。");
    }
}
