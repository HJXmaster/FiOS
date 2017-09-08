/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.Open;

/**
 *
 * @author 黄佳鑫
 */
public class OpenFileListFullException extends Exception {

    public OpenFileListFullException(String message) {
        super("err:已达到打开文件数量上限！" + message);
    }

    public OpenFileListFullException() {
        super("err:已达到打开文件数量上限！");
    }
}
