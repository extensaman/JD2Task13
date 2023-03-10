package by.academy.it.task13.configuration;

import by.academy.it.task13.AppSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfiguration {
    public static final String PROTOCOL_PROPERTY = "mail.transport.protocol";
    public static final String DEBUG_PROPERTY = "mail.debug";
    public static final String SMTP_AUTH_PROPERTY = "mail.smtp.auth";
    public static final String STARTTLS_PROPERTY = "mail.smtp.starttls.enable";

    private final AppSetting appSetting;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(appSetting.getMailHost());
        mailSender.setUsername(appSetting.getMailUsername());
        mailSender.setPassword(appSetting.getMailPassword());
        mailSender.setPort(appSetting.getMailPort());

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty(PROTOCOL_PROPERTY, appSetting.getMailProtocol());
        properties.setProperty(SMTP_AUTH_PROPERTY, appSetting.getMailSmtpAuth());
        properties.setProperty(STARTTLS_PROPERTY, appSetting.getMailSmtpStarttls());
        properties.setProperty(DEBUG_PROPERTY, appSetting.getMailDebug());

        return mailSender;
    }
}
