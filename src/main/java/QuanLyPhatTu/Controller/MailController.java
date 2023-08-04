package QuanLyPhatTu.Controller;
import QuanLyPhatTu.Services.AuthenticationService;
import QuanLyPhatTu.Services.IMailClient;
import QuanLyPhatTu.Services.MailService;
import QuanLyPhatTu.Services.PhatTuServices;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private PhatTuServices phatTuServices;
    @Autowired
    private IMailClient iMailClient;
    @PutMapping(value = "doimatkhau")
    public boolean changePass(@RequestParam String email,@RequestParam String password)
    {
        return phatTuServices.ChangePassword(email, password);
    }
    @PostMapping(value = "create")
    public boolean create(
            @RequestParam String email
    ) throws MessagingException {
        return iMailClient.create(email);
    }
    @PutMapping ("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email,@RequestParam String otp)
    {
        return new ResponseEntity<>(mailService.forgotPassword(email,otp), HttpStatus.OK);
    }
    @PutMapping ("/set-password")
    public ResponseEntity<String> setPassword(@RequestParam String email,@RequestHeader String newPassword)
    {
        return new ResponseEntity<>(mailService.setPassword(email,newPassword), HttpStatus.OK);
    }
    @PutMapping("/regenerate -otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email)
    {
        return new ResponseEntity<>(mailService.regenerateOtp(email),HttpStatus.OK);
    }
}
