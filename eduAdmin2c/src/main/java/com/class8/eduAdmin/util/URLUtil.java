package com.class8.eduAdmin.util;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLUtil {
	/**
	 * 对url进行编码
	 * @param url
	 * @return
	 */
	public static String encode(String url){
		try {  
            return URLEncoder.encode(url, "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
		 return null;  
	}
	
	/**
	 * 对url进行解码
	 * @param url
	 * @return
	 */
	public static String decode(String url){
		try {  
            return URLDecoder.decode(url, "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
	    return null;  
	}
	
	/**
	 * 判断url是否存在
	 * @param url
	 * @return
	 */
	public static boolean exists(String url){
		try {  
            URL u = new URL(url);  
            HttpURLConnection urlconn = (HttpURLConnection) u.openConnection();  
            int state = urlconn.getResponseCode();  
            if (state == 200) {  
                return true;  
            } else {  
                return false;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
		return false;  
	}
}
