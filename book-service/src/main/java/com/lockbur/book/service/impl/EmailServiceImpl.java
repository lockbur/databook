package com.lockbur.book.service.impl;

import com.lockbur.book.gateway.service.EmailService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {


    @Resource
    private JavaMailSender mailSender;

    @Resource
    private VelocityEngine velocityEngine;


    public void send(String toMail, String subject, String templateName, Map<String, Object> data)
            throws MessagingException, UnsupportedEncodingException {

        Assert.hasText(toMail);
        Assert.hasText(subject);
        Assert.hasText(templateName);

        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", data);

        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, false, "utf-8");

        messageHelper.setFrom(MimeUtility.encodeWord("系统邮件") + " <wang.kun@zhongfl.com>");
        messageHelper.setTo(toMail);
        messageHelper.setSubject(subject);
        messageHelper.setText(text, true);

        mailSender.send(mailMessage);
    }


}
