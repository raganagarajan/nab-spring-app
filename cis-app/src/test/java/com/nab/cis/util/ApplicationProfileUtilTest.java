package com.nab.cis.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import com.nab.cis.CISApplication;
import com.nab.cis.constant.ApplicationConstants;

public class ApplicationProfileUtilTest {
	
	@Test
    public void springApplicationInitializationTest() {
		SpringApplication application = new SpringApplication(CISApplication.class);
        ApplicationProfileUtil.setDefaultProfile(application);
        String[] args = {""};
        Environment env = application.run(args).getEnvironment();
        assertThat(env).isNotNull();
        String[] actualProfiles = ApplicationProfileUtil.getActiveProfiles(env);
        List<String> actualProfilesList = Arrays.asList(actualProfiles);
        assertThat(actualProfilesList).isNotEmpty();
        assertThat(actualProfilesList).contains(new String[] {ApplicationConstants.SPRING_PROFILE_DEV});
	}
}
