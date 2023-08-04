package QuanLyPhatTu.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phattu")
public class PhatTu  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phattuid")
    private int phattuid;
    @Column(name = "anhchup")
    private String anhchup;
    @Column(name = "dahoantuc")
    private boolean dahoantuc;
    @Column(name = "email")
    private String email;
    @Column(name = "gioitinh")
    private String gioitinh;
    @Column(name = "ho")
    private String ho;
    @Column(name = "ngaycapnhat")
    private Date ngaycapnhat;
    @Column(name = "ngayhoantuc")
    private Date ngayhoantuc;
    @Column(name = "ngaysinh")
    private Date ngaysinh;
    @Column(name = "ngayxuatgia")
    private Date ngayxuatgia;
    @Column(name = "password")
    private String password;
    @Column(name = "phapdanh")
    private String phapdanh;
    @Column(name = "sodienthoai")
    private String sodienthoai;
    @Column(name = "ten")
    private String ten;
    @Column(name = "tendem")
    private String tendem;
    @Column(name = "rootpassword")
    private String rootpassword;
    @Column(name = "chuaid",insertable = false,updatable = false)
    private int chuaid;
    @Column(name = "kieuthanhvienid",insertable = false,updatable = false)
    private int kieuthanhvienid;
    private boolean isActive;
    private String otp;
    private LocalDateTime localDateTime;
   @OneToMany(fetch = FetchType.LAZY,mappedBy = "phatTu")
   @JsonManagedReference
   private Set<PhatTuDaoTrang> phatTuDaoTrangs;
   @OneToMany(fetch = FetchType.LAZY,mappedBy = "phatTu")
   @JsonManagedReference
   private Set<DonDangKy> donDangKIES;
   @ManyToOne
   @JoinColumn(name = "kieuthanhvienid")
   @JsonBackReference
   private KieuThanhVien kieuThanhVien;
   @ManyToOne
   @JoinColumn(name = "chuaid")
   @JsonBackReference
   private Chua chua;
   @OneToMany(fetch = FetchType.LAZY,mappedBy = "phatTu")
   @JsonManagedReference
    private Set<Token> tokens;
}
