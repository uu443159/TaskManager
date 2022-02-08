package com.stefanini.taskmanager.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class SmtpAuthenticator extends Authenticator {
    private static final String PROPERTIES_FILE_NAME = "gmailSettings.properties";

    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    public SmtpAuthenticator() {
        super();
    }

    Properties properties = propertiesLoader.getProperties(PROPERTIES_FILE_NAME);

    private final String user = properties.getProperty("USERNAME");;
    private final String pass = properties.getProperty("PASS");;

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = user;
        String password = pass;
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length() > 0)) {
            return new PasswordAuthentication(username, password);
        }
        return null;
    }
}