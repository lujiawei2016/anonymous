package com.anonymous.common.utils;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月5日下午2:27:55
 */
public class CommonUtils {

	/**
	 * 判断是否为手机号码
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		try {
			String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
	        Pattern p = Pattern.compile(regExp);  
	        Matcher m = p.matcher(phone);  
	        return m.matches();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }
	
	/** 
     * 对字符串md5加密(大写+数字) 
     * @param str 传入要加密的字符串 
     * @return  MD5加密后的字符串 
     */  
      
    public static String MD5(String s) {  
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};         
  
        try {  
            byte[] btInput = s.getBytes();  
            // 获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
            // 使用指定的字节更新摘要  
            mdInst.update(btInput);  
            // 获得密文  
            byte[] md = mdInst.digest();  
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
