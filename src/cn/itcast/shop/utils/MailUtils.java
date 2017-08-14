package cn.itcast.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * �ʼ����͹���
 * @author xkx
 *
 */
public class MailUtils {
	/**
	 * �����ʼ��ķ���
	 * @param to �ռ���
	 * @param code ������
	 */
	public static void sendMail(String to,String code){
		/**
		 * 1.���һ��Session����.
		 * 2.����һ�������ʼ��Ķ���Message.
		 * 3.�����ʼ�Transport
		 */
		// 1.������Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");// ָ����֤Ϊtrue
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xkx_0912", "xkx0912");
			}
			
		});
		// 2.�����ʼ�����:
		Message message = new MimeMessage(session);
		// ���÷�����:
		try {
			message.setFrom(new InternetAddress("xkx_0912@163.com"));
			// �����ռ���:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ���� CC   ����BCC
			// ���ñ���
			message.setSubject("����e�̳ǹٷ������ʼ�");
			// �����ʼ�����:
			message.setContent("<h1>e�̳ǹٷ������ʼ�!������������ɼ������!</h1><h3><a href='http://172.16.38.188:8080/shop/user_active.action?code="+code+"'>http://172.16.38.188:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 3.�����ʼ�:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
