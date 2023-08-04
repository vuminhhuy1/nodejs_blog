package QuanLyPhatTu.Repository;

import QuanLyPhatTu.models.PhatTuDaoTrang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhatTuDaoTrangRepo extends JpaRepository<PhatTuDaoTrang,Integer> {
}
