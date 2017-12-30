package util;

/** 公用工具类
 * @author Administrator
 *
 */
public class CommonsUtil {
	/** 将字段名中的下划线去掉，并将下划线右边的字母改为大写
	 * @param changedName
	 * @return
	 */
	public static String changeName(String name) {
		//获取下划线在字段名中的位置
		int index=name.indexOf("_");
		//如果字段名中有下划线则将字段名中的下划线去掉，并将下划线右边的字母改为大写
		if (index != -1) {
			//StringBuffer用来拼接改变后的字段名
			StringBuffer sb=new StringBuffer();
			//切除下划线
			String[] strArr=name.split("_");
			//将数组中的第一个元素添加到StringBuffer中
			sb.append(strArr[0]);
			//将数组中的第二个元素的首字母大写，并添加到StringBuffer中
		    sb.append(toUpperCaseFirst(strArr[1]));
		    //为name重新赋值
		    name=sb.toString();
		}
		//返回name
		return name;
	}
	
	/** 将字符串中的首字母大写
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirst(String str) {
		//将字符串变为字符数组
	    char[] charArray = str.toCharArray(); 
	    //将字符数组中的第一个字母通过ASCII编码转为大写
	    charArray[0] -= 32;
	    //将字符数组转为字符串，并返回
	    return String.valueOf(charArray);
	}
}
