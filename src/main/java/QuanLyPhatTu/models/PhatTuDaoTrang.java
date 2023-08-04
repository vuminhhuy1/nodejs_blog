package QuanLyPhatTu.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phattudaotrangs")
public class PhatTuDaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phattudaotrangid")
    private int phattudaotrangid;
    @Column(name = "dathamgia")
    private Boolean dathamgia;
    @Column(name = "lydokhongthamgia")
    private String lydokhongthamgia;
    @Column(name = "daotrangid",insertable = false,updatable = false)
    private int daotrangid;
    @Column(name = "phattuid",insertable = false,updatable = false)
    private int phattuid;
    @ManyToOne
    @JoinColumn(name = "daotrangid")
    @JsonBackReference
    private DaoTrang daoTrang;
    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonBackReference
    private PhatTu phatTu;


}
