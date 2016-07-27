package com.class8.eduAdmin.shiro;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.class8.eduAdmin.model.Resource;
import com.class8.eduAdmin.service.IResourceService;
/**
 * 构建shiro的拦截器链，通过从数据库中和配置文件中加载的方式，需要注意加载的顺序问题
 *
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{
	
	private static final String PERMISSION_PATTERN = "perms[\"{0}\"]";
	private static final String DEFAULT_URL = "#";
	
	@Autowired
	private IResourceService resourceService;
	
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		ini.addSection(Ini.DEFAULT_SECTION_NAME);
		Ini.Section section = ini.getSection("urls");
		if(CollectionUtils.isEmpty(section)){
			 section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		
		List<Resource> resources = resourceService.findAllResources();
		Iterator<Resource> iterator = resources.iterator();
		while (iterator.hasNext()) {
			Resource resource = iterator.next();
			if(StringUtils.hasText(resource.getPermission()) && !DEFAULT_URL.equals(resource.getUrl())){
				/**
				 * 为数据库中定义的url，配置过滤器链，包括authc和perms过滤器
				 */
				section.put(resource.getUrl(), DefaultFilter.authc.name() + "," + MessageFormat.format(PERMISSION_PATTERN, resource.getPermission()));
			}
		}
		ini.load(filterChainDefinitions);
		section.putAll(ini.getSection(Ini.DEFAULT_SECTION_NAME));
		return section;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}
	
	private String filterChainDefinitions;
	
	public void setFilterChainDefinitions(String filterChainDefinitions){
		this.filterChainDefinitions = filterChainDefinitions;
	}

}
