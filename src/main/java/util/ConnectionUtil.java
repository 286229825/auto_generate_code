package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 数据库连接获取类
 * @author Administrator
 *
 */
public class ConnectionUtil {
	//数据库连接参数
	private final String DRIVER="com.mysql.jdbc.Driver";
	private final String URL= "jdbc:mysql://localhost:3306/abc?characterEncoding=utf-8";
	private final String USER= "root";
	private final String PASSWORD="286229825";
	
	private ConnectionUtil() {}
	private static ConnectionUtil connectionUtil;
	
	/** 获取ConnectionUtil类的实例
	 * @return
	 */
	public static ConnectionUtil getInstance() {
		if (connectionUtil == null) {
			synchronized(ConnectionUtil.class) {
				if (connectionUtil == null) {
					connectionUtil=new ConnectionUtil();
				}
			}
		}
		return connectionUtil;
	}
	
	/** 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		try {
			//加载数据库驱动
			Class.forName(DRIVER);
			//获取数据库连接
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
