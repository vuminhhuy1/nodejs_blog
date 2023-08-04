package QuanLyPhatTu.Services;

import QuanLyPhatTu.Repository.DaoTrangRepo;
import QuanLyPhatTu.models.DaoTrang;
import QuanLyPhatTu.models.PhatTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class DaoTrangService implements IDaoTrang{
@Autowired
private DaoTrangRepo daoTrangRepo;
    @Override
    public String addDaoTrang(DaoTrang daoTrang) {
        String str="";
        Calendar calendar = Calendar.getInstance();
        daoTrangRepo.save(daoTrang);
        str+="thêm thành công";
        return str;
    }

    @Override
    public String remake(DaoTrang suadaotrang) {
        Optional<DaoTrang> daoTrang = daoTrangRepo.findById(suadaotrang.getDaotrangid());
        String str ="";
        DaoTrang daoTrang1 = daoTrang.get();
        daoTrang1.setDaketthuc(suadaotrang.isDaketthuc());
        daoTrang1.setDaotrangid(suadaotrang.getDaotrangid());
        daoTrang1.setNguoitrutri(suadaotrang.getNguoitrutri());
        daoTrang1.setNoidung(suadaotrang.getNoidung());
        daoTrang1.setNoitochuc(suadaotrang.getNoitochuc());
        daoTrang1.setSothanhvienthamgia(suadaotrang.getSothanhvienthamgia());
        daoTrang1.setThoigiantochuc(suadaotrang.getThoigiantochuc());
        str+="sửa thành công";
        return str;
    }

    @Override
    public String remove(int iddaotrang) {
        String str = "";
       Optional<DaoTrang> daoTrang  = daoTrangRepo.findById(iddaotrang);
       List<DaoTrang> daoTrangs = new ArrayList<>();
        for (DaoTrang dt: daoTrangs
             ) {
            if (dt.getDaotrangid() == iddaotrang)
            {
                daoTrangRepo.deleteById(iddaotrang);
                str+="xóa thành công";
            }else
            {
                str+="xóa thất bại";
            }
        }
        return str;
    }

    @Override
    public List<DaoTrang> timkiemtheonoitochuc(String noitochua) {
        Optional<DaoTrang> daoTrang = daoTrangRepo.findByNoitochuc(noitochua);
        if (daoTrang.isEmpty())
        {
            return null;
        }
        List<DaoTrang> list = new ArrayList<>();
        daoTrangRepo.findAll().forEach(x->{
            if (x.getNoitochuc().toLowerCase().contains(noitochua))
            {
                list.add(x);
            }
        });
        return list;
    }
    public Page<DaoTrang> getphattupage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return daoTrangRepo.findAll(pageable);
    }
}
