package com.ibn.codegenerator.util;

import java.io.*;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.util
 * @author： RenBin
 * @createTime：2020/8/5 10:58
 */
public class BeanUtils {
    /**
     * @description: 代码深度拷贝
     * @author：RenBin
     * @createTime：2020/8/5 10:58
     */
    public static Object cloneObjBySerialization(Serializable src) {
        Object dest = null;
        try {
            ByteArrayOutputStream bos = null;
            ObjectOutputStream oos = null;
            try {
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(src);
                oos.flush();
            } finally {
                oos.close();
            }
            byte[] bytes = bos.toByteArray();
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                dest = ois.readObject();
            } finally {
                ois.close();
            }
        } catch (Exception e) {
            // 克隆失败
            e.printStackTrace();
        }
        return dest;
    }
}
