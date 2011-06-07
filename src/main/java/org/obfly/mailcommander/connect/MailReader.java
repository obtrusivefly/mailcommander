package org.obfly.mailcommander.connect;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

public class MailReader {
	
	private static final Logger log = Logger.getLogger(MailReader.class);
	
	private String server;
	private String protocol;
	private String user;
	private String pass;
	
	public MailReader(String server, String protocol, String user, String pass) {
		this.server = server;
		this.protocol = protocol;
		this.user = user;
		this.pass = pass;
	}

	public Message[] receiveMessages() {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", protocol);
			try {
				Session session = Session.getDefaultInstance(props, null);
				Store store = session.getStore("pop3");
				store.connect(server, user, pass);

				Folder inbox = store.getFolder("Inbox");
				inbox.open(Folder.READ_ONLY);
				Message messages[] = inbox.getMessages();
				return messages;

			} catch (NoSuchProviderException e) {
			log.error(e.getMessage(), e);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
		return new Message[] {};
	}

}