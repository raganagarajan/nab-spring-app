package com.nab.cis.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application.dozer")
public class DozerBeanMapperProperties {

	private List<String> mappingFiles;

	public List<String> getMappingFiles() {
		return mappingFiles;
	}

	public void setMappingFiles(List<String> mappingFiles) {
		this.mappingFiles = mappingFiles;
	}
}
