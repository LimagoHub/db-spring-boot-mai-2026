package de.db.webapp.domain.config;


import de.db.webapp.YamlPropertySourceFactory;
import de.db.webapp.domain.EmailService;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="mail")
@PropertySource(value = "classpath:mail.yaml",factory = YamlPropertySourceFactory.class)
@Setter
public class MailConfig {
    private  String host;
    private  String user;
    private  String password;
    private  String protocol;


    @Bean
    public EmailService getEmailService() {
        return new EmailService(host,user,password,protocol);
    }
}
