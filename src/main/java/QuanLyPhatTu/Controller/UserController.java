package QuanLyPhatTu.Controller;

import QuanLyPhatTu.Repository.PhatTuRepo;
import QuanLyPhatTu.models.PhatTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
@Autowired
private PhatTuRepo phatTuRepo;
    @GetMapping("/Login")
    public List<PhatTu> getAllPhatTu(){
        List<PhatTu> list = new ArrayList<>();
         for (PhatTu pt: phatTuRepo.findAll()
             ) {
            list.add(pt);
        }
         return list;
    }

}
