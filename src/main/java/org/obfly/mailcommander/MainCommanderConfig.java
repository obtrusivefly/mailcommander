package org.obfly.mailcommander;

import org.obfly.mailcommander.connect.MailReader;
import org.obfly.mailcommander.worker.Receiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCommanderConfig {
	
	private static final int RECEIVER_INTERVAL_SEC = 5;
	private static final int START_DELAY_SEC = 3;
	private static final String RECEIVER_NAME = "mailReceiver";
	
	private static final String MAIL_SERVER = "imap.gmail.com";
	private static final String MAIL_PROTOCOL = "imap";
	private static final String MAIL_USER = "";
	private static final String MAIL_PASS = "";
	
	public @Bean Receiver receiver() {
		return new Receiver(RECEIVER_NAME, START_DELAY_SEC, RECEIVER_INTERVAL_SEC);
	}
	
	public @Bean MailReader mailReader() {
		return new MailReader(MAIL_SERVER, MAIL_PROTOCOL, MAIL_USER, MAIL_PASS);
	}
	
	

}
