package com.qiktone.util.useragent;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 18:44
 */


public class UserAgentUtils
{
    public static boolean isComputer(HttpServletRequest request)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        DeviceType deviceType = userAgent.getOperatingSystem().getDeviceType();

        return DeviceType.COMPUTER.equals(deviceType);
    }

    public static boolean isOldIE(HttpServletRequest request)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        Version version = userAgent.getBrowserVersion();
        return (browser.getGroup().equals(Browser.IE)) && (Integer.parseInt(version.getMajorVersion()) < 9);
    }
}
