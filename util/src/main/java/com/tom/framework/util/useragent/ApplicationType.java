package com.tom.framework.util.useragent;

/**
 * ApplicationType
 *
 * @author Tom Zhao
 * @date 2016/3/17 0017
 */
public enum ApplicationType
{
    WEBMAIL("Webmail client"),
    UNKNOWN("unknown");

    private String name;

    private ApplicationType(String name) { this.name = name; }

    public String getName()
    {
        return this.name;
    }
}
