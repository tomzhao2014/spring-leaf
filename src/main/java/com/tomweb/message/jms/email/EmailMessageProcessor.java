package com.tomweb.message.jms.email;

import com.tomweb.message.jms.JmsMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;

/**
 * Created by tom on 15/12/28.
 */
public class EmailMessageProcessor
        implements JmsMessageProcessor<EmailMessage>
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String DEFAULT_DISPLAY_NAME = "eteams";
    private static EmailMessageProcessor emailMessageProcessor = new EmailMessageProcessor();

    public static EmailMessageProcessor getInstance() {
        return emailMessageProcessor;
    }

    public void processMessage(EmailMessage message)
    {
        try {
            Session session = EmailSessionFactory.getSession();
            MimeMessage mimeMessage = new MimeMessage(session);
            String smtp = session.getProperty("mail.transport.protocol");
            String userName = session.getProperty("userName");
            message.setFromAddress(userName);
            mimeMessage.setSubject(MimeUtility.encodeText(message.getTitle(), "gb2312", "B"));
            mimeMessage.setSentDate(new Date());

            mimeMessage.setFrom(new InternetAddress(message.getFromAddress(), "eteams"));

            Address[] toAddresses = new Address[message.getToAddresses().size()];
            for (int i = 0; i < toAddresses.length; i++) {
                toAddresses[i] = new InternetAddress((String)message.getToAddresses().get(i));
            }
            mimeMessage.addRecipients(Message.RecipientType.TO, toAddresses);

            Multipart multipart = new MimeMultipart();

            BodyPart contentBodyPart = new MimeBodyPart();
            contentBodyPart.setContent(message.getContent(), message.getFormat());
            multipart.addBodyPart(contentBodyPart);

            mimeMessage.setContent(multipart);
            mimeMessage.saveChanges();
            Transport transport = session.getTransport(smtp);
            transport.connect();

            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            this.logger.info("Email send success,fromAddress:{},toAddress:{}", userName, message.getAddress());
        } catch (Exception e) {
            EmailSendFailureHandler handler = message.getHandler();
            if (handler != null) {
                handler.onSendFailue(message.getParams(), message.getAddress());
            }
            this.logger.error("An error occurred sending mail,fromAddress:{},toAddress:{},{},{}", new Object[] { message.getFromAddress(), message.getAddress(),
                    e.getMessage(), e });
        }
    }
}
