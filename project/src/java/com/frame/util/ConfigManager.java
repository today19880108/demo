package com.frame.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 
 * <p>[读取配置文件]</p>
 * @author yushp
 *
 */
public class ConfigManager {
	private static Document pathxmldoc = null;

	/**
	 * 
	 * <p>[读取文档]</p>
	 * @return
	 */
	public static Document getPathXmlDoc(){
		if (pathxmldoc!=null){
			return pathxmldoc;
		}else{
			SAXReader builder = new SAXReader();
			InputStream ins = ConfigManager.class
					.getResourceAsStream("/comm/config.xml");
			try			{
				pathxmldoc = builder.read(ins);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return pathxmldoc;
	}

	/**
	 * 
	 * <p>[根据配置文件标签路径获得节点值]</p>
	 * @param textlabel
	 * @return
	 */
	public static String getItemValue(String textlabel){
		String value = "";
		Document doc = ConfigManager.getPathXmlDoc();

		Node node = null;
		try{
			node = doc.selectSingleNode(textlabel);   
			value = node.getStringValue().trim();   
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * 
	 * <p>[根据配置文件标签路径获得子节点值]</p>
	 * @param textlabel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getListValue(String textlabel){
		List<String> value = new ArrayList<String>();
		Document doc = ConfigManager.getPathXmlDoc();

		List<Node> node = null;
		try{
			node = doc.selectNodes(textlabel);  
			for(Node n: node){
				value.add(n.getStringValue().trim());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
}