package net.mindtap.showcase.cucumber.utils.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
	private static String userName = "automation.resultsqait@gmail.com";
	private static String password = "QaitAutomation";

	private static Session getSession() {
		String smtpHostServer = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHostServer);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		
		return Session.getInstance(props, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(userName, password);
			}
		});
	}

	public static void sendMail(String[] recipients, String sender, String subject, String body) {

		try {
			MimeMessage msg = new MimeMessage(getSession());

			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(sender, "QAIT-MindTap"));
			msg.setSubject(subject, "UTF-8");
			msg.setContent(body, "text/html; charset=utf-8");
			msg.setSentDate(new Date());
			InternetAddress[] recipientAddresses=new InternetAddress[recipients.length];
			for(int i=0;i<recipients.length;i++){
				if(recipients[i]!=null && recipients[i].trim().length()>0){
					recipientAddresses[i] = new InternetAddress(recipients[i], false);
				}
			}
			
			msg.setRecipients(Message.RecipientType.TO, recipientAddresses);

			Transport.send(msg);

			System.out.println("Email Sent Successfully!!");
		} catch (MessagingException | UnsupportedEncodingException e) {
			System.out.println("Email Failure");
			e.printStackTrace();
		}
	}
}
