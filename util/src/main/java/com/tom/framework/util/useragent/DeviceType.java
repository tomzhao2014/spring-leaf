package com.tom.framework.util.useragent;

/**
 * DeviceType
 *
 * @author Tom Zhao
 * @date 2016/3/17 0017
 */
public enum DeviceType
{
    COMPUTER("Computer"),

    MOBILE("Mobile"),

    TABLET("Tablet"),

    GAME_CONSOLE("Game console"),

    DMR("Digital media receiver"),

    UNKNOWN("Unknown");

    String name;

    private DeviceType(String name) { this.name = name; }

    public String getName()
    {
        return this.name;
    }
}
