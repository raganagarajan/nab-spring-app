package com.nab.cis;

import org.junit.Test;

public class CISApplicationTest {

	@Test
	public void startSpringBootApplication() {
		CISApplication.main(new String[]{
				"--spring.active.profiles=test",
			    "--server.port=9091",
			    });
	}
}
