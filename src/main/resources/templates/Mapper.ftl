package mapper;

import java.util.List;

import po.${tableName};

public interface ${tableName}Mapper {
	/** 插入一条记录
	 * @param user
	 * @throws Exception
	 */
	public void insertOne(${tableName} ${tableName?uncap_first}) throws Exception;
	
	/** 批量插入
	 * @param list
	 * @throws Exception
	 */
	public void insertBatch(List<${tableName}> list) throws Exception;
	
	/** 根据 id 删除一条记录
	 * @param id
	 * @throws Exception
	 */
	public void deleteOne(int id) throws Exception;
	
	/** 根据 id 批量删除
 	 * @param list
	 * @throws Exception
	 */
	public void deleteBatch(List<Integer> list) throws Exception;
	
	/** 根据 user 中 id 更新一条记录
	 * @param user 其中的 id 不能为空
	 * @throws Exception
	 */
	public void updateOne(${tableName} ${tableName?uncap_first}) throws Exception;
	
	/** 根据 user 中 id 批量更新
	 * @param user 其中的 id 不能为空
	 * @throws Exception
	 */
	public void updateBatch(List<${tableName}> list) throws Exception;
	
	/** 条件查询
	 * @param user
	 * @throws Exception
	 */
	public List<${tableName}> selectByCondition(${tableName} ${tableName?uncap_first}) throws Exception;
	
	/** 分页和条件查询
	 * @param map
	 * @throws Exception
	 */
	public List<${tableName}> selectByPage(${tableName} ${tableName?uncap_first}) throws Exception;
}
