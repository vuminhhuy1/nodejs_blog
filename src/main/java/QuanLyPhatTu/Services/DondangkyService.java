package QuanLyPhatTu.Services;

import QuanLyPhatTu.Repository.DaoTrangRepo;
import QuanLyPhatTu.Repository.DonDangKyRepo;
import QuanLyPhatTu.Repository.PhatTuRepo;
import QuanLyPhatTu.models.DaoTrang;
import QuanLyPhatTu.models.DonDangKy;
import QuanLyPhatTu.models.PhatTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DondangkyService implements Idondangky{
    @Autowired
    private DaoTrangRepo daoTrangRepo;
    @Autowired
    private DonDangKyRepo donDangKyRepo;
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Override
    public String themdondangki(DonDangKy dondangky) {
        int userid = 0;
        PhatTu phattu = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                List<PhatTu> phattuList = phatTuRepo.findAll();
                for (PhatTu i : phattuList) {
                    if (i.getEmail().equals(username)) {
                        userid = i.getPhattuid();
                        phattu = i;
                    }
                }
            }
        }
        dondangky.setTrangthaidon(0);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dondangky.setNgayguidon(date);
        dondangky.setPhatTu(phattu);
        donDangKyRepo.save(dondangky);
        return "thêm thành công,đợi duyệt đơn";

    }

    @Override
    public String xoadondangki(int dondangkyid) {
        Optional<DonDangKy> dondangky = donDangKyRepo.findById(dondangkyid);
        DonDangKy dondangkys = dondangky.get();
        List<DaoTrang> daotrangsList = daoTrangRepo.findAll();
        for (DaoTrang i : daotrangsList) {
            if (dondangkys.getDaoTrang().getDaotrangid() == i.getDaotrangid()) {
                if(dondangkys.getTrangthaidon() == 1)
                {
                    Optional<DaoTrang> daotrangsOptional = daoTrangRepo.findById(dondangkys.getDaoTrang().getDaotrangid());
                    DaoTrang daotrangs = daotrangsOptional.get();
                    daotrangs.setSothanhvienthamgia(daotrangs.getSothanhvienthamgia() - 1);
                    daoTrangRepo.save(daotrangs);
                }

            }
        }
        daoTrangRepo.deleteById(dondangkyid);

        return "xóa thành công";
    }

    @Override
    public String duyetdon(int donid, boolean xacnhanduyet) {
        int adminid = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                List<PhatTu> phattuList = phatTuRepo.findAll();
                for (PhatTu i : phattuList) {
                    if (i.getEmail().equals(username)) {
                        adminid = i.getPhattuid();
                    }
                }
            }
        }
        int dem = 0;
        Optional<DonDangKy> dondangky = donDangKyRepo.findById(donid);
        if (dondangky.isPresent()) {
            DonDangKy dondangkys = dondangky.get();
            dondangkys.setTrangthaidon(xacnhanduyet == true ? 1 : 0);
            dondangkys.setNguoixuly(adminid);
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            dondangkys.setNgayxuly(date);
            if (dondangkys.getTrangthaidon() == 1 && dondangkys.getPhatTu().getKieuThanhVien().getKieuthanhvienid() == 1) {
                List<DonDangKy> dondangkysList = donDangKyRepo.findAll();
                List<DaoTrang> daotrangsList = daoTrangRepo.findAll();
                for (DonDangKy i : dondangkysList) {
                    for (DaoTrang j : daotrangsList) {
                        if (i.getDaoTrang().getDaotrangid() == j.getDaotrangid()) {
                            if (dondangkys.getDaoTrang().getDaotrangid() == j.getDaotrangid()) {
                                    if(i.getTrangthaidon() == 1)
                                    {
                                        dem += 1;
                                    }
                            }
                        }
                    }
                }

                Optional<DaoTrang> daotrang = daoTrangRepo.findById(dondangkys.getDaoTrang().getDaotrangid());
                DaoTrang daotrangs = daotrang.get();
                daotrangs.setSothanhvienthamgia(dem);
                daoTrangRepo.save(daotrangs);
                return "Đã duyệt đơn";
            } else {
                return "Đã không duyệt đơn đăng kí này";
            }
        }
        return "Đơn đăng kí không tồn tại";
    }

}


