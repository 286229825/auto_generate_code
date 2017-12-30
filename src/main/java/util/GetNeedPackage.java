package util;

import java.util.HashMap;
import java.util.Map;

/** 获取数据库类型所对应的java中需要导入的包名
 * @author Administrator
 *
 */
public class GetNeedPackage {
	//数据库类型与java中需要导入的报名的映射
	private static Map<String, String> map=new HashMap<>();
	
	//在类加载的时候就初始化map
	static {
		map.put("DATE", "import java.sql.Date;");
		map.put("DATETIME", "import java.sql.Timestamp;");
	}
	//获取map
	public static Map<String, String> getMap() {
		return map;
	}
}
