package com.qiktone.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 18:55
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils
{
    private static final Log logger = LogFactory.getLog(StringUtils.class);
    public static final String DELIMITER = ":";
    private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String encodeCookie(String[] cookieTokens)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cookieTokens.length; i++) {
            sb.append(cookieTokens[i]);

            if (i < cookieTokens.length - 1) {
                sb.append(":");
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encodeBase64(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String[] decodeCookie(String cookieValue)
    {
        for (int j = 0; j < cookieValue.length() % 4; j++) {
            cookieValue = new StringBuilder().append(cookieValue).append("=").toString();
        }

        if (!Base64.isBase64(cookieValue.getBytes())) {
            logger.error(new StringBuilder().append("Cookie token was not Base64 encoded; value was '").append(cookieValue).append("'").toString());
            return null;
        }

        String cookieAsPlainText = new String(Base64.decodeBase64(cookieValue.getBytes()));

        String[] tokens = org.springframework.util.StringUtils.delimitedListToStringArray(cookieAsPlainText, ":");

        if (((tokens[0].equalsIgnoreCase("http")) || (tokens[0].equalsIgnoreCase("https"))) && (tokens[1].startsWith("//")))
        {
            String[] newTokens = new String[tokens.length - 1];
            newTokens[0] = new StringBuilder().append(tokens[0]).append(":").append(tokens[1]).toString();
            System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
            tokens = newTokens;
        }
        return tokens;
    }

    public static String encodeMd5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String getRandomUUID()
    {
        UUID id = UUID.randomUUID();
        long mostSigBits = id.getMostSignificantBits();
        long leastSigBits = id.getLeastSignificantBits();
        return new StringBuilder().append(digits(mostSigBits >> 32, 8)).append(digits(mostSigBits >> 16, 4)).append(digits(mostSigBits, 4)).append(digits(leastSigBits >> 48, 4)).append(digits(leastSigBits, 12)).toString();
    }

    private static String digits(long val, int digits)
    {
        long hi = 1L << digits * 4;
        return Long.toHexString(hi | val & hi - 1L).substring(1);
    }

    public static String getRandomString(int length)
    {
        int size = length < 0 ? 1 : length;
        String text = "abcdefghijklmnopqrstuvwxyz1234567890";
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String c = String.valueOf(chars[new java.util.Random().nextInt(chars.length)]);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getRandomDigit(int length)
    {
        int size = length < 0 ? 1 : length;
        String text = "1234567890";
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String c = String.valueOf(chars[new java.util.Random().nextInt(chars.length)]);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getDownloadFilename(String fileName, String userAgent) {
        if (fileName == null) {
            return null;
        }
        try
        {
            if (userAgent.indexOf("msie") != -1) {
                fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
            } else if ((userAgent.indexOf("applewebkit") != -1) && (userAgent.indexOf("chrome") != -1)) {
                fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
            } else if ((userAgent.indexOf("safari") != -1) && (userAgent.indexOf("chrome") == -1)) {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            } else if (userAgent.indexOf("mozilla") != -1) {
                String chinese = "[一-龥]";
                Pattern pat = Pattern.compile(chinese);
                Matcher matcher = pat.matcher(fileName);
                if (matcher.find()) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                    fileName = new StringBuilder().append("\"").append(fileName).append("\"").toString();
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return fileName.replace(",", "");
    }

    public static String getSinglePinYin(String paramString)
    {
        if (paramString == null) {
            logger.warn("转换拼音时传入的参数为null");
            return "";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = paramString.trim().toLowerCase().toCharArray();
        String output = "";
        try
        {
            for (char element : input)
                if (Character.toString(element).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(element, format);
                    output = new StringBuilder().append(output).append(temp[0]).toString();
                } else {
                    output = new StringBuilder().append(output).append(Character.toString(element)).toString();
                }
        }
        catch (BadHanyuPinyinOutputFormatCombination e)
        {
            logger.error(e.getMessage(), e);
        }
        return output;
    }

    public static String getPinYin(String string)
    {
        if (string == null) {
            return null;
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        String[] stringArray = { "" };
        String[] returnArray = { "" };
        try {
            returnArray = getPinYin(stringArray, string, format);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        StringBuffer buffer = new StringBuffer();
        for (String pinyin : returnArray) {
            buffer.append(",").append(pinyin);
        }
        return buffer.toString().substring(1);
    }

    private static String[] getPinYin(String[] inputArray, String string, HanyuPinyinOutputFormat format)
            throws Exception
    {
        if ((string == null) || (string.length() == 0)) {
            return inputArray;
        }
        char c = string.charAt(0);
        String[] returnArray = null;
        int index = 0;

        if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
            List pinyinList = getUnitPinYin(c, format);
            int lenght = pinyinList.size() * inputArray.length;
            returnArray = new String[lenght];
            //todo:该地方有编译错误，先做备注，后续处理
           /* for ( String inputPinyin:inputArray) {
                for (String pinyin: pinyinList) {
                    returnArray[index] = new StringBuilder().append(inputPinyin).append(pinyin).toString();
                    index++;
                }
            }*/
        }else {
            returnArray = new String[inputArray.length];
            for (String inputPinyin : inputArray) {
                returnArray[index] = new StringBuilder().append(inputPinyin).append(Character.toString(c)).toString();
                index++;
            }
        }

        return getPinYin(returnArray, string.substring(1), format);
    }

    private static List<String> getUnitPinYin(char c, HanyuPinyinOutputFormat format) throws Exception {
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
        List pinyinList = new ArrayList();
        for (String string : pinyinArray) {
            if (!pinyinList.contains(string)) {
                pinyinList.add(string);
            }
        }

        return pinyinList;
    }

    public static String toUnicode(String theString)
    {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = 2147483647;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            if ((aChar < ' ') || (aChar > '~')) {
                outBuffer.append('\\');
                outBuffer.append('u');
                outBuffer.append(toHex(aChar >> '\f' & 0xF));
                outBuffer.append(toHex(aChar >> '\b' & 0xF));
                outBuffer.append(toHex(aChar >> '\004' & 0xF));
                outBuffer.append(toHex(aChar & 0xF));
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    public String fromUnicode(char[] in, int off, int len, char[] convtBuf)
    {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
                newLen = 2147483647;
            }
            convtBuf = new char[newLen];
        }

        char[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            char aChar = in[(off++)];
            if (aChar == '\\') {
                aChar = in[(off++)];
                if (aChar == 'u')
                {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[(off++)];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - 48;
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 97;
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 65;
                                break;
                            case ':':
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                            case '?':
                            case '@':
                            case 'G':
                            case 'H':
                            case 'I':
                            case 'J':
                            case 'K':
                            case 'L':
                            case 'M':
                            case 'N':
                            case 'O':
                            case 'P':
                            case 'Q':
                            case 'R':
                            case 'S':
                            case 'T':
                            case 'U':
                            case 'V':
                            case 'W':
                            case 'X':
                            case 'Y':
                            case 'Z':
                            case '[':
                            case '\\':
                            case ']':
                            case '^':
                            case '_':
                            case '`':
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[(outLen++)] = (char)value;
                    continue;
                }if (aChar == 't')
                    aChar = '\t';
                else if (aChar == 'r')
                    aChar = '\r';
                else if (aChar == 'n')
                    aChar = '\n';
                else if (aChar == 'f') {
                    aChar = '\f';
                }
                out[(outLen++)] = aChar; continue;
            }

            out[(outLen++)] = aChar;
        }

        return new String(out, 0, outLen);
    }

    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    public static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }
}
