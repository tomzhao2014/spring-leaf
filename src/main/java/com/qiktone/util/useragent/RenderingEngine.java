package com.qiktone.util.useragent;

/**
 * RenderingEngine
 *
 * @author Tom Zhao
 * @date 2016/3/17 0017
 */
public enum RenderingEngine
{
    TRIDENT("Trident"),

    WORD("Microsoft Office Word"),

    GECKO("Gecko"),

    WEBKIT("WebKit"),

    PRESTO("Presto"),

    MOZILLA("Mozilla"),

    KHTML("KHTML"),

    OTHER("Other");

    String name;

    private RenderingEngine(String name) { this.name = name;
    }
}
