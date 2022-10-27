package vn.edu.poly.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.duanmau.Adapter.AdapterUser;
import vn.edu.poly.duanmau.DAO.DAO_User;
import vn.edu.poly.duanmau.Model.User;
import vn.edu.poly.duanmau.R;

public class Fragment_User extends Fragment {
    private RecyclerView rcvListUser;
    private FloatingActionButton fabAddUser;
    DAO_User dao_user;
    AdapterUser adapterUser;
    ArrayList<User> list_user;
    private TextInputLayout inputIdAdd;
    private TextInputLayout inputFullNameAdd;
    private TextView tvDates;
    private ImageView imgDates;
    private TextView tvTtDates;
    private TextInputLayout inputPhoneAdd;
    private MaterialButton btnAddUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qltv, container, false);
        dao_user = new DAO_User(getContext());
        dao_user.opend();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListUser = view.findViewById(R.id.rcv_list_user);
        fabAddUser = view.findViewById(R.id.fab_add_user);
        refeshData();
        rcvListUser.setItemAnimator(new DefaultItemAnimator());
        adapterUser.notifyItemInserted(list_user.size()-1);
        rcvListUser.scrollToPosition(adapterUser.getItemCount()-1);
        fabAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddUser();
            }
        });
    }

    public void refeshData() {
        list_user = dao_user.getAll();
        adapterUser = new AdapterUser(dao_user, list_user);
        rcvListUser.setAdapter(adapterUser);
    }


    public void dialogAddUser() {
        final Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_add_user);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        inputIdAdd = dialog.findViewById(R.id.input_idAdd);
        inputFullNameAdd = dialog.findViewById(R.id.input_fullNameAdd);
        tvDates = dialog.findViewById(R.id.tv_dates);
        imgDates = dialog.findViewById(R.id.img_dates);
        tvTtDates = dialog.findViewById(R.id.tv_tt_dates);
        inputPhoneAdd = dialog.findViewById(R.id.input_phoneAdd);
        btnAddUser = dialog.findViewById(R.id.btn_AddUser);
        imgDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate();
            }
        });
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkError() == true&&checkPhoneNumber()==true) {
                    User user = new User();
                    user.setIdUser(inputIdAdd.getEditText().getText().toString().trim().toUpperCase());
                    user.setFullName(inputFullNameAdd.getEditText().getText().toString().trim().toUpperCase());
                    user.setDates(formatDate(tvDates.getText().toString().trim()));
                    user.setPhone(inputPhoneAdd.getEditText().getText().toString().trim());
                    if (dao_user.insertUser(user) > 0) {
                        list_user.clear();
                        list_user.addAll(dao_user.getAll());
                        adapterUser.notifyDataSetChanged();
                        dialog.dismiss();
                        refeshData();
                        Toast.makeText(getContext(), "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thành viên thất bại (Mã thành viên đã tồn tại)!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();

    }

    public void dialogDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                tvDates.setText(days + "-" + (months + 1) + "-" + years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public String formatDate(String a) {
        String newDate = null;
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 = a;
        SimpleDateFormat Format2 = new SimpleDateFormat("dd-mm-yyyy");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("yyyy-mm-dd", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }


    public boolean checkError() {
        if (inputIdAdd.getEditText().getText().toString().trim().isEmpty() ||
                inputFullNameAdd.getEditText().getText().toString().trim().isEmpty() ||
                inputPhoneAdd.getEditText().getText().toString().trim().isEmpty() ||
                tvDates.getText().toString().isEmpty()) {
            if (inputIdAdd.getEditText().getText().toString().trim().isEmpty()) {
                inputIdAdd.setError("Mã thành viên không được để trống");
            } else {
                inputIdAdd.setError("");
            }
            if (inputFullNameAdd.getEditText().getText().toString().trim().isEmpty()) {
                inputFullNameAdd.setError("Họ tên không được để trống");
            } else {
                inputFullNameAdd.setError("");
            }
            if (inputPhoneAdd.getEditText().getText().toString().trim().isEmpty()) {
                inputPhoneAdd.setError("Số điện thoại không được để trống");
            } else {
                inputPhoneAdd.setError("");
            }
            if (tvDates.getText().toString().isEmpty()) {
                tvTtDates.setText("Ngày sinh không được để trông");
            } else {
                tvTtDates.setText("");
            }
            return false;

        } else {
            return true;
        }
    }
    public boolean checkPhoneNumber(){
        if(!inputPhoneAdd.getEditText().getText().toString().trim().matches("^[0-9]{10}$")){
            inputPhoneAdd.setError("Số điện thoại có 10 ký tự");
            return false;
        }else{
            inputPhoneAdd.setError("");
            return true;
        }

    }

}
