package com.tom.framework.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * 编码工具类
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 14:33
 */
public class EncodeUtils {
    //盐值
    public static final String SALT = "TeaMs@WEaVEr";
    //默认编码
    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    public static String hexEncode(byte[] input) {
        return Hex.encodeHexString(input);
    }

    public static byte[] hexDecode(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }

    }

    public static String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    public static String base64UrlSafeEncode(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }

    public static String urlEncode(String input) {
        return urlEncode(input, "UTF-8");
    }

    public static String urlEncode(String input, String encoding) {
        try {
            return URLEncoder.encode(input, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }

    }

    public static String urlDecode(String input) {
        return urlDecode(input, "UTF-8");
    }

    public static String urlDecode(String input, String encoding) {
        try {
            return URLDecoder.decode(input, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    public static String htmlEscape(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    public static String htmlUnescape(String htmlEscaped) {
        return StringEscapeUtils.escapeHtml4(htmlEscaped);
    }

    public static String xmlEscape(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    public static String xmlUnescape(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    public static String encode(String data) {
        if (data == null) return null;
        String dataHex = hexEncode(data.getBytes());
        String md5 = DigestUtils.md5Hex(data + "TeaMs@WEaVEr");
        return md5 + dataHex;
    }

    public static String decode(String key) {
        if (key == null) return null;
        String md5 = key.substring(0, 32);
        String dataHex = key.substring(32);
        String data = new String(hexDecode(dataHex));
        if (!DigestUtils.md5Hex(data + "TeaMs@WEaVEr").equals(md5)) {
            return null;
        }
        return data;
    }



}
