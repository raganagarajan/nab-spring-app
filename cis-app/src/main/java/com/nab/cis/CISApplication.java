package com.nab.cis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nab.cis.util.ApplicationProfileUtil;

@SpringBootApplication
public class CISApplication {
	
	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CISApplication.class);
        ApplicationProfileUtil.setDefaultProfile(application);
        application.run(args).getEnvironment();
    }
}
