package QuanLyPhatTu.Services;

import QuanLyPhatTu.DTO.EmailDto;
import jakarta.mail.MessagingException;

public interface IMailService
{
    void sendHtmlMail(EmailDto dataMail, String templateName) throws MessagingException;
}
