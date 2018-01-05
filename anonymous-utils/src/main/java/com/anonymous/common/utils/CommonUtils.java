package com.anonymous.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.anonymous.message.auote.MessageQuote;

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
}
