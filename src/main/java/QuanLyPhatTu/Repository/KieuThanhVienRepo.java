package QuanLyPhatTu.Repository;

import QuanLyPhatTu.models.KieuThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuThanhVienRepo extends JpaRepository<KieuThanhVien,Integer> {
}
