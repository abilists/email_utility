package io.utility.email;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.mail.internet.MimeUtility;

/**
 * From Source : https://github.com/abilists/email_utility
 * @author Joon
 *
 */
public class Emailer {

	public static String EMIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	public static String DEFAULT_PROTOCAL = "TLSv1.2";

	public static String DEFAULT_CONTENTS_TEXT = "utf-8";

	public static String DEFAULT_CONTENTS_HTML = "text/html; charset=utf-8";

	public static void sendEmail(EmailBean email, final String userName, final String password) throws IOException {
		Emailer.sendEmail(email, userName, password, Emailer.DEFAULT_CONTENTS_HTML, null);
	}

	public static void sendEmail(EmailBean email, final String userName, final String password, String strHtml, String protocols) throws IOException {

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
			if (email.getReplyTo() != null && !email.getReplyTo().isEmpty()){
				message.setReplyTo(InternetAddress.parse(email.getReplyTo()));
			}
			if (email.getCc() != null && !email.getCc().isEmpty()){
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getCc()));
			}
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
			message.setSubject(MimeUtility.encodeText(email.getSubject(), "utf-8", "B"));

			// TEXT
		    MimeBodyPart textPart = new MimeBodyPart();
		    textPart.setText(email.getMsg(), DEFAULT_CONTENTS_TEXT);
		    // HTML
		    MimeBodyPart htmlPart = new MimeBodyPart();
		    htmlPart.setContent(email.getMsg(), DEFAULT_CONTENTS_HTML);
		    // Set text, html
			Multipart multiPart=new MimeMultipart("alternative");
			multiPart.addBodyPart(textPart);
			multiPart.addBodyPart(htmlPart);

	     	// Attache file
	     	if(email.getFilePath() != null) {
	     		File file=new File(email.getFilePath());
	     		BodyPart filePart=new MimeBodyPart();
	     		DataSource attachment=new FileDataSource(file);
	     		filePart.setDataHandler(new DataHandler(attachment));
	     		filePart.setFileName(file.getName());
	     		multiPart.addBodyPart(filePart);
	     	}

	     	// Set text or html
	     	if(!strHtml.isEmpty()) {
	     		message.setContent(multiPart, strHtml);
	     	} else {
	     		message.setContent(multiPart);	
	     	}

	     	Transport.send(message);
		} catch (UnsupportedEncodingException ue) {
			throw new IOException(ue);
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