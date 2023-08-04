package QuanLyPhatTu.Services;

import jakarta.mail.MessagingException;

public interface IMail {
    Boolean create(String emailAdress) throws MessagingException;
}
