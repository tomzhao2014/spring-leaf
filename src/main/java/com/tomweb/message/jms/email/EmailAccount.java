package com.tomweb.message.jms.email;

/**
 * Created by tom on 15/12/28.
 */
public class EmailAccount
{
    private String protocol;
    private String host;
    private String port;
    private String userName;
    private String password;
    private boolean auth;

    public EmailAccount()
    {
    }

    public EmailAccount(String protocol, String host, String port, String userName, String password, boolean auth)
    {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.auth = auth;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuth() {
        return this.auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }
}
