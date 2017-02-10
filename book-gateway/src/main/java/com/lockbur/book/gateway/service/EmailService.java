package com.lockbur.book.gateway.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by wangkun23 on 2017/2/10.
 */
public interface EmailService {

    public void send(String toMail, String subject, String templateName, Map<String, Object> data)
            throws MessagingException, UnsupportedEncodingException;
}
