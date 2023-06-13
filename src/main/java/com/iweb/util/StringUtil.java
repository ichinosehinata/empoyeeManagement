package com.iweb.util;

import com.iweb.view.MainView;

import java.util.Random;

/**
 * @author Guan
 * @date 2023/4/21 19:23
 */
public class StringUtil {
    private static String strPool;
    static {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 128; i++) {
            if (Character.isLetterOrDigit((char) i)){
                sb.append((char)i);
            }
        }
        strPool=sb.toString();
    }
    public static String getRandomString(){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(strPool.charAt(r.nextInt(strPool.length())));
        }
        return sb.toString();
    }
    public static boolean isInteger(String str){
        String regex="[0-9]+";
        if (str.matches(regex)) {
            return true;
        }else {
            return false;
        }
    }
}
