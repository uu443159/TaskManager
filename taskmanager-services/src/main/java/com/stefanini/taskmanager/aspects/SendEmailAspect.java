package com.stefanini.taskmanager.aspects;

import com.stefanini.taskmanager.annotations.SendEmail;
import com.stefanini.taskmanager.entity.Task;
import com.stefanini.taskmanager.entity.User;
import com.stefanini.taskmanager.utils.JavaMailUtil;
import com.stefanini.taskmanager.utils.ReflectionUtil;
import com.stefanini.taskmanager.utils.SmtpAuthenticator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.mail.Session;
import java.util.Properties;

@Aspect
public class SendEmailAspect {

    @Pointcut("@annotation(com.stefanini.taskmanager.annotations.SendEmail)")
    public void sendEmailMethods() {}

    @AfterReturning("sendEmailMethods()")
    public void sendEmail(JoinPoint joinPoint) {
        SendEmail sendEmailAnnotation = null;
        try {
            sendEmailAnnotation = ReflectionUtil.getMethodAnnotation(joinPoint, SendEmail.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        switch (sendEmailAnnotation.event()) {
            case USER_CREATED: {
                User user = (User) joinPoint.getArgs()[0];

                doSendEmail(sendEmailAnnotation.to(), sendEmailAnnotation.subject(), getUserCreatedMsg(user));
                break;
            }
            case TASK_CREATED: {
                Task task = (Task) joinPoint.getArgs()[0];

                doSendEmail(sendEmailAnnotation.to(), sendEmailAnnotation.subject(), getTaskAssignedMsg(task));
                break;
            }
        }

    }


    private void doSendEmail(String to, String subject, String message) {
        JavaMailUtil emailUtil = new JavaMailUtil();
        Properties properties = emailUtil.setProperties();

        SmtpAuthenticator authenticator = new SmtpAuthenticator();

        Session session = Session.getInstance(properties, authenticator);
        emailUtil.sendEmail(session, to, subject, message);

    }

    private String getUserCreatedMsg(User user) {

        String userCreatedMsg = String.format("User %1$s / %2$s identified by %3$s has been created", user.getFirstName(), user.getLastName(), user.getUserName());
        StringBuilder sb = new StringBuilder(userCreatedMsg);

        user.getTaskList().forEach((task) -> {
            String taskAssignedMsg = getTaskAssignedMsg(task);
            sb.append("\n");
            sb.append(taskAssignedMsg);
        });

        return sb.toString();
    }

    private String getTaskAssignedMsg(Task task) {
        return String.format("Task %1$s: %2$s has been assigned to %3$s", task.getTitle(), task.getDescription(), task.getUserName());
    }
}
