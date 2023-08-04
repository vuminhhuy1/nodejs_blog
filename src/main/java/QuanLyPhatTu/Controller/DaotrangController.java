package QuanLyPhatTu.Controller;

import QuanLyPhatTu.Services.DaoTrangService;
import QuanLyPhatTu.models.DaoTrang;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class DaotrangController {
    @Autowired
    private DaoTrangService daoTrangService;
    @RequestMapping(value = "/adddaotrang",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String adddaotrang(@RequestBody String daotrang)
    {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        DaoTrang daoTrang1 = gson.fromJson(daotrang,DaoTrang.class);
        return daoTrangService.addDaoTrang(daoTrang1);
    }
    @PutMapping(value = "/suadaotrang",produces = MediaType.APPLICATION_JSON_VALUE)
    public String remakedaotrang( @RequestBody String daotrang)
    {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        DaoTrang daoTrang1 = gson.fromJson(daotrang,DaoTrang.class);
        return daoTrangService.remake(daoTrang1);
    }
    @DeleteMapping(value = "/xoadaotrang",produces = MediaType.APPLICATION_JSON_VALUE)
    public String removechua(@RequestParam int iddaotrang)
    {
        return daoTrangService.remove(iddaotrang);
    }
    @GetMapping (value = "search1",produces = MediaType.APPLICATION_JSON_VALUE)
    List<DaoTrang> search(@RequestParam String noitochuc)
    {
        return daoTrangService.timkiemtheonoitochuc(noitochuc);
    }
    @GetMapping(value = "/Pageable1/{pageNumber}/{pageSize}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<DaoTrang> phattuPage(@PathVariable Integer pageNumber, @PathVariable Integer pageSize)
    {
        return daoTrangService.getphattupage(pageNumber,pageSize);
    }
}
