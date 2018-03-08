package com.anonymous.httpclient.utils;

import java.security.MessageDigest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import net.sf.json.JSONObject;

/**
 * http请求工具类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年9月5日上午10:23:38
 */
public class HttpUtils {
	
	private static HttpClient httpClient;
	
	/**
	 * 描述:多线程http连接管理器，用于管理http连接
	 */
	private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
	
	private HttpUtils() {
	}
	
	/**
	 * @description 获取httpClient实例
	 * @author lupb
	 * @date Oct 17, 2012
	 * @return HttpClient实例
	 */
	public static HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = new HttpClient(httpConnectionManager);
			// 忽略Cookies
			httpClient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
			// 每主机最大连接数和总共最大连接数，通过hosfConfiguration设置host来区分每个主机
			httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(8);
			httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(48);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);
			httpClient.getHttpConnectionManager().getParams().setTcpNoDelay(true);
			httpClient.getHttpConnectionManager().getParams().setLinger(1000);
			// 失败的情况下会进行3次尝试,成功之后不会再尝试
			httpClient.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		}
		return httpClient;
	}
	
	/**
	 * 向指定URL发送get请求
	 * @param url
	 * @return
	 */
	public static JSONObject getMethodRequest(String url) {
		GetMethod getMethod = new GetMethod(url);
		try {
			// 访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(getMethod);
			// 返回成功状态码200
			if (statusCode == 200) {
				String str = getMethod.getResponseBodyAsString();
				return JSONObject.fromObject(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();// 释放连接
			getHttpClient().getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}
	
	/**
	 * @author ssl
	 * @since 2012-12-7
	 * @param url
	 * @return 
	 */
	public static String getRequest(String url) {
		GetMethod getMethod = new GetMethod(url);
		try {
			// 访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(getMethod);
			// 返回成功状态码200
			if (statusCode == 200) {
				String str = getMethod.getResponseBodyAsString();
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();// 释放连接
			getHttpClient().getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}
	
	/**
	 * @author ssl
	 * @since 2012-12-7
	 * @param url
	 * @return 
	 */
	public static JSONObject getPostRequest(String url) {
		PostMethod postMethod = new PostMethod(url);
		try {
			// 访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(postMethod);
			// 返回成功状态码200
			if (statusCode == 200) {
				String str = postMethod.getResponseBodyAsString();
				return JSONObject.fromObject(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
			getHttpClient().getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}
	
	/** 
	 * 方法名: String postMethodRequest(String url, String data)</br>
	 * 详述: 向指定url发出HttpPost请求，请求数据转换为流</br>
	 * 开发人员：卢嘉威</br>
	 * 创建时间：Jan 11, 2013</br>
	 * @param url 访问地址
	 * @param data 请求提交的数据
	 * @return 返回数据
	 */
	public static String postMethodRequest(String url, String data) {
		String responseStr = "";
		PostMethod postMethod = new PostMethod(url);
		try {
			RequestEntity en = new StringRequestEntity(data, "text/plain", "utf-8");
			postMethod.setRequestEntity(en);
			// 访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(postMethod);
			// 返回成功状态码200
			if (statusCode == 200) {
				responseStr = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
			getHttpClient().getHttpConnectionManager().closeIdleConnections(0);
		}
		return responseStr;
	}
	
	/**
	 * 带参数调用接口
	 * @param url
	 * @param basicNameValuePairs
	 * @return
	 */
	public static String PostByHttpClient(String url, List<NameValuePair> basicNameValuePairs) {
		PostMethod post = null;
		try {
			post = new PostMethod(url);
			post.getParams().setContentCharset("utf-8");
			if (basicNameValuePairs != null)
				post.setRequestBody(basicNameValuePairs.toArray(new NameValuePair[basicNameValuePairs.size()]));
			// 访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(post);
			// 返回成功状态码200
			if (statusCode == 200) {
				String str = post.getResponseBodyAsString();
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
			getHttpClient().getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}
	
	/**
	 * 带请求头参数调用接口
	 * @param url
	 * @param basicNameValuePairs
	 * @param headerMap
	 * @return
	 */
	public static String PostByHttpClient(String url, List<NameValuePair> basicNameValuePairs,Map<String, String> headerMap) {
		PostMethod post = null;
		try {
			post = new PostMethod(url);
			post.getParams().setContentCharset("utf-8");
			
			if(headerMap != null){
        		Iterator<Entry<String, String>> iter = headerMap.entrySet().iterator();
        		while(iter.hasNext()){
        			Entry<String, String> entity = iter.next();
        			String key = entity.getKey();
        			String val = entity.getValue();
        			post.addRequestHeader(key, val);
        		}
        	}
			
			if (basicNameValuePairs != null)
				post.setRequestBody(basicNameValuePairs.toArray(new NameValuePair[basicNameValuePairs.size()]));
			// 访问指定URL并取得返回状态码
			int statusCode = getHttpClient().executeMethod(post);
			System.out.println(post.getResponseBodyAsString());
			// 返回成功状态码200
			if (statusCode == 200) {
				String str = post.getResponseBodyAsString();
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
			getHttpClient().getHttpConnectionManager().closeIdleConnections(0);
		}
		return null;
	}
	
	public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
	
}