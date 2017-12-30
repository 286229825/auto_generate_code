package util;

import java.util.HashMap;
import java.util.Map;

/** 获取数据库类型相对应的java简单类型
 * @author Administrator
 *
 */
public class GetTypeMap {
	//数据库类型与java类型匹配的字典
	private static Map<String, String> map=new HashMap<>();
	
	//在类加载的时候就初始化map
	static {
		map.put("INT", "Integer");
		map.put("VARCHAR", "String");
		map.put("DOUBLE", "double");
		map.put("TEXT", "String");
		map.put("BOOLEAN", "boolean");
		map.put("CHAR", "char");
		map.put("DATE", "Date");
		map.put("DATETIME", "TimeStamp");
	}
	//获取map
	public static Map<String, String> getMap() {
		return map;
	}
}
