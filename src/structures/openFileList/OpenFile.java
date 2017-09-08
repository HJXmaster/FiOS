/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.openFileList;

import structures.Record;

/**
 *
 * @author ZoeC
 */
public class OpenFile {

    private char[] path = new char[20];
    private char attribute;
    private int blockIndex; //文件起始盘块号
    private int length; //文件长度，文件占用的字节数
    private boolean isOpenInWrite;
    private Pointer read;
    private Pointer write;

    OpenFile(char[] path, Record record, boolean isOpenInWrite) {
        this.path = path;
        attribute = (char) record.getAttribute();
        blockIndex = (int) record.getBlockIndex();
        length = (int) record.getLength();
        this.isOpenInWrite = isOpenInWrite;
        read = new Pointer(record.getBlockIndex(), 0);
        write = new Pointer(record.getBlockIndex(), 0);
    }

    /**
     * @return the path
     */
    public char[] getPath() {
        return path;
    }

    /**
     * @return the attribute
     */
    public char getAttribute() {
        return attribute;
    }
/**
     * @return the attribute
     */
    public String  getAttributeMsg() {
        if(attribute==1)return "只读文件";
        if(attribute==3)return "只读系统文件";
        if(attribute==4)return "普通文件";
        if(attribute==6)return "普通系统文件";
        return "无法识别的文件";
    }
    /**
     * @return the blockIndex
     */
    public int getBlockIndex() {
        return blockIndex;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @return the isOpenInWrite
     */
    public boolean isIsOpenInWrite() {
        return isOpenInWrite;
    }
    
    /**
     * @return the isOpenInWrite
     */
    public String isIsOpenInWriteMsg() {
        if(isOpenInWrite) return "写方式";
        else return "读方式";
        
    }

    /**
     * @return the read
     */
    public Pointer getRead() {
        return read;
    }

    /**
     * @return the write
     */
    public Pointer getWrite() {
        return write;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @param isOpenInWrite the isOpenInWrite to set
     */
    public void setIsOpenInWrite(boolean isOpenInWrite) {
        this.isOpenInWrite = isOpenInWrite;
    }

}
