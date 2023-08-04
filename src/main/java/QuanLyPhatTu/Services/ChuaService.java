package QuanLyPhatTu.Services;

import QuanLyPhatTu.Repository.ChuaRepo;
import QuanLyPhatTu.models.Chua;
import QuanLyPhatTu.models.PhatTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChuaService implements IChua{
@Autowired
private ChuaRepo chuaRepo;
    @Override
    public String addchua(Chua chua) {
        String str = "";
        chuaRepo.save(chua);
        str+="thêm thành công";
        return str;
     }

    @Override
    public String remake(Chua suachua) {
       String str = "";
       Optional<Chua> chua = chuaRepo.findById(suachua.getChuaid());
       Chua chua1 = chua.get();
       chua1.setTrutri(suachua.getTrutri());
       chua1.setChuaid(suachua.getChuaid());
       chua1.setTenchua(suachua.getTenchua());
       chua1.setNgaythanhlap(suachua.getNgaythanhlap());
       chua1.setDiachi(suachua.getDiachi());
       chua1.setCapnhat(suachua.getCapnhat());
       chuaRepo.save(chua1);
       str+="sửa thành công";
       return str;
    }



    @Override
    public String remove(int idchua) {
        String str = "";
       Optional<Chua> chua = chuaRepo.findById(idchua);
      List<Chua> chuas = new ArrayList<>();
        for (Chua chua1: chuas
             ) {
            if (chua1.getChuaid() == idchua)
            {
                chuaRepo.deleteById(idchua);
                str+="xóa thành công";
            }else
            {
                str+="xóa thất bại";
            }

        }
       return str;
    }

    @Override
    public List<Chua> timkiemtheoten(String ten) {
        Optional<Chua> chua = chuaRepo.findByTenchua(ten);
        if (chua.isEmpty())
        {
            return null;
        }
        List<Chua> list = new ArrayList<>();
        chuaRepo.findAll().forEach(x->{
            if (x.getTenchua().toLowerCase().contains(ten))
            {
                list.add(x);
            }
        });
        return list;
    }
    public Page<Chua> getphattupage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return chuaRepo.findAll(pageable);
    }
}
