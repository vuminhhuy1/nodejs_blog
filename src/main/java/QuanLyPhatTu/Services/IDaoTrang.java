package QuanLyPhatTu.Services;

import QuanLyPhatTu.models.Chua;
import QuanLyPhatTu.models.DaoTrang;

import java.util.List;

public interface IDaoTrang {
    public String addDaoTrang(DaoTrang daoTrang);
    public String remake(DaoTrang suadaotrang);
    public String remove(int iddaotrang);
    public List<DaoTrang> timkiemtheonoitochuc(String noitochua);
}
