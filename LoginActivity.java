package vn.edu.poly.duanmau;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_Admin;
import vn.edu.poly.duanmau.Model.Admin;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout inputUser;
    private TextInputLayout inputPass;
    private MaterialCheckBox chkRePass;
    private TextView tvUpdatePass;
    private MaterialButton btnDn;
    private TextView tvCreateAccount;
    DAO_Admin dao_admin;
    ArrayList<Admin> list_ad;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            save();
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dao_admin = new DAO_Admin(LoginActivity.this);
        dao_admin.opend();
        list_ad = dao_admin.getAll();
        sharedPreferences=getSharedPreferences("ADMIN", Context.MODE_PRIVATE);
        inputUser = findViewById(R.id.input_user);
        inputPass = findViewById(R.id.input_pass);
        chkRePass = findViewById(R.id.chk_rePass);
        tvUpdatePass = findViewById(R.id.tv_update_pass);
        btnDn = findViewById(R.id.btn_dn);
        tvCreateAccount = findViewById(R.id.tv_create_account);
        bundle=new Bundle();
        btnDn.setOnClickListener(view->{
            if(checkLogin()==true){
                if(login()==true){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    inputPass.setError("");
                    save();
                    intent.putExtra(DAO_Admin.TB_NAME,bundle);
                    inputPass.getEditText().setText("");
                    startActivity(intent);
                }else{
                    inputPass.setError("Tên tài khoản hoặc mật khẩu không đúng");
                }
            }
        });
        tvCreateAccount.setOnClickListener(view->{
            Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
            startActivity(intent);
        });
        tvUpdatePass.setOnClickListener(view->{
            Intent intent = new Intent(LoginActivity.this, ForgotpassActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        list_ad = dao_admin.getAll();
        read();
    }

    public boolean login(){
        boolean a=false;
        for(int i=0;i< list_ad.size();i++){
            if( inputUser.getEditText().getText().toString().equals(list_ad.get(i).getIdAdmin()) && inputPass.getEditText().getText().toString().equals(list_ad.get(i).getPassword())){
                a=true;
                bundle.putString(DAO_Admin.IDAMIN,list_ad.get(i).getIdAdmin());
                bundle.putString(DAO_Admin.FULLNAME,list_ad.get(i).getFullName());
                bundle.putString(DAO_Admin.PHONE,list_ad.get(i).getPhone());
                save();
                break;
            }else{
                a= false;
            }
        }
        return a;
    }
    public boolean checkLogin(){
        if(inputUser.getEditText().getText().toString().isEmpty()||inputPass.getEditText().getText().toString().isEmpty()){
            if(inputUser.getEditText().getText().toString().isEmpty()){
                inputUser.setError("Tên tài khoản khồn được để trống");
            }else{
                inputUser.setError("");
            }
            if(inputPass.getEditText().getText().toString().isEmpty()){
                inputPass.setError("Mật khẩu không được để trống");
            }else{
                inputPass.setError("");
            }
            return false;
        }else{
            return true;
        }
    }
    public void save() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("user", inputUser.getEditText().getText().toString());
        editor.putString("pass", inputPass.getEditText().getText().toString());
        editor.putBoolean("check", chkRePass.isChecked());
        editor.commit();
    }

    public void read() {
        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");
        boolean a = sharedPreferences.getBoolean("check", false);
        inputUser.getEditText().setText(user);
        if (a == true) {
            inputPass.getEditText().setText(pass);
            chkRePass.setChecked(true);
        }
    }
}