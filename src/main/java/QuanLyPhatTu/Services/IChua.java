package QuanLyPhatTu.Services;

import QuanLyPhatTu.models.Chua;
import org.apache.tomcat.util.buf.CharsetUtil;

import java.util.List;

public interface IChua {
    public String addchua(Chua chua);
    public String remake(Chua suachua);
    public String remove(int idchua);
    public List<Chua> timkiemtheoten(String ten);
}
