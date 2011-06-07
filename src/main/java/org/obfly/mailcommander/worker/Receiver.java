package org.obfly.mailcommander.worker;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.obfly.mailcommander.connect.MailReader;
import org.springframework.beans.factory.annotation.Autowired;

public class Receiver extends Thread {

	Logger log = Logger.getLogger(Receiver.class.getCanonicalName());
	
	private int interval;
	private int startDelay;

	@Autowired
	private MailReader mailReader;
	
	public Receiver(String name, int startDelaySec, int intervalSec) {
		log.debug("Creating worker of name " + name);
		this.interval = intervalSec * 1000;
		this.startDelay = startDelaySec * 1000;
		this.setName(name);
		
		start();
	}

	public void run() {
		try {
			log.debug("Starting worker " + getName() + " in " + startDelay + " seconds");
			Thread.sleep(startDelay);
			while(true) {
				work();
				Thread.sleep(interval);
			}
			
		} catch (InterruptedException e) {
			log.error("Receiver interrupted", e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void work() throws IOException, MessagingException {
		log.debug("Worker " + getName() + " working...");
		Message messages[] = mailReader.receiveMessages();
		for( Message  m : messages) {
			log.debug("Message content: " + m.getContent().toString());
		}
	}
}
