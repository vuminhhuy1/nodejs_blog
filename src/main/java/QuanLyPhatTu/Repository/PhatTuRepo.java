package QuanLyPhatTu.Repository;

import QuanLyPhatTu.models.PhatTu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhatTuRepo extends JpaRepository<PhatTu,Integer> {
   Optional<PhatTu> findByEmail(String email);
   Optional<PhatTu> findByEmailAndSodienthoai(String email,String sdt);
   Optional<PhatTu> findByTenContains(String ten);
}
