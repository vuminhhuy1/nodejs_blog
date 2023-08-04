package QuanLyPhatTu.Services;

import QuanLyPhatTu.DTO.EmailDto;
import QuanLyPhatTu.Repository.PhatTuRepo;
import QuanLyPhatTu.models.PhatTu;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IMailClient implements IMail{
    @Autowired
    private MailService mailService;
    @Autowired
    private PhatTuRepo phatTuRepo;

    @Override
    public Boolean create(String emailAdress) throws MessagingException {
        PhatTu pt = new PhatTu();
        List<PhatTu> ptlist = phatTuRepo.findAll();
        for (PhatTu i : ptlist) {
            if (i.getEmail().equals(emailAdress)) {
                EmailDto dataMail = new EmailDto();
                dataMail.setTo(emailAdress);
                dataMail.setSubject("PHỤC HỒI MẬT KHẨU");
                Map<String, Object> props = new HashMap<>();
                props.put("name", i.getPhapdanh());
                props.put("username", i.getEmail());
                props.put("password", i.getRootpassword());
                dataMail.setProps(props);
                mailService.sendHtmlMail(dataMail, "client");
                return true;
            }

        }
        return false;
    }

}
