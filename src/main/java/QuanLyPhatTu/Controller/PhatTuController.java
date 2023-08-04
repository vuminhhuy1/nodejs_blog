package QuanLyPhatTu.Controller;
import QuanLyPhatTu.Auth.AuthenticationRequest;
import QuanLyPhatTu.Repository.PhatTuRepo;
import QuanLyPhatTu.Services.JwtService;
import QuanLyPhatTu.Services.PhatTuServices;
import QuanLyPhatTu.models.PhatTu;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/auth")
public class PhatTuController {
@Autowired
private PhatTuServices phatTuServices;
@Autowired
    private PhatTuRepo phatTuRepo;
@Autowired
 private AuthenticationManager authenticationManager;
@Autowired
private JwtService jwtService;
    @PostMapping("/new")
    public String addNewUser(@RequestBody String pt) {
             Gson gson = new Gson();
             PhatTu phatTu = gson.fromJson(pt,PhatTu.class);
             return phatTuServices.addUser(phatTu);
    }

    @PostMapping(value = "/authenticate")
    public String authenticateAndGetToken(@RequestBody String authenticationRequest){
         Gson gson = new Gson();
         AuthenticationRequest authenticationRequest1 = gson.fromJson(authenticationRequest,AuthenticationRequest.class);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest1.getEmail(),authenticationRequest1.getPassword()));
        if(authentication.isAuthenticated())
        {
            jwtService.addTokenDb(authenticationRequest1.getEmail());
            return jwtService.generateToken(authenticationRequest1.getEmail());
        }else{

            throw new UsernameNotFoundException("Không tìm thấy ");
        }
    }
@GetMapping("/showpt")
public List<PhatTu> showphattu()
{
    return phatTuServices.showphattu();
}
@PutMapping(value = "/suaphattu",produces = MediaType.APPLICATION_JSON_VALUE)
public String remakePhatTu( @RequestBody String phatTu)
{
    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
        }
    }).create();
    PhatTu phatTu1 = gson.fromJson(phatTu,PhatTu.class);
    return phatTuServices.remakePhatTu(phatTu1);
}
    @DeleteMapping(value = "/xoaphattu")
    public String removept(@RequestParam int idphattu)
    {
        return phatTuServices.removePhatTu(idphattu);
    }
@GetMapping (value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    List<PhatTu> search(@RequestParam String ten)
{
    return phatTuServices.search(ten);
}
    @GetMapping(value = "/Pageable/{pageNumber}/{pageSize}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PhatTu> phattuPage(@PathVariable Integer pageNumber,@PathVariable Integer pageSize)
    {
        return phatTuServices.getphattupage(pageNumber,pageSize);
    }
}
