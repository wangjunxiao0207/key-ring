package com.key.service.impl;

import com.google.common.collect.Sets;
import com.key.beans.Mail;
import com.key.service.SysMailService;
import com.key.util.MailUtil;
import com.key.util.PropertiesUtil;
import com.key.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 用来发送邮件服务
 */
@Service
public class SysMailServiceImpl implements SysMailService {

    private static Mail simpleMailTemplate = new Mail();
    private static boolean isEnable = true;

    static {
        String subject = PropertiesUtil.getProperty("email.subject","系统未知错误");
        String receiversStr = PropertiesUtil.getProperty("email.receivers","abc@gmail.com");
        String enable = PropertiesUtil.getProperty("email.enable","true");

        List<String> receiversList = StringUtil.split(receiversStr, ",");
        Set<String> receivers = Sets.newHashSet();
        receivers.addAll(receiversList);
        if(StringUtil.equals(enable, "true")) {
            isEnable = true;
        } else {
            isEnable = false;
        }

        simpleMailTemplate.setSubject(subject);
        simpleMailTemplate.setReceivers(receivers);
    }

    /**
     * 发送简单邮件
     * @param message 邮件内容信息
     */
    public void sendSimpleMail(String message) {
        if(!isEnable) return;
        simpleMailTemplate.setMessage(message);
        MailUtil.send(simpleMailTemplate);
    }
}
