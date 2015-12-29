package com.tomweb.message.jms.email;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by tom on 15/12/28.
 */

@Transactional
public abstract interface EmailSendFailureHandler extends Serializable
{
    public abstract void onSendFailue(Object paramObject, String paramString);
}
