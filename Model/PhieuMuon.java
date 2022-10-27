package vn.edu.poly.duanmau.Model;

public class PhieuMuon {
    private int id;
    private String maTV;
    private String ngayMuon;
    private String nameUser;
    private String phoneUser;
    private String ngayTra;
    private String email;

    public PhieuMuon() {
    }


    public PhieuMuon(int id, String maTV, String ngayMuon, String nameUser, String phoneUser, String ngayTra, String email) {
        this.id = id;
        this.maTV = maTV;
        this.ngayMuon = ngayMuon;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.ngayTra = ngayTra;
        this.email = email;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTV() {
        return maTV;
    }

    public void setMaTV(String maTV) {
        this.maTV = maTV;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

}
