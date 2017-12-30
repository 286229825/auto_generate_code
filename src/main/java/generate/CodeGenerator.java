package generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import freemarker.template.Template;
import util.CommonsUtil;
import util.FreeMarkerTemplateUtils;
import util.GetNeedPackage;
import util.GetTypeMap;
import util.TableDataUtil;

/**
 * 自动生成相关代码的类
 * 
 * @author Administrator
 *
 */
public class CodeGenerator {
	// 当前项目在本地文件系统的路径+"/src/main/java"
	private static final String DISK_PATH = System.getProperty("user.dir") + "/src/main/java";

	// 指定生成的实体类文件的包名
	private static final String MODEL_PACKAGE = "po";

	// 指定生成的mapper文件的包名
	private static final String MAPPER_PACKAGE = "mapper";

	// 实体类模版名称
	private static final String MODEL_TEMPLATE = "Model.ftl";

	// mapper模版名称
	private static final String MAPPER_TEMPLATE = "Mapper.ftl";

	// mapperXML模版名称
	private static final String MAPPERXML_TEMPLATE = "MapperXML.ftl";
	
	public static void generate(String ...tableNames) throws Exception {
		for (String tableName : tableNames) {
			generateModel(tableName, TableDataUtil.getTableData(tableName));
			generateMapperXML(tableName, TableDataUtil.getTableData(tableName));
			generateMapper(tableName);
		}
	}

	public static void main(String[] args) throws Exception {
		generate("user","authority");
	}

	/**
	 * 通过freemarker的模版自动生成相关代码
	 * 
	 * @param templateName
	 *            自定义的模版的名字（以".ftl"为后缀）
	 * @param file
	 *            代码输出路径
	 * @param map
	 *            模版数据
	 * @throws Exception
	 */
	private static void generateFileByTemplate(String templateName, File file, Map<String, Object> map)
			throws Exception {
		// 获取freemarker模版
		Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
		// 定义输出流
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		// 执行模版并输出
		template.process(map, bw);
	}

	/**
	 * 生成实体类文件
	 * 
	 * @param tableName
	 *            需要生成实体类的表名，用来作为实体类的类名
	 * @param packageName
	 *            输出的包名
	 * @param rs
	 *            表的相关字段的数据
	 * @throws Exception
	 */
	private static void generateModel(String tableName, ResultSet rs) throws Exception {
		// 将表名的首字母大写，用来作为实体类的类名
		tableName = CommonsUtil.toUpperCaseFirst(tableName);
		// 定义代码的基本输出路径
		final String path = DISK_PATH + "/" + MODEL_PACKAGE;
		// 创建代码输出文件夹
		File file = new File(path);
		file.mkdirs();
		// 定义代码的输出路径
		File modelFile = new File(path + "/" + tableName + ".java");
		// 用来存储表中各列的数据
		List<ColumnField> list = new ArrayList<ColumnField>();
		// 用来存储需要导入的包名
		List<String> needPackages = new ArrayList<String>();
		// 遍历表的相关字段的数据
		while (rs.next()) {
			// 自定义的类，用来描述列
			ColumnField columnField = new ColumnField();
			// 获取当前列的名称
			String name = rs.getString("COLUMN_NAME");
			columnField.setName(name);
			// 获取当前列修改后的名称
			columnField.setChangedName(CommonsUtil.changeName(name));
			// 获取当前列的数据类型
			String type = rs.getString("TYPE_NAME");
			columnField.setType(GetTypeMap.getMap().get(type));
			// 将当前类型所需要导入的包名封装到needPackage集合中
			String needPackage = GetNeedPackage.getMap().get(type);
			if (needPackage != null) {
				needPackages.add(needPackage);
			}
			// 获取当前列的注释
			columnField.setAnnotation(rs.getString("REMARKS"));
			// 添加到集合中
			list.add(columnField);
		}
		// 封装freemarker模版生成代码所需要的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fields", list);
		map.put("packageName", MODEL_PACKAGE.toLowerCase());
		map.put("tableName", tableName);
		map.put("needPackage", needPackages);
		// 自动生成代码
		generateFileByTemplate(MODEL_TEMPLATE, modelFile, map);
	}

	private static void generateMapper(String tableName) throws Exception {
		// 将表名的首字母大写，用来作为实体类的类名
		tableName = CommonsUtil.toUpperCaseFirst(tableName);
		// 定义代码的基本输出路径
		final String path = DISK_PATH + "/" + MAPPER_PACKAGE;
		// 创建代码输出文件夹
		File file = new File(path);
		file.mkdirs();
		// 定义代码的输出路径
		File modelFile = new File(path + "/" + tableName +"Mapper"+".java");
		// 封装freemarker模版生成代码所需要的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		// 自动生成代码
		generateFileByTemplate(MAPPER_TEMPLATE, modelFile, map);
	}

	private static void generateMapperXML(String tableName, ResultSet rs) throws Exception {
		// 将表名的首字母大写，用来作为实体类的类名
		String tableNameUpperFirst = CommonsUtil.toUpperCaseFirst(tableName);
		// 定义代码的基本输出路径
		final String path = DISK_PATH + "/" + MAPPER_PACKAGE;
		// 创建代码输出文件夹
		File file = new File(path);
		file.mkdirs();
		// 定义代码的输出路径
		File modelFile = new File(path + "/" + tableNameUpperFirst + "Mapper.xml");
		// 用来存储表中各列的数据
		List<ColumnField> list = new ArrayList<ColumnField>();
		// 遍历表的相关字段的数据
		while (rs.next()) {
			// 自定义的类，用来描述列
			ColumnField columnField = new ColumnField();
			// 获取当前列的名称
			String name = rs.getString("COLUMN_NAME");
			columnField.setName(name);
			// 获取当前列修改后的名称
			columnField.setChangedName(CommonsUtil.changeName(name));
			// 获取当前列的数据类型
			String type = rs.getString("TYPE_NAME");
			columnField.setType(GetTypeMap.getMap().get(type));
			// 获取当前列的注释
			columnField.setAnnotation(rs.getString("REMARKS"));
			// 添加到集合中
			list.add(columnField);
		}
		// 封装freemarker模版生成代码所需要的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fields", list);
		map.put("tableName", tableName);
		map.put("tableNameUpperFirst", tableNameUpperFirst);
		// 自动生成代码
		generateFileByTemplate(MAPPERXML_TEMPLATE, modelFile, map);
	}
}
