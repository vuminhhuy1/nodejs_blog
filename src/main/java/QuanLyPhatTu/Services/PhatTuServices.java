package QuanLyPhatTu.Services;

import QuanLyPhatTu.Repository.*;
import QuanLyPhatTu.models.Chua;
import QuanLyPhatTu.models.KieuThanhVien;
import QuanLyPhatTu.models.PhatTu;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class PhatTuServices implements IPhatTu {
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private DonDangKyRepo donDangKyRepo;
    @Autowired
    private PhatTuDaoTrangRepo phatTuDaoTrangRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KieuThanhVienRepo kieuThanhVienRepo;
    @Autowired
    private ChuaRepo chuaRepo;
    @Override
    public String addPhatTu(PhatTu pt) {
        KieuThanhVien kieuthanhviens = new KieuThanhVien();
        Optional<KieuThanhVien> kieuthanhviensOptional = kieuThanhVienRepo.findById(2);
        kieuthanhviens = kieuthanhviensOptional.get();

        List<PhatTu> phattuList = phatTuRepo.findAll();
        if (phattuList.size() > 0) {
            for (PhatTu i : phattuList) {
                if (i.getEmail().equals(pt.getEmail())) {
                    return "thêm thất bại tài khoản đã tồn tại";
                }
            }
            pt.setActive(true);
            pt.setKieuThanhVien(kieuthanhviens);
            pt.setRootpassword(pt.getPassword());
            pt.setPassword(passwordEncoder.encode(pt.getPassword()));
            phatTuRepo.save(pt);
            return "thêm thành công";
        } else {
            pt.setActive(true);
            pt.setKieuThanhVien(kieuthanhviens);
            pt.setRootpassword(pt.getPassword());
            pt.setPassword(passwordEncoder.encode(pt.getPassword()));
            phatTuRepo.save(pt);
            return "thêm thành công";
        }

    }

    @Override
    public List<PhatTu> showphattu() {

        List<PhatTu> ptl = phatTuRepo.findAll();
        return ptl;
    }

    @Override
    public String remakePhatTu(PhatTu PhatTuSua) {
        Optional<PhatTu> phatTu = phatTuRepo.findById(PhatTuSua.getPhattuid());
        String str = "";
        PhatTu phatTu1 = phatTu.get();
        phatTu1.setPhattuid(PhatTuSua.getPhattuid());
        phatTu1.setAnhchup(PhatTuSua.getAnhchup());
        phatTu1.setChuaid(PhatTuSua.getChuaid());
        phatTu1.setDahoantuc(PhatTuSua.isDahoantuc());
        phatTu1.setEmail(PhatTuSua.getEmail());
        phatTu1.setKieuthanhvienid(PhatTuSua.getKieuthanhvienid());
        phatTu1.setNgayxuatgia(PhatTuSua.getNgayxuatgia());
        phatTu1.setSodienthoai(PhatTuSua.getSodienthoai());
        phatTu1.setGioitinh(PhatTuSua.getGioitinh());
        phatTu1.setHo(PhatTuSua.getHo());
        phatTu1.setNgaycapnhat(PhatTuSua.getNgaycapnhat());
        phatTu1.setNgayhoantuc(PhatTuSua.getNgayhoantuc());
        phatTu1.setNgaysinh(PhatTuSua.getNgaysinh());
        phatTu1.setPhapdanh(PhatTuSua.getPhapdanh());
        phatTu1.setTen(PhatTuSua.getTen());
        phatTu1.setTendem(PhatTuSua.getTendem());
        phatTuRepo.save(phatTu1);
        List<PhatTu> list = new ArrayList<>();

        for (PhatTu x : list
        ) {

            if (x.getSodienthoai() == phatTu1.getSodienthoai() || x.getEmail() == phatTu1.getEmail()) {
                str += "sửa thất bại";
            } else {
                return str += "sửa thành công";
            }
        }
        return str;
    }

    @Override
    public String removePhatTu(int idphattu) {
        Optional<PhatTu> phatTu1 = phatTuRepo.findById(idphattu);
        String str = "";
        if (phatTu1.get().isActive() == false) {
            str += "xóa thành công";
        }else
        {
            str+="xóa thất bại";
        }
        return str;
    }

    @Override
    public List<PhatTu> search(String ten) {
        Optional<PhatTu> phatTu = phatTuRepo.findByTenContains(ten);
        if (phatTu.isEmpty()) {
            return null;
        }
        List<PhatTu> list = new ArrayList<>();
        phatTuRepo.findAll().forEach(x -> {
            if (x.getTen().toLowerCase().contains(ten)) {
                list.add(x);
            }
        });
        return list;
    }

    public Page<PhatTu> getphattupage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return phatTuRepo.findAll(pageable);
    }

    @Override
    public boolean ChangePassword(String email, String password) {
        List<PhatTu> ptlist = phatTuRepo.findAll();
        for (PhatTu i : ptlist) {
            if (i.getEmail().equals(email)) {
                i.setRootpassword(password);
                i.setPassword(passwordEncoder.encode(password));
                return true;
            }
        }
        return false;
    }

    public String addUser(PhatTu phatTu) {
        KieuThanhVien kieuthanhviens = new KieuThanhVien();
        Chua chua = new Chua();
        Optional<KieuThanhVien> kieuthanhviensOptional = kieuThanhVienRepo.findById(1);
        Optional<Chua> chuaOptional = chuaRepo.findById(1);
        chua = chuaOptional.get();
        kieuthanhviens = kieuthanhviensOptional.get();
        for (PhatTu pt: phatTuRepo.findAll()
             ) {
            if (pt.getEmail().equals(phatTu.getEmail()))
            {
                return "thêm thất bại tài khoản đã tồn tại rồi đó";
            }
        }
            phatTu.setActive(true);
            phatTu.setKieuThanhVien(kieuthanhviens);
            phatTu.setChua(chua);
            phatTu.setRootpassword(phatTu.getPassword());
            phatTu.setPassword(passwordEncoder.encode(phatTu.getPassword()));
            phatTuRepo.save(phatTu);
            return "thêm thành công";
        }

    }


