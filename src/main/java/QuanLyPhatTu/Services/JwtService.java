package QuanLyPhatTu.Services;
import QuanLyPhatTu.Auth.AuthenticationRespone;
import QuanLyPhatTu.Repository.PhatTuRepo;
import QuanLyPhatTu.Repository.TokenRepo;
import QuanLyPhatTu.models.PhatTu;
import QuanLyPhatTu.models.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
@Component
public class JwtService {
    AuthenticationRespone authenticationRespone =  new AuthenticationRespone();
    @Autowired
    private TokenRepo tokenRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(String email){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,email);
    }

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
    public void addTokenDb(String email) {
        String token = generateToken(email);
        List<Token> token1 = tokenRepo.findAll();
        Optional<PhatTu> phattu1 = phatTuRepo.findByEmail(email);
        PhatTu phattu = phattu1.get();
        for(Token i:token1)
        {
            if(i.getPhattuid() == phattu.getPhattuid())
            {
                i.setStoken(token);
                tokenRepo.save(i);
                return;
            }
        }
        Token tknew = new Token();
        tknew.setStoken(token);
        tknew.setPhatTu(phattu);
        tokenRepo.save(tknew);
    }
    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
