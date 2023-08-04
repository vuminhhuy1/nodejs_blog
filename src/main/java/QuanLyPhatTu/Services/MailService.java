package QuanLyPhatTu.Services;

import QuanLyPhatTu.DTO.EmailDto;
import QuanLyPhatTu.EmailUtil.EmailUtil;
import QuanLyPhatTu.EmailUtil.OtpUtil;
import QuanLyPhatTu.Repository.PhatTuRepo;
import QuanLyPhatTu.models.PhatTu;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MailService implements IMailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PhatTuRepo phatTuRepo;
    private final EmailUtil emailUtil;
    private final OtpUtil otpUtil;
    @Override
    public void sendHtmlMail(EmailDto dataMail, String templateName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        Context context = new Context();
        context.setVariables(dataMail.getProps());
        String html = templateEngine.process(templateName, context);
        helper.setTo(dataMail.getTo());
        helper.setSubject(dataMail.getSubject());
        helper.setText(html, true);
        mailSender.send(message);
    }


    public String forgotPassword(String email, String otp) {
        PhatTu user = phatTuRepo.findByEmail(email)
                .orElseThrow(
                        ()->new RuntimeException("User not found with this email"+email));
        try {
            if (user.getOtp().equals(otp) && Duration.between(user.getLocalDateTime(), LocalDateTime.now()).getSeconds()<(1*60)){
                user.setActive(true);
                phatTuRepo.save(user);
            }
            emailUtil.sendSetPasswordEmail(email);

        }catch (MessagingException e)
        {
            throw new RuntimeException("Unable to send set password email please try again");
        }
        return "Please check your email to set new password to your account ";
    }

    public String setPassword(String email,String newPassword) {
        PhatTu user = phatTuRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found with this email"+email));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setRootpassword(newPassword);
        phatTuRepo.save(user);
        return "new password seccessfully login with new password";
    }
    public String regenerateOtp(String email) {
        PhatTu user = phatTuRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found with this email"+email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email,otp);
        }catch (MessagingException e)
        {
            throw  new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setLocalDateTime(LocalDateTime.now());
        phatTuRepo.save(user);
        return "Email sent....please verify account within 1 minute";
    }
}
