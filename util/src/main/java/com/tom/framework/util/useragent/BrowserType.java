package com.tom.framework.util.useragent;

/**
 * BrowserType
 *
 * @author Tom Zhao
 * @date 2016/3/17 0017
 */
public enum BrowserType
{
    WEB_BROWSER("Browser"),

    MOBILE_BROWSER("Browser (mobile)"),

    TEXT_BROWSER("Browser (text only)"),

    EMAIL_CLIENT("Email Client"),

    ROBOT("Robot"),

    TOOL("Downloading tool"),

    APP("Application"),
    UNKNOWN("unknown");

    private String name;

    private BrowserType(String name) { this.name = name; }

    public String getName()
    {
        return this.name;
    }
}
