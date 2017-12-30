package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 表数据获取类
 * @author Administrator
 *
 */
public class TableDataUtil {
	
	/** 获取表中所有的列的信息
	 * @return
	 */
	public static ResultSet getTableData(String tableName) {
		//获取数据库连接
		Connection connection=ConnectionUtil.getInstance().getConnection();
		try {
			//获取DatabaseMetaData接口
			DatabaseMetaData metaData = connection.getMetaData();
			//获取user表中所有的列的信息
			return metaData.getColumns(null, null, tableName, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
