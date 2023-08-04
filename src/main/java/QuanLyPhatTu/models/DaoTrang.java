package QuanLyPhatTu.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "daotrangs")
public class DaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daotrangid")
    private int daotrangid;
    @Column(name = "daketthuc")
    private boolean daketthuc;
    @Column(name = "noidung")
    private String noidung;
    @Column(name = "noitochuc")
    private String noitochuc;
    @Column(name = "sothanhvienthamgia")
    private int sothanhvienthamgia;
    @Column(name = "thoigiantochuc")
    private Date thoigiantochuc;
    @Column(name = "nguoitrutri")
    private int nguoitrutri;
    @Column(name = "isactive")
    private boolean isActive;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "daoTrang")
    @JsonManagedReference
    Set<DonDangKy> donDangKIES;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "daoTrang")
    @JsonManagedReference
    private Set<PhatTuDaoTrang> phatTuDaoTrangs;


}
