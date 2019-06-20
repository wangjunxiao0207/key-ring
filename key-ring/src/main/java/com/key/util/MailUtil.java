package com.key.util;

import com.key.beans.Mail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MailUtil {
    // 发送者的邮箱
    public static String from = "13579947882@163.com";
    // 连接的邮件服务器的端口， ssL协议的默认端口使465/994
    public static int port = 25;
    // 发电子邮件的邮件服务器, 默认网易的163邮箱
    public static String host = "smtp.163.com";
    // 邮箱密码
    public static String pass = "1106464969";
    // 账户昵称
    public static String nickname = "系统";

    public static boolean send(Mail mail) {
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceivers(), ","));
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + StringUtils.join(mail.getReceivers(), ",") + "失败", e);
            return false;
        }
    }

    public static void main(String[] args) {
        Mail mail = new Mail();
        mail.setSubject("测试标题");
        Set<String> receivers = new HashSet<>();
        receivers.add("2580183201@qq.com");
        receivers.add("13579947882@163.com");
        receivers.add("wangjunxiao0207@163.com");
        mail.setReceivers(receivers);
        mail.setMessage("测试内容");
        send(mail);
    }
}

