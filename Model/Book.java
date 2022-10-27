package vn.edu.poly.duanmau.Model;

public class Book {
    private int idCategory;
    private int idBook;
    private String titleCategory;
    private String titleBook;
    private String tacGia;
    private double gia;
    private int soLuong;



    public Book() {

    }

    public Book(int idCategory, int idBook, String titleCategory, String titleBook, String tacGia, double gia, int soLuong) {
        this.idCategory = idCategory;
        this.idBook = idBook;
        this.titleCategory = titleCategory;
        this.titleBook = titleBook;
        this.tacGia = tacGia;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }


    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
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

    public String toString(){
        return "Tên sách: "+titleBook+" Mã sách: "+idBook+"\n"+"Giá Tiền: "+gia+" Tác giả: "+tacGia;
    }
}
