package QuanLyPhatTu.Controller;

import QuanLyPhatTu.Services.DondangkyService;
import QuanLyPhatTu.models.DonDangKy;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class DonDangKyController {
    @Autowired
    private DondangkyService dondangkyService;
    @PostMapping(value = "/themddk")
    public String themdondangky(@RequestBody String donDangKy)
    {
        Gson gson = new Gson();
        DonDangKy donDangKy1 = gson.fromJson(donDangKy,DonDangKy.class);
        return dondangkyService.themdondangki(donDangKy1);
    }
    @DeleteMapping("/xoadondangki")
    public String xoadondangki(@RequestParam int dondangkyid)
    {
        return dondangkyService.xoadondangki(dondangkyid);
    }
    @PutMapping("/duyetdon")
    public String duyetdon(@RequestParam int donid,@RequestParam boolean xacnhanduyet)
    {
        return dondangkyService.duyetdon(donid, xacnhanduyet);
    }
}
