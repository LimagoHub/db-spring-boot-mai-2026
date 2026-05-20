package de.db.webapp.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailService {

    private final String host;
    private final String user;
    private final String password;
    private final String protocol;


    public void sendEmail(String email, String subject, String body) {
        System.out.println("Sending email to " + email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmailService{");
        sb.append("host='").append(host).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", protocol='").append(protocol).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
