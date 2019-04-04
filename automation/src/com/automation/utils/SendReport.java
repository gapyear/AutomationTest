package com.automation.utils;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendReport {
	
	public static String myEmailAccount = "15902853985@163.com";
	public static String myEmailPassword = "lategapyear@1995";
	public static String myEmailSMTPHost = "smtp.163.com";
	public static String receiveMailAccount = "2179026898@qq.com";
	
	public static void sendReport() throws Exception{
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", myEmailSMTPHost);
		props.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		
		MimeMessage message = createMimeMessage(session,myEmailAccount,receiveMailAccount);
		
		Transport transport = session.getTransport();
		transport.connect(myEmailSMTPHost,myEmailAccount,myEmailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
		
	}

	private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception{
		// TODO Auto-generated method stub
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sendMail));
		
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));
		message.setSubject("测试报告");
		
		Multipart multipart = new MimeMultipart();
		BodyPart contentPart = new MimeBodyPart();
		
		contentPart.setText("Hi,详细测试报告请查看附件\n Best Regards,Tester");
		contentPart.setHeader("Content-Type", "text/html;charset=GBK");
		multipart.addBodyPart(contentPart);
		
		String file = "C:\\Users\\shaluo\\workspace\\automation\\src\\report.html";
		File usFile = new File(file);
		MimeBodyPart fileBody = new MimeBodyPart();
		DataSource source = new FileDataSource(file);
		fileBody.setDataHandler(new DataHandler(source));
		sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		
		fileBody.setFileName("=?GBK?B?"+enc.encode(usFile.getName().getBytes()) + "?=");
		multipart.addBodyPart(fileBody);
		message.setContent(multipart,"text/html;charset=UTF-8");
		
		message.setSentDate(new Date());
		message.saveChanges();
		
		return message;
	}
}
