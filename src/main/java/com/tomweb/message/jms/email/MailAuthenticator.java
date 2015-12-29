package com.tomweb.message.jms.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by tom on 15/12/28.
 */
public class MailAuthenticator extends Authenticator
{
    private String userName;
    private String passWord;

    public MailAuthenticator()
    {
    }

    public MailAuthenticator(String userName, String passWord)
    {
        this.userName = userName;
        this.passWord = passWord;
    }

    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(this.userName, this.passWord);
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}