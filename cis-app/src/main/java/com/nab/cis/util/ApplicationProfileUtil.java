package com.nab.cis.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import com.nab.cis.constant.ApplicationConstants;

public class ApplicationProfileUtil {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	
	private ApplicationProfileUtil() {
	}
	
	public static void setDefaultProfile(SpringApplication app) {
        Map<String, Object> defaultProperties = new HashMap<>();
        defaultProperties.put(SPRING_PROFILE_DEFAULT, ApplicationConstants.SPRING_PROFILE_DEV);
        app.setDefaultProperties(defaultProperties);
    }
	
	public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
