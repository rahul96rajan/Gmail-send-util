package com.pratise.gmailAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailUtil {
	public static Properties configProp;
	public static Properties props;

	public static void initialiseProperties() {
		try {
			configProp = new Properties();
			FileInputStream in = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config.properties");
			configProp.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		props = new Properties();
		props.put("mail.smtp.host", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
	}

	public static boolean sendMail(String subject, String body) {

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configProp.getProperty("email_id"),
						configProp.getProperty("password"));
			}
		});
		try {
			MimeMessage msg = new MimeMessage(session);
			String to = configProp.getProperty("to_id");
			InternetAddress[] address = InternetAddress.parse(to, true);
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(body);
			msg.setHeader("XPriority", "1");
			Transport.send(msg);
			System.out.println("Mail has been sent successfully to : " + configProp.getProperty("to_id"));
			return true;
		} catch (MessagingException mEx) {
			System.out.println("Unable to send an email" + mEx);
			return false;
		}
	}
}
