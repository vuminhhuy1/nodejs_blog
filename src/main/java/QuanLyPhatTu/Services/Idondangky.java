package QuanLyPhatTu.Services;

import QuanLyPhatTu.models.DonDangKy;

public interface Idondangky {
    public String themdondangki(DonDangKy dondangkythem);
    public String xoadondangki(int dondangkiid);
    public String duyetdon(int donid,boolean xacnhanduyet);
}
