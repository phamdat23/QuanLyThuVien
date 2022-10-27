package vn.edu.poly.duanmau.Model;

public class User {
    private String idUser;
    private String fullName;
    private String phone;
    private String dates;
    private String email;

    public User() {
    }

    public User(String idUser, String fullName, String phone, String dates, String email) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.phone = phone;
        this.dates = dates;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}
