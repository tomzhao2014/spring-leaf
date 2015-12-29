package com.tomweb.message.jms.sms;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 15/12/28.
 */
public class EmayHttpClient
{
    private static final Logger logger = LoggerFactory.getLogger(EmayHttpClient.class);
    public static final String ENCODE = "utf-8";
    public static final int RESULT_SUCCESS_CODE = 0;
    public static final int RESULT_ERROR_CODE = -1;
    public static final int NET_CONNECTION_ERROR_CODE = -2;
    public static final int RESULT_PARSER_ERROR_CODE = -3;
    public static final String NET_CONNECTION_ERROR_MSG = "网络连接失败";
    public static final String RESULT_PARSER_ERROR_MSG = "返回结果解析错误";
    public static final String RESULT_ERROR_MSG = "返回结果错误";
    public static final String REGIST_URL = "http://sdkhttp.eucp.b2m.cn/sdkproxy/regist.action";
    public static final String SEND_SMS_URL = "http://sdkhttp.eucp.b2m.cn/sdkproxy/sendsms.action";
    public static final String LOGOUT_URL = "http://sdkhttp.eucp.b2m.cn/sdkproxy/logout.action";
    public static final String BALANCE_URL = "http://sdkhttp.eucp.b2m.cn/sdkproxy/querybalance.action";
    public static final String GETMO_URL = "http://sdkhttp.eucp.b2m.cn/sdkproxy/getmo.action";
    public static final String ASYSN_SEND_SMS_URL = "http://sdkhttp.eucp.b2m.cn/sdkproxy/asynsendsms.action";
    public static final String SEND_TIME_SMS_URL = "http://sdkhttp.eucp.b2m.cn/sdkproxy/sendtimesms.action";
    private final String cdkey = "3SDK-EMY-0130-NHVOO";
    private final String password = "189162";

    private HttpClient client = new HttpClient();

    public int registEx()
    {
        logger.info("Regist EmayHttpClient...");
        return getError("http://sdkhttp.eucp.b2m.cn/sdkproxy/regist.action", null);
    }

    public int logout()
    {
        return 0;
    }

    public String getBalance()
    {
        return getMessage("http://sdkhttp.eucp.b2m.cn/sdkproxy/querybalance.action", null);
    }

    public int getError(String url, Map<String, String> others)
    {
        Map params = new HashMap();
        params.put("cdkey", "3SDK-EMY-0130-NHVOO");
        params.put("password", "189162");
        if (others != null) {
            params.putAll(others);
        }
        String rs = postURL(url, params);
        if (rs == null) {
            logger.warn("网络连接失败");
            return -2;
        }

        return 0;
    }

    public boolean asynSendSMS(String phone, String msg)
    {
        Map params = new HashMap();
        params.put("phone", phone);
        params.put("message", msg);
        return getError("http://sdkhttp.eucp.b2m.cn/sdkproxy/asynsendsms.action", params) == 0;
    }

    public int sendSMS(String[] phones, String msg)
    {
        Map params = new HashMap();
        String phone = "";
        for (String p : phones) {
            phone = phone + p + ",";
        }
        int length = phone.length();
        if (phone.length() > 11) {
            phone = phone.substring(0, length - 1);
        }
        params.put("phone", phone);
        params.put("message", msg);
        return getError("http://sdkhttp.eucp.b2m.cn/sdkproxy/sendsms.action", params);
    }

    public String getMessage(String url, Map<String, String> others)
    {
        Map params = new HashMap();
        params.put("cdkey", "3SDK-EMY-0130-NHVOO");
        params.put("password", "189162");
        if (others != null) {
            params.putAll(others);
        }
        String rs = postURL(url, params);
        if (rs == null) {
            logger.warn("网络连接失败");
            return String.valueOf(-2);
        }
        return rs;
    }

    public String postURL(String url, Map<String, String> params)
    {
        PostMethod postMethod = new PostMethod(url);
        HttpClientParams hcp = new HttpClientParams();
        hcp.setHttpElementCharset("utf-8");
        hcp.setContentCharset("utf-8");
        this.client.setParams(hcp);
        postMethod.setRequestHeader("Connection", "close");
        int size = params.size();
        if (size > 0) {
            NameValuePair[] postData = new NameValuePair[size];
            int i = 0;
            for (Map.Entry entry : params.entrySet()) {
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                postData[i] = new NameValuePair(key, value);
                i++;
            }
            postMethod.addParameters(postData);
            try {
                int state = this.client.executeMethod(postMethod);
                if (200 == state) {
                    String rs = postMethod.getResponseBodyAsString();
                    if (rs != null) {
                        int index = rs.indexOf("?>");
                        if (index != -1) {
                            rs = rs.substring(index + 2);
                        }
                    }
                    return rs;
                }
            } catch (HttpException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                postMethod.releaseConnection(); } postMethod.releaseConnection();
        }

        return null;
    }

    public int sendScheduledSMS(String[] phones, String msg, String time)
    {
        Map params = new HashMap();
        String phone = "";
        for (String p : phones) {
            phone = phone + p + ",";
        }
        int length = phone.length();
        if (phone.length() > 11) {
            phone = phone.substring(0, length - 1);
        }
        params.put("phone", phone);
        params.put("message", msg);
        return getError("http://sdkhttp.eucp.b2m.cn/sdkproxy/sendtimesms.action", params);
    }

    public static void main(String[] args)
    {
        EmayHttpClient client = new EmayHttpClient();

        client.sendSMS(new String[] { "15800581845", "18616637840" },
                "【泛微】xxx同意了您加入xxxx团队协作空间的申请,请登录www.eteams.cn开始使用。");

        String balance = client.getBalance();
        logger.info("短信余额为:" + balance);
    }
}