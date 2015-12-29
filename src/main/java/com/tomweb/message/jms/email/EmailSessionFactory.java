package com.tomweb.message.jms.email;

import javax.mail.Session;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by tom on 15/12/28.
 */
public class EmailSessionFactory
{
    public static final String HOST = "smtp.qiye.163.com";
    public static final String PORT = "25";
    public static final String SMTP = "smtp";
    public static final String TEAMS_MAIL_ADDRESS = "service@eteams.cn";
    public static final String PASSWORD = "eteams2013";
    private static int counter = 0;
    private static ArrayList<Session> sessionList = new ArrayList();
    private static Session singleSession = null;

    static {
        buildSession();
    }

    public static Session getSession()
    {
        String profile = System.getProperty("spring.profiles.active");
        if ((profile == null) || (!"production".equalsIgnoreCase(profile))) {
            return singleSession;
        }
        return (Session)sessionList.get(getCount());
    }

    private static void buildSession()
    {
        EmailAccount emailAccount = new EmailAccount("smtp", "smtp.qiye.163.com", "25", "service@eteams.cn", "eteams2013", true);
        Properties property = getProperties(emailAccount);
        singleSession = Session.getInstance(property, new MailAuthenticator("service@eteams.cn", "eteams2013"));
        String userName = "service@eteams.cn";
        EmailAccount[] emailAccounts = new EmailAccount[5];
        for (int i = 0; i < 5; i++) {
            userName = userName.substring(0, userName.indexOf("@")) + (i + 1) + userName.substring(userName.indexOf("@"));
            emailAccounts[i] = new EmailAccount("smtp", "smtp.qiye.163.com", "25", userName, "eteams2013", true);
        }
        int length = emailAccounts.length;
        for (int i=0; i < length; i++)
        {
            EmailAccount ea = emailAccounts[i];
            Properties props = getProperties(ea);
            Session session = Session.getInstance(props, new MailAuthenticator(ea.getUserName(), ea.getPassword()));
            sessionList.add(session);
        }
    }

    private static Properties getProperties(EmailAccount emailAccount)
    {
        Properties props = new Properties(System.getProperties());
        props.put("mail.transport.protocol", emailAccount.getProtocol());
        props.put("mail.smtp.host", emailAccount.getHost());
        props.put("mail.smtp.port", emailAccount.getPort());
        props.put("mail.smtp.auth", Boolean.toString(emailAccount.isAuth()));
        props.put("userName", emailAccount.getUserName());
        return props;
    }

    private static int getCount() {
        synchronized (EmailSessionFactory.class) {
            return counter++ % 5;
        }
    }
}
