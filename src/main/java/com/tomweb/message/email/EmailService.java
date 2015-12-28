package com.tomweb.message.email;

import java.util.Map;

/**
 * EmailService
 *
 * @author Tom Zhao
 * @date 2015/12/28 0028
 */
public abstract interface EmailService {
    public abstract void sendValidateEmail(String paramString, Map<String, String> paramMap);

    public abstract void sendRehireEmail(String paramString, Map<String, String> paramMap);

    public abstract void sendCaptchaEmail(String paramString, Map<String, String> paramMap);

    public abstract void sendInviteMail(String paramString, Map<String, String> paramMap);
}
