package main;

import exceptions.File.FileAlreadyExistException;
import exceptions.File.FileAlreadyOpenedException;
import exceptions.File.FileNotExistException;
import exceptions.*;
import exceptions.File.FileOpenedInWriteException;
import exceptions.File.FileReadOnlyException;
import structures.Disk;
import structures.FAT;
import structures.openFileList.OpenFileList;
import structures.Record;
import exceptions.Open.OpenFileListFullException;
import exceptions.PathNotExistException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 所有实例都在这里面
 *
 * @author ZoeC
 */
public class Instances {

    private static Disk disk = new Disk();
    private static FAT fat = new FAT();
    private static OpenFileList openFileList = new OpenFileList();

    public Instances() {
        disk.setBlock(0, fat.getSingleFAT(0));
        disk.setBlock(1, fat.getSingleFAT(1));
    }

    /**
     * 创建文件 一定是可写的
     *
     * @param path 这个是绝对名！！！
     * @param type
     * @param attribute
     * @throws Exception
     */
    public void createFile(String path, String type, byte attribute) throws Exception {
//        if (openFileList.getLength() == openFileList.getMAX_AMOUNT()) {
//            throw new OpenFileListFullException();
//        }

        isPathLegal(path+"."+type);
        int index = 2;
        if (!getSubPath(path).equals(path)) {
            Record subRecord = disk.getRecord(2, getSubPath(path));
            index = Integer.valueOf(subRecord.getBlockIndex());
        }
        int location = disk.getEmptyLocation(index);
        System.out.println("REcord放在第几块" + index);
        System.out.println("中的第" + location + "个位置。");
        int blockIndex = fat.getEmptyIndex();
        Record record = new Record(path.substring(path.lastIndexOf("/")+1,path.length()).toCharArray(), type.toCharArray(), attribute, (byte) blockIndex);
        disk.signRecord(index, location, record);
        System.out.println("创建文件成功");
        disk.getBlock(blockIndex)[0] = '#';
        fat.setFAT(blockIndex, (byte) -1);

        //openFileList.signOpenFileList(path+"."+type, record, true);
    }

    /**
     * 创建目录
     *
     * @param path 这个是绝对名！！！
     * @param attribute
     * @throws Exception
     */
    public void createFolder(String path, byte attribute) throws Exception {//创建的一定是可写的
        isPathLegal(path);
        int index = 2;
        if (!getSubPath(path).equals(path)) {
            Record subRecord = disk.getRecord(2, getSubPath(path));
            index = Integer.valueOf(subRecord.getBlockIndex());
        }
        int location = disk.getEmptyLocation(index);
        System.out.println("REcord放在第几块" + index);
        System.out.println(location);
        int blockIndex = fat.getEmptyIndex();
        Record record = new Record(getName(path).toCharArray(), attribute, (byte) blockIndex);
        disk.signRecord(index, location, record);
        disk.setEmptyFolder(blockIndex);
        System.out.println("创建成功");
        fat.setFAT(blockIndex, (byte) -1);
    }

    /**
     * 删除文件
     *
     * @param path
     * @throws Exception
     */
    public void delFile(String path) throws Exception {
        if (!isFileRepeat(path)) {
            throw new FileNotExistException();
        }
        int[] location = disk.getRecordLocation(2, path);
        if (openFileList.isExistInFileList(path)) {
            throw new FileAlreadyOpenedException();
        }
        Record record = disk.getRecord(2, path);
        int index = record.getBlockIndex();
        int temp;
        while ((temp =fat.getSingleFATData(index) )!= -1) {
            fat.setFAT(index, (byte) 0);
            index=temp;
        }
        fat.setFAT(index, (byte) 0);
        disk.getBlock(location[0])[location[1]]='$';
        System.out.println("删除文件成功");
    }

    /**
     * 删除文件夹(需增加提醒)
     *
     * @param path
     * @throws Exception
     */
    @Deprecated
    public void delFolder(String path) throws Exception {
        if (!isFileRepeat(path)) {
            throw new FileNotExistException();
        }
        int[] location = disk.getRecordLocation(2, path);
        Record record = disk.getRecord(2, path);
        if(record.getAttribute()<8)throw new Exception("没有该文件夹");
        int index = record.getBlockIndex();
        for (int i = 0; i < 8; i++) {
            Record tempRecord = disk.loadRecord(index, i);
            char[] name = tempRecord.getName();
            if (name[0] != '$') {
                if (tempRecord.isFolder()) {
                    delFolder(path + "/" + getString(name));
                } else {
                    delFile(path + "/" + getString(name) + "." + getString(tempRecord.getType()));
                }
            }
        }
        fat.setFAT(index, (byte) 0);
        disk.getBlock(location[0])[location[1]]='$';
        System.out.println("删除文件夹成功");
    }

    /**
     * 从绝对路径中获取文件名
     *
     * @param path
     * @return
     */
    private String getName(String path) {
        int index = String.valueOf(path).lastIndexOf("/");
        if (index == -1) {
            return path;
        }
        return String.valueOf(path).substring(index + 1);
    }

    /**
     * 展示文件夹
     *
     * @param path
     * @throws Exception
     */
    public ArrayList<Record> showFolder(String path) throws Exception {
        ArrayList<Record> records = new ArrayList<>();
        Record gotrecord=disk.getRecord(2, path);
        if(gotrecord.getAttribute()<8)throw new Exception("不是文件夹");
        int index = gotrecord.getBlockIndex();
        for (int i = 0; i < 8; i++) {
            Record record = disk.loadRecord(index, i);
            if (record.getName()[0] != '$') {
                records.add(record);
            }
        }
        return records;
    }

    /**
     * 检查路径是否存在、是否有重名文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    public boolean isPathLegal(String path) throws Exception {
        String subPath = getSubPath(path);
        if (!subPath.equals(path) && !isPathExist(subPath)) { //根目录没有父目录，所以不用判断是否存在父目录。
            throw new PathNotExistException();
        }
        if (isFileRepeat(path)) {
            throw new FileAlreadyExistException();
        }
        return true;
    }

    /**
     * 获取子路径
     *
     * @param path
     * @return
     */
    private String getSubPath(String path) {
        int index = path.lastIndexOf("/");
        if (index == -1) {
            return path;
        }
        return path.substring(0, index);
    }

    /**
     * 检查文件是否重复
     *
     * @param path
     * @return
     */
    private boolean isFileRepeat(String path) {
        try {
            disk.getRecordLocation(2, path);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * 检查目录是否存在
     *
     * @param path
     * @return
     * @throws exceptions.PathNotExistException
     */
    private boolean isPathExist(String path) {
        try {
            disk.getRecordLocation(2, path);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * 修改文件属性
     * @param path
     * @param attribute
     * @throws Exception 
     */
    public void changeAttribute(String path, byte attribute) throws Exception {
        if (openFileList.isExistInFileList(path)) {
            throw new FileAlreadyOpenedException();
        }
        Record record = disk.getRecord(2, path);
        record.setAttribute(attribute);
        int[] location = disk.getRecordLocation(2, path);
        disk.signRecord(location[0], location[1], record);
    }

    /**
     * 显示文件夹内容（非读取！）
     *
     * @param path
     * @return 文件中的字符串
     * @throws Exception
     */
    public String showFile(String path) throws Exception {
        StringBuilder sb = new StringBuilder();
        Record record = disk.getRecord(2, path);
        if (openFileList.isExistInFileList(path)) {
            throw new FileAlreadyOpenedException();
        }
        int index = record.getBlockIndex();
        while (((int) fat.getSingleFATData(index))!= -1) {
            for(int i=0;i<64;i++){
                sb.append((char)disk.getBlock(index)[i]);
            }
            index = (int) fat.getSingleFATData(index);
        }
        for(int i=0;i<64;i++){
            char temp=(char)disk.getBlock(index)[i];
            if(temp=='#')break;
                sb.append(temp);
        }
        
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 打开文件。 若已经打开则不管，否则登记
     *
     * @param path
     * @param isWrite
     * @throws Exception
     */
    private void openFile(String path, boolean isWrite) throws Exception {
        Record record = disk.getRecord(2, path);
        if (record.isReadOnly()) {
            if (isWrite) {
                throw new FileReadOnlyException();
            }
        }
        if (!openFileList.isExistInFileList(path)) {
            openFileList.signOpenFileList(path, record, isWrite);
        }
    }

    /**
     * 打开文件。 若已经打开则不管，否则登记
     *
     * @param path
     * @param isWrite
     * @throws Exception
     */
    public void closeFile(String path) throws Exception {
        if (!openFileList.isExistInFileList(path)) {
            throw new Exception("文件未打开");
        }
        openFileList.removeFromFileList(path);
    }

    /**
     * 读文件
     *
     * @param path
     * @param lengthForRead
     * @return
     * @throws Exception
     */
    public String readFile(String path, int lengthForRead) throws Exception {
        openFile(path, false);
        openFileList.getOpenFile(path);
        int index = openFileList.getOpenFile(path).getRead().getBlockIndex();
        int byteIndex = openFileList.getOpenFile(path).getRead().getByteIndex();
        int indexInBlock = byteIndex;
        int tempIndex = index;
        boolean flag = false;
        char last = 0;
        StringBuilder sb = new StringBuilder("");
        while ((int) fat.getSingleFATData(index) != -1) {
            while (indexInBlock < 64) {
                if (lengthForRead == 0) {
                    flag = true;
                    break;
                }
                last = (char) disk.getBlock(index)[indexInBlock % 64];
                sb.append(last);
                lengthForRead--;
                indexInBlock++;
            }
            tempIndex = index;
            if (flag) {
                break;
            }
            indexInBlock = 0;
            index = (int) fat.getSingleFATData(index);
        }

        while (indexInBlock < 64) {
            if (lengthForRead == 0) {
                break;
            }
            last = (char) disk.getBlock(index)[indexInBlock % 64];
            if (last == '#') {
                break;
            }
            sb.append(last);
            lengthForRead--;
            indexInBlock++;
        }
        openFileList.getOpenFile(path).getRead().setBlockIndex(tempIndex);
        openFileList.getOpenFile(path).getRead().setByteIndex(indexInBlock);
        return sb.toString();
    }

    /**
     * 写文件
     *
     * @param path
     * @param buffer
     * @param lengthForWrite
     * @throws Exception
     */
    public void writeFile(String path, String buffer, int lengthForWrite) throws Exception {//存放准备写入磁盘信息的缓冲
        if (!openFileList.isExistInFileList(path)) {
            openFile(path, true);
        }else if(!openFileList.getOpenFile(path).isIsOpenInWrite()){
            throw new Exception("文件不是以写方式打开");
        }
        buffer = buffer + "#";
        lengthForWrite = lengthForWrite + 1;

        openFileList.getOpenFile(path);
        int index = openFileList.getOpenFile(path).getWrite().getBlockIndex();//文件初始指针
        int byteIndex = openFileList.getOpenFile(path).getWrite().getByteIndex();
        System.out.println("初始指针块号："+index+"块内地址："+byteIndex);
        int tempIndex = index;//文件最后一块
        int emptyIndex = index;
        int temp;
        boolean flag = false;
        while ((temp = (int) fat.getSingleFATData(tempIndex)) != -1) {
            tempIndex = temp;
            fat.setFAT(temp, (byte) 0);
        }
        if (index != tempIndex) {
            fat.setFAT(tempIndex, (byte) 0);
        }
        fat.setFAT(index, (byte) -1);
        int i = 0;

        while (lengthForWrite > 0) {
            while (lengthForWrite > 0 && byteIndex < 64) {
                disk.getBlock(index)[byteIndex % 64] = (byte) buffer.charAt(i);
                i++;
                byteIndex++;
                if (--lengthForWrite == 0) {
                    flag = true;
                }
            }
            if (!flag) {
                byteIndex = 0;
                emptyIndex = fat.getEmptyIndex();
                fat.setFAT(index, (byte) emptyIndex);
                fat.setFAT(emptyIndex, (byte) -1);
                index = emptyIndex;
            }
        }
        System.out.println("指针块号："+index+"块内地址："+(byteIndex-1));
        openFileList.getOpenFile(path).getWrite().setBlockIndex(index);
        openFileList.getOpenFile(path).getWrite().setByteIndex(byteIndex - 1);

        int numOfBlocks = 1;
        int fatData = 0;
        Record record = disk.getRecord(2, path);
        int blockIndex = record.getBlockIndex();
        while ((fatData = fat.getSingleFATData(blockIndex)) != -1) {
            numOfBlocks++;
            blockIndex = fatData;
        }
//        record.setLength((byte) numOfBlocks);
        int[] location = disk.getRecordLocation(2, path);
        disk.getBlock(location[0])[location[1]+7]=(byte)numOfBlocks;
        openFileList.getOpenFile(path).setLength(numOfBlocks);
    }

    public FAT getFAT() {
        return fat;
    }

    public Disk getDisk() {
        return disk;
    }

    public OpenFileList getOpenFileList() {
        return openFileList;
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
