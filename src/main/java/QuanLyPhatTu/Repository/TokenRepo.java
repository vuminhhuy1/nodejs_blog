package QuanLyPhatTu.Repository;

import QuanLyPhatTu.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Token,Integer> {
}
