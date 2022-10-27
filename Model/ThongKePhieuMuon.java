package vn.edu.poly.duanmau.Model;

public class ThongKePhieuMuon {
    private int idPM;
    private String tenSV;
    private String email;
    private String tenSach;
    private String ngayMuon;

    public ThongKePhieuMuon() {
    }

    public ThongKePhieuMuon(int idPM, String tenSV, String email, String tenSach, String ngayMuon) {
        this.idPM = idPM;
        this.tenSV = tenSV;
        this.email = email;
        this.tenSach = tenSach;
        this.ngayMuon = ngayMuon;
    }

    public int getIdPM() {
        return idPM;
    }

    public void setIdPM(int idPM) {
        this.idPM = idPM;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }
}
