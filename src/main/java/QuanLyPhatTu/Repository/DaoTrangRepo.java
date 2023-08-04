package QuanLyPhatTu.Repository;

import QuanLyPhatTu.models.DaoTrang;
import QuanLyPhatTu.models.PhatTu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DaoTrangRepo extends JpaRepository<DaoTrang,Integer> {
    Optional<DaoTrang> findByNoitochuc(String noitochuc);
}
