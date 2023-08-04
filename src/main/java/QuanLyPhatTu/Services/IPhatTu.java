package QuanLyPhatTu.Services;

import QuanLyPhatTu.models.PhatTu;

import java.util.List;

public interface IPhatTu {
    public String addPhatTu(PhatTu pt);
    public String remakePhatTu(PhatTu PhatTuSua);
    public String removePhatTu(int idphattu);
    public List<PhatTu> search(String ten);
    public List<PhatTu> showphattu();
    public boolean ChangePassword(String email,String password);
}
