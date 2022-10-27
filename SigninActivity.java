package vn.edu.poly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import vn.edu.poly.duanmau.DAO.DAO_Admin;
import vn.edu.poly.duanmau.Model.Admin;

public class SigninActivity extends AppCompatActivity {
    private ImageView imgBack;
    private TextInputLayout inputFullName;
    private TextInputLayout inputSdt;
    private TextInputLayout inputUserName;
    private TextInputLayout inputPassWord;
    private TextInputLayout inputCofPass;
    private MaterialButton btnDk;
    DAO_Admin dao_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signin);
        dao_admin= new DAO_Admin(getApplicationContext());
        dao_admin.opend();
        imgBack = findViewById(R.id.img_back);
        inputFullName = findViewById(R.id.input_fullName);
        inputSdt = findViewById(R.id.input_sdt);
        inputUserName = findViewById(R.id.input_userName);
        inputPassWord = findViewById(R.id.input_passWord);
        inputCofPass = findViewById(R.id.input_CofPass);
        btnDk = findViewById(R.id.btn_dk);
        imgBack.setOnClickListener(view -> {
            finish();
        });
        btnDk.setOnClickListener(view->{
            if(checkSignin()==true){
                if(Signin()==true){
                    finish();
                }else{
                    Toast.makeText(SigninActivity.this, "Đăng ký thất bại ( Có thể UserName đã tồn tại)" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean Signin() {
        Admin obj = new Admin();
        String a = inputFullName.getEditText().getText().toString().trim();
        obj.setFullName(a.toUpperCase());
       obj.setIdAdmin(inputUserName.getEditText().getText().toString().trim());
       obj.setPhone(inputSdt.getEditText().getText().toString().trim());
       obj.setPassword(inputPassWord.getEditText().getText().toString().trim());
        long res = dao_admin.insertAdmin(obj);
        if (res > 0) {
            Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public boolean checkSignin() {
        if (inputFullName.getEditText().getText().toString().isEmpty() ||
                inputSdt.getEditText().getText().toString().isEmpty() ||
                inputUserName.getEditText().getText().toString().isEmpty() ||
                inputPassWord.getEditText().getText().toString().isEmpty() ||
                inputCofPass.getEditText().getText().toString().isEmpty()) {
            if (inputFullName.getEditText().getText().toString().isEmpty()) {
                inputFullName.setError("Không được để trống trường này");
            } else {
                inputFullName.setError("");
            }
            if (inputSdt.getEditText().getText().toString().isEmpty()) {
                inputSdt.setError("Không được để trống trường này");
            } else {
                inputSdt.setError("");
            }
            if (inputUserName.getEditText().getText().toString().isEmpty()) {
                inputUserName.setError("Không được để trống trường này");
            } else {
                inputUserName.setError("");
            }
            if (inputPassWord.getEditText().getText().toString().isEmpty()) {
                inputPassWord.setError("Không được để trống trường này");
            } else if (inputPassWord.getEditText().getText().toString().trim().length() < 8) {
                inputPassWord.setError("Mật khẩu phải ít nhất 8 ký tự");
            } else {
                inputPassWord.setError("");
            }
            if (inputCofPass.getEditText().getText().toString().isEmpty()) {
                inputCofPass.setError("Không được để trống trường này");
            } else if (!inputCofPass.getEditText().getText().toString().trim().equals(inputPassWord.getEditText().getText().toString().trim())) {
                inputCofPass.setError("Không đúng mật khẩu");
            } else {
                inputCofPass.setError("");
            }

            return false;
        } else {
            return true;
        }
    }

}