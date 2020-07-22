package com.ibn.codegenerator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.util
 * @author： RenBin
 * @createTime：2020/7/22 9:44
 */
public class StringUtil {
    private static final Pattern UNDERSCORE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * @description: 下划线转驼峰，首字母大写
     * @author：RenBin
     * @createTime：2020/7/22 9:45
     */
    public static String underscoreToUpcamelCase(String underscore) {
        if (null == underscore) {
            return "";
        }
        underscore = underscore.toLowerCase();
        final StringBuffer sb = new StringBuffer();
        Matcher m = UNDERSCORE_PATTERN.matcher(underscore);
        while (m.find()){
            m.appendReplacement(sb,m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }
    /**
     * @description: 首字母大写
     * @author：RenBin
     * @createTime：2020/7/22 10:44
     */
    public static String upperCaseFirstLatter(String str){
        char[] strChar=str.toCharArray();
        if (strChar[0] >= 'a' && strChar[0] <= 'z') {
            strChar[0] = (char) (strChar[0] - 32);
        }
        return String.valueOf(strChar);
    }
    /**
     * @description: 首字母小写
     * @author：RenBin
     * @createTime：2020/7/22 12:43
     */
    public static String lowCaseFirstLatter(String str){
        char[] strChar=str.toCharArray();
        if (strChar[0] >= 'A' && strChar[0] <= 'Z') {
            strChar[0] = (char) (strChar[0] + 32);
        }
        return String.valueOf(strChar);
    }

}
