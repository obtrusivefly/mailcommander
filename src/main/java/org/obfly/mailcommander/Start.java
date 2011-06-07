package org.obfly.mailcommander;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {

	private static Logger log = Logger
			.getLogger(Start.class.getCanonicalName());

	public static void main(String[] args) {
		log.debug("Starting at last...");
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				MainCommanderConfig.class);

	}

}
