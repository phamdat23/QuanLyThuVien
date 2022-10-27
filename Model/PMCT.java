package vn.edu.poly.duanmau.Model;

public class PMCT {
    private int idPMCT;
    private int idPM;
    private int idBook;
    private double gia;
    private int soLuong;
    private String titleBook;

    public PMCT() {
    }

    public PMCT(int idPMCT, int idPM, int idBook, double gia, int soLuong, String titleBook) {
        this.idPMCT = idPMCT;
        this.idPM = idPM;
        this.idBook = idBook;
        this.gia = gia;
        this.soLuong = soLuong;
        this.titleBook = titleBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public int getIdPMCT() {
        return idPMCT;
    }

    public void setIdPMCT(int idPMCT) {
        this.idPMCT = idPMCT;
    }

    public int getIdPM() {
        return idPM;
    }

    public void setIdPM(int idPM) {
        this.idPM = idPM;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
