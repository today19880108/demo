package com.frame.util;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * <p>[公共工具类]<p>
 * @author yushp
 *
 */
public class CommUtil {
	
	/**
	 * 
	 * <p>[空值处理方法]<p>
	 * @author yushp
	 * @param src
	 * @param view
	 * @return
	 */
	public static <T> T nvl(T src, T view){
		if(src==null||"".equals(src)){
			return view;
		}
		return src;
	}
	
	/**
	 * 
	 * <p>[判断等值处理方法]<p>
	 * @author yushp
	 * @param src
	 * @param view
	 * @return
	 */
	public static <T> T decode(T src, T val, T view){
		if((val!=null&&val.equals(src))||val==src){
			return view;
		}
		return src;
	}
	
	/**
	 * 
	 * <p>[有序集合去掉指定值]<p>
	 * @author yushp
	 * @param src
	 */
	public static <T> void clearNull(List<T> src, T val){
		Iterator<T> iterator = src.iterator();  
		while(iterator.hasNext()){  
			T e = iterator.next();  
		  if(e==val){  
		  	iterator.remove();  
		  }  
		} 
	} 
}
