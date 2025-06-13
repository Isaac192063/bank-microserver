package com.bank.user.service.imp;

import com.bank.securityjwt.util.JwtUtil;
import com.bank.user.dto.MailDTO;
import com.bank.user.entity.AccountEntity;
import com.bank.user.repository.AccountRepository;
import com.bank.user.service.interfaces.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    public void sendMail(MailDTO emailDTO, String nameApp, String token) throws MessagingException {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailDTO.getAddressee());
            helper.setSubject(emailDTO.getAffair());

            //para enviar el texto directamente es setText

            Context context = new Context();
            context.setVariable("mensaje", emailDTO.getMessage());
            context.setVariable("date", LocalDateTime.now().toString());
            context.setVariable("nameApp", nameApp);
            context.setVariable("token", token);

            String contentHTML = templateEngine.process("email", context);

            helper.setText(contentHTML, true);

            javaMailSender.send(message);

        } catch (Exception exception) {
            throw new RuntimeException("Error al enviar el correo electronico: " + exception.getMessage(), exception);
        }
    }

    @Override
    public AccountEntity addAccount(AccountEntity account) {
        String token = jwtUtil.generateToken(account.getNameApp());
        account.setToken(token);

        try {
            this.sendMail(
                    new MailDTO(account.getEmail(), "Creación de sesión", account.getDescription()),
                    account.getNameApp(),
                    token);
            return accountRepository.save(account);

        } catch (Exception exception) {
            throw new RuntimeException("Error al enviar el correo electronico: " + exception.getMessage(), exception);
        }

    }
}
