/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author ZoeC
 */
public class Record {

    private char[] name = {0, 0, 0};
    private char[] type = {0, 0};
    private byte attribute = 0;
    private byte blockIndex = 0;
    private byte length = 0;//占用块数

    /**
     * 建立文件夹记录
     *
     * @param name
     * @param attribute
     * @param blockIndex
     */
    public Record(char[] name, byte attribute, byte blockIndex) {
        this.name = name;////////////////////////////////////////////////////////少了这一句
        this.attribute = attribute;
        this.blockIndex = blockIndex;
    }

    public Record() {
        this.name[0] = '$';
    }

    /**
     * 监理文件记录
     *
     * @param name
     * @param type
     * @param attribute
     * @param blockIndex
     */
    public Record(char[] name, char[] type, byte attribute, byte blockIndex) {
        this.name = name;
        this.type = type;
        this.attribute = attribute;
        this.blockIndex = blockIndex;
        this.length = 1;
    }

    public Record(char[] name, char[] type, byte attribute, byte blockIndex, byte length) {
        this.name = name;
        this.type = type;
        this.attribute = attribute;
        this.blockIndex = blockIndex;
        this.length = length;
    }

    /**
     * 该记录是否指向文件夹
     *
     * @return
     */
    public boolean isFolder() {
        if (getAttribute() > 7 && getAttribute() < 16) {
            return true;
        }
        return false;
    }

    /**
     * 是否为可读可写的普通文件
     *
     * @return
     */
    public boolean isGeneral() {
        if (getAttribute() == 4 || getAttribute() == 5 || getAttribute() == 6 || getAttribute() == 7 || getAttribute() == 12 || getAttribute() == 13 || getAttribute() == 14 || getAttribute() == 15) {
            return true;
        }
        return false;
    }

    public boolean isSystem() {
        if (getAttribute() == 2 || getAttribute() == 3 || getAttribute() == 6 || getAttribute() == 7 || getAttribute() == 10 || getAttribute() == 11 || getAttribute() == 14 || getAttribute() == 15) {
            return true;
        }
        return false;
    }

    public boolean isReadOnly() {
        if (getAttribute() % 2 != 0) {
            return true;
        }
        return false;
    }

    /**
     * @return the name
     */
    public char[] getName() {
        char[] newname = {0, 0, 0};
        for (int i = 0; i < this.name.length; i++) {
            newname[i] = name[i];
        }
        return newname;
    }

    /**
     * @return the type
     */
    public char[] getType() {
        char[] newtype = {0, 0};
        for (int i = 0; i < this.type.length; i++) {
            newtype[i] = type[i];
        }
        return newtype;
    }

    /**
     * @return the attribute
     */
    public byte getAttribute() {
        return attribute;
    }

    /**
     * @return the blockIndex
     */
    public byte getBlockIndex() {
        return blockIndex;
    }

    /**
     * @return the length
     */
    public byte getLength() {
        return length;
    }

    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(byte attribute) {
        this.attribute = attribute;
    }

    /**
     * @param length the length to set
     */
    public void setLength(byte length) {
        this.length = length;
    }
}
