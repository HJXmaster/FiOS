package structures;

import exceptions.File.FileNotExistException;
import exceptions.FolderFullException;
import exceptions.Open.OpenFileListFullException;
import exceptions.PathNotExistException;

/**
 * 存储硬盘 共128个盘块，每块64byte
 *
 * @author ZoeC
 */
public class Disk {

    private static byte[][] blocks = new byte[128][];

    public Disk() {
        for (int i = 0; i < 128; i++) {
            blocks[i] = new byte[64];
        }
        setEmptyFolder(2);
    }

    public void setEmptyFolder(int index) {
        byte[] block = new byte[64];
        for (int i = 0; i < 64; i++) {
            block[i] = 0;
        }
        for (int i = 0; i < 8; i++) {
            block[i * 8] = '$';
        }
        setBlock(index, block);
    }

    /**
     * 存储一个完整的块
     *
     * @param index 位置
     * @param block 一个完整的块
     */
    public void setBlock(int index, byte[] block) {
        blocks[index] = block;
    }

    /**
     * 返回整个磁盘数组
     *
     * @return the blocks
     */
    public byte[][] getBlocks() {
        return blocks;
    }

    /**
     * 返回指定的盘块
     *
     * @return the blocks
     */
    public byte[] getBlock(int index) {
        return blocks[index];
    }

    /**
     * 将某记录存入磁盘
     *
     * @param whichBlock 第几个盘快
     * @param whichIndex 这是这个盘快的第几个记录
     * @param record
     */
    public void signRecord(int whichBlock, int whichIndex, Record record) {
        char[] name = record.getName();
        char[] type = record.getType();
        //修改name长度可能不为3。
        System.out.println("文件名" + String.valueOf(name));
        blocks[whichBlock][8 * whichIndex] = (byte) name[0];
        blocks[whichBlock][8 * whichIndex + 1] = (byte) name[1];
        blocks[whichBlock][8 * whichIndex + 2] = (byte) name[2];
        blocks[whichBlock][8 * whichIndex + 3] = (byte) type[0];
        blocks[whichBlock][8 * whichIndex + 4] = (byte) type[1];
        blocks[whichBlock][8 * whichIndex + 5] = record.getAttribute();
        blocks[whichBlock][8 * whichIndex + 6] = record.getBlockIndex();
        blocks[whichBlock][8 * whichIndex + 7] = record.getLength();
    }

    /**
     * 从磁盘读出记录
     *
     * @param whichBlock 第几个盘快
     * @param location 这是这个盘快的第几个记录
     * @return
     */
    public Record loadRecord(int index, int location) {
        char[] name = new char[3];
        char[] type = new char[2];
        name[0] = (char) blocks[index][8 * location];
        name[1] = (char) blocks[index][8 * location + 1];
        name[2] = (char) blocks[index][8 * location + 2];
        type[0] = (char) blocks[index][8 * location + 3];
        type[1] = (char) blocks[index][8 * location + 4];
        return new Record(name, type, blocks[index][8 * location + 5], blocks[index][8 * location + 6], blocks[index][8 * location + 7]);
    }

    public int getEmptyLocation(int index) throws FolderFullException {
        System.out.println("查找第几个：" + index);
        byte[] block = getBlock(index);
        for (int location = 0; location < 8; location++) {
            char a = (char) block[8 * location];
            if (a == '$') {
                return location;
            }
        }
        throw new FolderFullException();
    }

    /**
     *
     * @param blockIndex 从哪个块开始找，最开始是2号
     * @param dir 绝对路径
     * @return
     * @throws PathNotExistException
     * @throws OpenFileListFullException
     */
    public Record getRecord(int blockIndex, String dir)
            throws PathNotExistException, OpenFileListFullException, FileNotExistException {
        String[] dirs = dir.split("/", 2);
        Record record = null;
        if (dirs.length == 2) {     //存在下级子目录
            try {
                Record subrecord = seek(blockIndex, dirs[0]);
                record = getRecord(subrecord.getBlockIndex(), dirs[1]);//查找下级子目录
            } catch (Exception ex) {
                throw new PathNotExistException("不存在的路径！");
            }
        } else {        //不存在下一级子目录
            String[] fileInformation = dirs[0].split("\\.", 2);//分割为文件名和文件类型名
            if (fileInformation.length == 1) {    //目录
                try {
                    record = seek(blockIndex, fileInformation[0]);
                } catch (Exception ex) {
                    throw new PathNotExistException("不存在的路径！");
                }
            } else {                             //文件
                int i = 0;
                for (; i < 8; i++) {
                    if (fileInformation[0].equals(getString(loadRecord(blockIndex, i).getName()))
                            && fileInformation[1].equals(getString(loadRecord(blockIndex, i).getType()))) {
                        record = loadRecord(blockIndex, i);
                        break;
                    }
                }
                if (i >= 8) {
                    throw new FileNotExistException("不存在的文件！");
                }
            }
        }
        return record;
    }

    /**
     *
     * @param blockIndex
     * @param dir
     * @return 返回哪个快的哪个部分
     * @throws PathNotExistException
     * @throws OpenFileListFullException
     */
    public int[] getRecordLocation(int blockIndex, String dir)
            throws PathNotExistException, FileNotExistException {
        String[] dirs = dir.split("/", 2);
        int[] location = new int[2];
        Record record = null;
        if (dirs.length == 2) {     //存在下级子目录
            try {
                record = seek(blockIndex, dirs[0]);
                location = getRecordLocation(record.getBlockIndex(), dirs[1]);//查找下级子目录
            } catch (Exception ex) {
                throw new PathNotExistException("不存在的路径！");
            }
        } else {        //不存在下一级子目录
            String[] fileInformation = dirs[0].split("\\.", 2);//分割为文件名和文件类型名
            if (fileInformation.length == 1) {    //目录
                int i = 0;
                for (; i < 8; i++) {
                    if (fileInformation[0].equals(getString(loadRecord(blockIndex, i).getName()))) {
                        location[0] = blockIndex;
                        location[1] = i * 8;
                        break;
                    }
                }
                if (i >= 8) {
                    throw new PathNotExistException("不存在的路径！");
                }
            } else {                             //文件
                int i = 0;
                for (; i < 8; i++) {
                    if (fileInformation[0].equals(getString(loadRecord(blockIndex, i).getName()))
                            && fileInformation[1].equals(getString(loadRecord(blockIndex, i).getType()))) {
                        location[0] = blockIndex;
                        location[1] = i * 8;
                        break;
                    }
                }
                if (i >= 8) {
                    throw new FileNotExistException("不存在的文件！");
                }
            }
        }
        return location;
    }

    /**
     *
     * @param blockIndex
     * @param dir
     * @return
     * @throws PathNotExistException
     */
    private Record seek(int blockIndex, String dir) throws PathNotExistException {
        Record record = null;
        int i = 0;
        for (; i < 8; i++) {
            if (dir.equals(getString(loadRecord(blockIndex, i).getName()))) {
                record = loadRecord(blockIndex, i);
                break;
            }
        }
        if (i >= 8) {
            throw new PathNotExistException("不存在的路径！");
        }
        return record;
    }

    /**
     * 去除$
     *
     * @param string
     * @return
     */
    private String getString(char[] string) {
        String newString = "";
        for (int i = 0; i < string.length; i++) {
            if (string[i] != 0 && string[0] != '$') {
                newString = newString + string[i];
            }
        }
        return newString;
    }
}
