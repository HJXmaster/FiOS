package structures.openFileList;

import exceptions.File.FileNotOpenedException;
import exceptions.Open.OpenFileListFullException;
import structures.Record;

/**
 * 打开文件列表
 *
 * @author ZoeC
 */
public class OpenFileList {

    private final int MAX_AMOUNT = 5;
    private OpenFile[] fileList = new OpenFile[getMAX_AMOUNT()];
    private int length = 0;//已打开文件登记表中登记的文件数量

    /**
     * 打开文件时添加记录
     *
     * @param path
     * @param record
     * @param isOpenInWrite
     * @throws Exception
     */
    public void signOpenFileList(String path, Record record, boolean isOpenInWrite) throws Exception {
        if (getLength() == getMAX_AMOUNT()) {
            throw new OpenFileListFullException();
        }
        fileList[getLength()] = new OpenFile(path.toCharArray(), record, isOpenInWrite);
        length++;
    }

    /**
     * 是否已经打开文件
     *
     * @param path
     * @return
     */
    public boolean isExistInFileList(String path) {
        for (OpenFile openFile : fileList) {
            if (openFile != null && String.valueOf(openFile.getPath()).compareTo(path) == 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 是否已经打开文件
     *
     * @param path
     * @return
     */
    public void removeFromFileList(String path) {
        for (int i=0;i<5;i++) {
            if (fileList[i] != null && String.valueOf(fileList[i].getPath()).compareTo(path) == 0) {
                fileList[i]=null;
                length--;
            }
        }
        
    }

    /**
     * 是否已经以写形式打开
     *
     * @param path
     * @return
     */
    public boolean isOpenInWrite(String path) throws FileNotOpenedException {
        if (!isExistInFileList(path)) {
            throw new FileNotOpenedException();
        }
        for (OpenFile openFile : fileList) {
            if (openFile != null && String.valueOf(openFile.getPath()).compareTo(path) == 0) {
                if (openFile.isIsOpenInWrite()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the MAX_AMOUNT
     */
    public int getMAX_AMOUNT() {
        return MAX_AMOUNT;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * 获取全部表格
     *
     * @return the fileList
     */
    public OpenFile[] getFileList() {
        return fileList;
    }

    /**
     * 通过下标获取指定表格
     *
     * @param index
     * @return
     */
    public OpenFile getOpenFile(int index) {
        return fileList[index];
    }

    /**
     * 通过路径获取指定表格
     *
     * @param index
     * @return
     */
    public OpenFile getOpenFile(String path) throws Exception {
        for (OpenFile openFile : fileList) {
            if (openFile != null && String.valueOf(openFile.getPath()).compareTo(path) == 0) {
                return openFile;
            }
        }
        throw new Exception("err:无法得到打开文件登记项");
    }
}
