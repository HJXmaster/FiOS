package structures;

/**
 * FAT表 分成两部分，每个64项
 *
 * @author ZoeC
 */
public class FAT {

    private byte[][] fat = new byte[2][];
    private int index;

    public FAT() {
        fat[0] = new byte[64];
        fat[1] = new byte[64];
        fat[0][0] = -1;
        fat[0][1] = -1;
        fat[0][2] = -1;
        for (int i = 3; i < fat[0].length; i++) {
            fat[0][i] = 0;
        }
        for (int i = 0; i < fat[1].length; i++) {
            fat[1][i] = 0;
        }
        //文件分配表的第23 项和第49 项写入“254”表示该盘块损坏不能使用。
        fat[0][22] = (byte) 254;
        fat[0][48] = (byte) 254;
        System.out.println("FAT initialized");
    }

    /**
     * 设置某块的值
     *
     * @param index 哪一块
     * @param value 设定的值
     */
    public void setFAT(int index, byte value) {
        if (isFirstFAT(index)) {
            fat[0][this.index] = value;
        } else {
            fat[1][this.index] = value;
        }
    }

    /**
     *
     * @param 想要第一张还是第二张ＦＡＴ表
     * @return 返回第一张或第二张表
     */
    public byte[] getSingleFAT(int index) {
        return fat[index];
    }

    /**
     *
     * @param 想要第一张还是第二张ＦＡＴ表
     * @return 返回第一张或第二张表
     */
    public byte getSingleFATData(int index) {
        if (index > 63) {
            return fat[1][index - 63];
        }
        return fat[0][index];
    }

    private boolean isFirstFAT(int index) {
        if (index <= 63) {
            this.index = index;
            return true;
        } else {
            this.index = index - 64;
            return false;
        }
    }

    /**
     * 获取一个空的FAT位置
     *
     * @return
     * @throws Exception
     */
    public int getEmptyIndex() throws Exception {
        for (int i = 0; i < fat[0].length; i++) {
            if (fat[0][i] == 0) {
                return i;
            }
        }
        for (int i = 0; i < fat[1].length; i++) {
            if (fat[1][i] == 0) {
                setFAT(i + 64, (byte) -1);
                return i + 64;
            }
        }
        throw new Exception("err:FAT is full!");
    }

    /**
     * 显示FAT
     *
     * @deprecated
     */
    @Deprecated
    public void showFAT() {
        System.out.println("");
        System.out.println("---------------------------------------------");
        System.out.println("FAT:");
        for (int i = 0; i < 64; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println("");
        for (int i = 0; i < 64; i++) {
            System.out.printf("%4d", fat[0][i]);
        }
        System.out.println("");
        System.out.println("---------------------------------------------");
        for (int i = 64; i < 128; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println("");
        for (int i = 0; i < 64; i++) {
            System.out.printf("%4d", fat[1][i]);
        }
        System.out.println("");
        System.out.println("---------------------------------------------");
    }

}
