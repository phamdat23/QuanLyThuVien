package vn.edu.poly.duanmau.Model;

public class ThongKe {
    private double tongDoanhThu;
    private String ngayThang;

    public ThongKe() {
    }

    public ThongKe(double tongDoanhThu, String ngayThang) {
        this.tongDoanhThu = tongDoanhThu;
        this.ngayThang = ngayThang;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }
}
