/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.openFileList;

/**
 *
 * @author ZoeC
 */
public class Pointer {

    //读、写指针的结构
    private int blockIndex; //块号
    private int byteIndex; //块内第几个字节

    public Pointer(byte blockIndex, int byteIndex) {
        this.blockIndex = (int) blockIndex;
        this.byteIndex = byteIndex;
    }

    /**
     * @return the blockIndex
     */
    public int getBlockIndex() {
        return blockIndex;
    }

    /**
     * @return the byteIndex
     */
    public int getByteIndex() {
        return byteIndex;
    }

    /**
     * @param blockIndex the blockIndex to set
     */
    public void setBlockIndex(int blockIndex) {
        this.blockIndex = blockIndex;
    }

    /**
     * @param byteIndex the byteIndex to set
     */
    public void setByteIndex(int byteIndex) {
        this.byteIndex = byteIndex;
    }

}
