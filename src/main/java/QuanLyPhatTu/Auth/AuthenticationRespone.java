package QuanLyPhatTu.Auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AuthenticationRespone {
    private String email;
    private String token;
}
