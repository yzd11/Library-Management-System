package service.utils;

/**
 * @author 禹治东
 * @version 1.0
 */
//字符串工具类
public class MyStringUtils {
    //判断是否为空
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }
    //判断是否不为空
    public static boolean isNotEmpty(String str){
        if(str != null && !"".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }
}
