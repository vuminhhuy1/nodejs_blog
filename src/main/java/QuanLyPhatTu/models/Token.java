package QuanLyPhatTu.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class Token  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "stoken")
    private String stoken;
    @Column(name = "phattuid",insertable = false,updatable = false)
    private int phattuid;
    @ManyToOne
    @JoinColumn(name = "phattuid")
    @JsonBackReference
    private PhatTu phatTu;

}
