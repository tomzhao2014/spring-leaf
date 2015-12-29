package com.tomweb.message.sms;

/**
 * SmsService
 *
 * @author Tom Zhao
 * @date 2015/12/28 0028
 * 短信接口
 */
public abstract interface SmsService
{
    public abstract void sendInviteToRemind(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString);

    public abstract void sendDueToRemind(String paramString1, String paramString2, String[] paramArrayOfString);

    public abstract void sendCheckCodeToRemind(String paramString1, String paramString2, String[] paramArrayOfString);

    public abstract void sendApplyToRemind(String paramString1, String paramString2, String[] paramArrayOfString);

    public abstract void sendAgreeToRemind(String paramString1, String paramString2, String[] paramArrayOfString);

    public abstract void sendPwdResetToRemind(String paramString1, String paramString2, String[] paramArrayOfString);

    public abstract void regPhoneCheck(String paramString1, String paramString2);

    public abstract void sendRehireMessages(String paramString1, String paramString2);
}
