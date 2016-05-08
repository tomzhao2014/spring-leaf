package com.tom.framework.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 19:12
 */
public class HttpUtils
{
    public static final int PAGE_MAX_LEN = 1048576;
    private static Log logger = LogFactory.getLog(HttpUtils.class);

    public static String httpGet(String url, Map<String, String> params)
    {
        HttpClient httpClient = new HttpClient();
        GetMethod method = null;
        try {
            if ((params == null) || (params.size() <= 0)) {
                method = new GetMethod(url);
            } else {
                String param = buildParam(params);
                method = new GetMethod(new StringBuilder().append(url).append("?").append(param).toString());
            }
            int statusCode = httpClient.executeMethod(method);
            if ((statusCode == 400) || (statusCode == 500)) {
                throw new HttpException(new StringBuilder().append("Method failed: ").append(method.getStatusLine()).toString());
            }
            InputStream inputStream = method.getResponseBodyAsStream();
            if (null == inputStream) {
                return null;
            }
            String str1 = getContent(inputStream);
            return str1;
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }

        return null;
    }

    public static String httpPost(String url, Map<String, String> params)
    {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        if ((params != null) && (params.size() > 0)) {
            NameValuePair[] nameValuePairs = new NameValuePair[params.size()];
            int i = 0;
            for (Map.Entry entry : params.entrySet()) {
                nameValuePairs[i] = new NameValuePair((String)entry.getKey(), (String)entry.getValue());
                i++;
            }
            postMethod.setRequestBody(nameValuePairs);
        }
        try {
            int statusCode = client.executeMethod(postMethod);
            if ((statusCode != 200) && (statusCode != 201)) {
                throw new HttpException(new StringBuilder().append("Method failed: ").append(postMethod.getStatusLine()).toString());
            }
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            if (null == inputStream) {

                return null;
            }
            String content  = getContent(inputStream);
            return content;
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        } finally {
            postMethod.releaseConnection();
        }
        return null;
    }

    public static boolean httpDelete(String url)
    {
        HttpClient client = new HttpClient();
        DeleteMethod deleteMethod = new DeleteMethod(url);
        deleteMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        try
        {
            int statusCode = client.executeMethod(deleteMethod);
            if (statusCode != 200) {
                throw new HttpException(new StringBuilder().append("Method failed: ").append(deleteMethod.getStatusLine()).toString());
            }
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        } finally { deleteMethod.releaseConnection(); }
    }

    private static String buildParam(Map<String, String> params)
            throws UnsupportedEncodingException
    {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry entry : params.entrySet()) {
            sb.append((String)entry.getKey());
            sb.append("=");
            sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
            sb.append("&");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    public static String getContent(InputStream is)
            throws IOException
    {
        StringBuilder out = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line = null;
        try {
            while ((line = reader.readLine()) != null)
                out.append(line);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toString();
    }
}
