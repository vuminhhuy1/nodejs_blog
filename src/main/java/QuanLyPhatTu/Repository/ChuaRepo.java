package QuanLyPhatTu.Repository;

import QuanLyPhatTu.models.Chua;
import QuanLyPhatTu.models.PhatTu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChuaRepo extends JpaRepository<Chua,Integer> {
    Optional<Chua> findByTenchua(String ten);
}
