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
public class PathNotExistException extends Exception {

    public PathNotExistException(String message) {
        super("err:路径不存在。" + message);
    }

    public PathNotExistException() {
        super("err:路径不存在。");
    }
}
