package com.stefanini.taskmanager.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class JavaMailUtil {

    private static final String PROPERTIES_FILE_NAME = "gmailSettings.properties";

    private PropertiesLoader propertiesLoader;
    Properties properties;

    private String host;
    private String port;
    private String ssl;
    private String auth;

    public JavaMailUtil() {
        initProperties();
    }

    private void initProperties() {
        propertiesLoader = new PropertiesLoader();
        properties = propertiesLoader.getProperties(PROPERTIES_FILE_NAME);

        host = properties.getProperty("HOST");
        port = properties.getProperty("SSL_PORT");
        ssl = properties.getProperty("SSL_ENABLE");
        auth = properties.getProperty("AUTH");
    }

    public Properties setProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", ssl);
        properties.put("mail.smtp.auth", auth);

        return properties;
    }

    public void sendEmail(Session session, String to, String subject, String body) {

        MimeMessage message = prepareMessage(session, to, subject, body);

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private static MimeMessage prepareMessage (Session session, String to, String subject, String body) {
        MimeMessage message = new MimeMessage(session);

        try {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return message;
    }
}


