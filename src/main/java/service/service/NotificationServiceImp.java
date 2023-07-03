package service.service;

import service.model.request.NotificationRequest;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService{

    private final JavaMailSender javaMailSender;
    @SneakyThrows
    @Override
    public void sendOTP(NotificationRequest notificationRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setText(notificationRequest.getMessage());
        helper.setTo(notificationRequest.getTo());
        javaMailSender.send(mimeMessage);
    }
}
