package com.vti.Service;

import com.vti.Entity.Account;
import com.vti.Repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IAccountRepository accountRepository;

    public void sendLinkChangePassword(String email){

        // tao ra token
        //  uuid(random) render token
        UUID uuid = UUID.randomUUID();
        Account acc = accountRepository.findByEmail(email);
        acc.setToken(uuid.toString());
        acc.setTokenCreated(LocalDateTime.now()); // set timeout token
        accountRepository.save(acc);

        // send email with token
        String subject = "Thay đổi password";
        String body = "Click vào link để thay đổi password: http://127.0.0.1:5500/changePassword.html?token="+ uuid.toString();
        senderMail(email, subject, body);
    }

        //send mail
    public void senderMail(String toEmail, String subject, String body) throws NullPointerException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("haunx.vjsc13@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail send Successfully" );
    }

        //change pass
    public void changePassword(String password, String token) throws Exception {
        Account account = accountRepository.findByToken(token);
        if (!checkTimeToken(account.getTokenCreated())){
            throw new Exception("Token het hạn");
        }
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        account.setToken("");
        accountRepository.save(account);
    }

    //check timeout token
    public boolean checkTimeToken(LocalDateTime tokenCreated){
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreated, now);
        return diff.toMinutes() <= 10;
    }
}
