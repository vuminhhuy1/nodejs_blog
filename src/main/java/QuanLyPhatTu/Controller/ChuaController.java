package QuanLyPhatTu.Controller;

import QuanLyPhatTu.Services.ChuaService;
import QuanLyPhatTu.Services.PhatTuServices;
import QuanLyPhatTu.models.Chua;
import QuanLyPhatTu.models.PhatTu;
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
public class ChuaController {
    @Autowired
    private ChuaService chuaService;
    @RequestMapping(value = "/addchua",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)

    public String addChua(@RequestBody String chua)
    {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Chua chua1 = gson.fromJson(chua,Chua.class);
        return chuaService.addchua(chua1);

    }
    @PutMapping(value = "/suachua",produces = MediaType.APPLICATION_JSON_VALUE)
    public String remakechua( @RequestBody String chua)
    {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Chua chua1 = gson.fromJson(chua,Chua.class);
        return chuaService.remake(chua1);
    }
    @DeleteMapping(value = "/xoachua",produces = MediaType.APPLICATION_JSON_VALUE)
    public String removechua(@RequestParam int idchua)
    {
        return chuaService.remove(idchua);
    }
    @GetMapping (value = "/search2",produces = MediaType.APPLICATION_JSON_VALUE)
    List<Chua> search(@RequestParam String ten)
    {
        return chuaService.timkiemtheoten(ten);
    }
    @GetMapping(value = "/Pageable2/{pageNumber}/{pageSize}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Chua> phattuPage(@PathVariable Integer pageNumber, @PathVariable Integer pageSize)
    {
        return chuaService.getphattupage(pageNumber,pageSize);
    }
}
