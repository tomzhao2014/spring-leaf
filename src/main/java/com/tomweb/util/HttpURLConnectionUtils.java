package com.tomweb.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tom on 15/12/6.
 */
public class HttpURLConnectionUtils {
    /**
     * @param urlStr
     *            请求的地址
     * @param content
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    public static String sendRequest(String urlStr, String content, String encoding, String method ) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod(method);// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            if (StringUtils.isNotBlank(content)) {
                DataOutputStream out = new DataOutputStream(connection
                        .getOutputStream());// 打开输出流往对端服务器写数据
                out.write(content.getBytes(encoding));// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
                out.flush();// 刷新
                out.close();// 关闭输出流
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
           } finally {
            if (connection != null) {
                try {
                    connection.disconnect();// 关闭连接
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
