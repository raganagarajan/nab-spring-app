package com.nab.cis.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nab.cis.config.properties.DozerBeanMapperProperties;

@Configuration
public class DozerBeanMapperConfiguration {

	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean(DozerBeanMapperProperties dozerBeanMapperProperties) {
		DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(dozerBeanMapperProperties.getMappingFiles());
        return dozerBean;
	}
}
