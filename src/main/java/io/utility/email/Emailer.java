package io.utility.email;

import java.io.File;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * From Source : https://github.com/abilists/email_utility
 * @author Joon
 *
 */
public class Emailer {

	public static String EMIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	public static String DEFAULT_PROTOCAL = "TLSv1.2";

	public static String DEFAULT_CONTENTS_HTML = "text/html; charset=utf-8";

	public static void sendEmail(EmailBean email, final String userName, final String password) {
		Emailer.sendEmail(email, userName, password, Emailer.DEFAULT_CONTENTS_HTML, null);
	}

	public static void sendEmail(EmailBean email, final String userName, final String password, String strHtml, String protocols) {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", email.getSmtpAuthEnable());
		properties.put("mail.smtp.starttls.enable", email.getSmtpStarttlsEnable());
		properties.put("mail.smtp.host", email.getSmtpHost());
		properties.put("mail.smtp.port", email.getSmtpPort());
		if(protocols != null) {
			properties.put("mail.smtp.ssl.protocols", protocols);
		}

		Session session = Session.getInstance(properties, 
			new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setHeader("Content-Type", DEFAULT_CONTENTS_HTML);
			message.setFrom(new InternetAddress(email.getSmtpSender())); // From email
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
			message.setSubject(email.getSubject());

			BodyPart part1=new MimeBodyPart();
			part1.setText(email.getMsg());
	
			Multipart multiPart=new MimeMultipart();
			multiPart.addBodyPart(part1);
	     	// Attache file
	     	if(email.getFilePath() != null) {
	     		File file=new File(email.getFilePath());
	     		BodyPart part2=new MimeBodyPart();
	     		DataSource attachment=new FileDataSource(file);
	     		part2.setDataHandler(new DataHandler(attachment));
	     		part2.setFileName(file.getName());
	     		multiPart.addBodyPart(part2);
	     	}

	     	// Set text or html
	     	if(!strHtml.isEmpty()) {
	     		message.setContent(multiPart, strHtml);	
	     	} else {
	     		message.setContent(multiPart);	
	     	}

	     	Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isValid(String email) {

        Pattern pat = Pattern.compile(EMIL_REGEX);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}