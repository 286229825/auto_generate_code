package util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/** 获取freemarker模版的类
 * @author Administrator
 *
 */
public class FreeMarkerTemplateUtils {
	private FreeMarkerTemplateUtils() {}
	//获取对应当前freemarker版本号的freemarker配置对象
	private static final Configuration CONFIGURATION=new Configuration(Configuration.VERSION_2_3_23);
	
	static {
		//设置自定义模版加载路径，"/templates"表示根目录下的templates文件夹
		CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/templates"));
		//设置默认编码
		CONFIGURATION.setDefaultEncoding("utf-8");
		//设置模版的异常处理，往上抛出
		CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		//设置不使用缓存（因为每个模版在当前需求中只会使用一次，所以不需要缓存）
		CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
	}
	
	/** 获取模版。根据templateName和在CONFIGURATION中配置的自定义模版加载路径一起组成当前模版的路径
	 * @param templateName 
	 * @return
	 * @throws Exception
	 */
	public static Template getTemplate(String templateName) throws Exception {
		return CONFIGURATION.getTemplate(templateName);
	}
}
