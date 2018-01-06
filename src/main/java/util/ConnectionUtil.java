package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import generate.DbInfo;

/** 数据库连接获取类
 * @author Administrator
 *
 */
public class ConnectionUtil {
	
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
	public Connection getConnection(DbInfo dbInfo) {
		try {
			//加载数据库驱动
			Class.forName(dbInfo.getDriver());
			//获取数据库连接
			return DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUser(), dbInfo.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
