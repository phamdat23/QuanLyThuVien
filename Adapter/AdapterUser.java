package vn.edu.poly.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.duanmau.DAO.DAO_User;
import vn.edu.poly.duanmau.Model.User;
import vn.edu.poly.duanmau.R;
import vn.edu.poly.duanmau.ViewHoder.ViewHoderUser;

public class AdapterUser extends RecyclerView.Adapter<ViewHoderUser> {
    Context context;
    DAO_User dao_user;
    ArrayList<User> list_user;

    public AdapterUser(DAO_User dao_user, ArrayList<User> list_user) {
        this.dao_user = dao_user;
        this.list_user = list_user;
    }

    @NonNull
    @Override
    public ViewHoderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_user, parent, false);
        ViewHoderUser viewHoderUser = new ViewHoderUser(row);
        context = parent.getContext();
        dao_user = new DAO_User(context);
        return viewHoderUser;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderUser holder, int position) {
        dao_user.opend();
        list_user = dao_user.getAll();
        final int index = position;
        User user = list_user.get(index);
        holder.tvFullNameUser.setText(user.getFullName());
        holder.tvPhoneNumberUser.setText(user.getPhone());
        holder.imgUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateUser(user, index);
            }
        });
        holder.imgDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeleteUser(user, index);
            }
        });
        holder.tv_chiTietUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChiTiet(user);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list_user == null ? 0 : list_user.size();
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

    public String formatDate2(String a) {
        String newDate = null;
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 = a;
        SimpleDateFormat Format2 = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("dd-mm-yyyy", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public void dialogDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                tvDatesUp.setText(days + "-" + (months + 1) + "-" + years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    private TextInputLayout inputIdUp;
    private TextInputLayout inputFullNameUp;
    private TextView tvDatesUp;
    private ImageView imgDatesUp;
    private TextView tvTtDatesUp;
    private TextInputLayout inputPhoneUp;
    private MaterialButton btnUserUp;


    public void dialogUpdateUser(User user, int index) {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_user);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);

        inputIdUp = dialog.findViewById(R.id.input_idUp);
        inputFullNameUp = dialog.findViewById(R.id.input_fullNameUp);
        tvDatesUp = dialog.findViewById(R.id.tv_datesUp);
        imgDatesUp = dialog.findViewById(R.id.img_datesUp);
        tvTtDatesUp = dialog.findViewById(R.id.tv_tt_datesUp);
        inputPhoneUp = dialog.findViewById(R.id.input_phoneUp);
        btnUserUp = dialog.findViewById(R.id.btn_UserUp);
        inputIdUp.getEditText().setText(user.getIdUser());
        inputFullNameUp.getEditText().setText(user.getFullName());
        inputPhoneUp.getEditText().setText(user.getPhone());
        tvDatesUp.setText(formatDate2(user.getDates()));
        imgDatesUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate();
            }
        });
        btnUserUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkError() == true&& checkPhoneNumber()==true) {
                    user.setIdUser(inputIdUp.getEditText().getText().toString().trim());
                    user.setPhone(inputPhoneUp.getEditText().getText().toString().trim());
                    user.setFullName(inputFullNameUp.getEditText().getText().toString().trim());
                    user.setDates(formatDate(tvDatesUp.getText().toString().trim()));
                    if (dao_user.updateUser(user) > 0) {
                        list_user.set(index, user);
                        notifyItemChanged(index);
                        dialog.dismiss();
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();

    }

    public void dialogDeleteUser(User user, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa thành viên");
        builder.setMessage("Bạn có muốn xóa " + user.getIdUser());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_user.deleteUser(user);
                if (res > 0) {
                    list_user.remove(index);
                    notifyItemRemoved(index);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private TextView tvIdUser;
    private TextView tvFullName;
    private TextView tvDate;
    private TextView tvPhone;



    public void dialogChiTiet(User user){
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_chi_tiet_tv);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tvIdUser = dialog.findViewById(R.id.tv_idUser);
        tvFullName = dialog.findViewById(R.id.tv_fullName);
        tvDate = dialog.findViewById(R.id.tv_date);
        tvPhone = dialog.findViewById(R.id.tv_phone);
        tvIdUser.setText("Mã thành viên: "+user.getIdUser());
        tvFullName.setText("Họ tên: "+user.getFullName());
        tvDate.setText("Ngày sinh: "+formatDate2(user.getDates()));
        tvPhone.setText("Số điện thoại: "+user.getPhone());
        dialog.show();
    }

    public boolean checkError() {
        if (inputIdUp.getEditText().getText().toString().trim().isEmpty() ||
                inputFullNameUp.getEditText().getText().toString().trim().isEmpty() ||
                inputPhoneUp.getEditText().getText().toString().trim().isEmpty() ||
                tvDatesUp.getText().toString().isEmpty()) {
            if (inputIdUp.getEditText().getText().toString().trim().isEmpty()) {
                inputIdUp.setError("Mã thành viên không được để trống");
            } else {
                inputIdUp.setError("");
            }
            if (inputFullNameUp.getEditText().getText().toString().trim().isEmpty()) {
                inputFullNameUp.setError("Họ tên không được để trống");
            } else {
                inputFullNameUp.setError("");
            }
            if (inputPhoneUp.getEditText().getText().toString().trim().isEmpty()) {
                inputPhoneUp.setError("Số điện thoại không được để trống");
            } else {
                inputPhoneUp.setError("");
            }
            if (tvDatesUp.getText().toString().isEmpty()) {
                tvTtDatesUp.setText("Ngày sinh không được để trông");
            } else {
                tvTtDatesUp.setText("");
            }
            return false;

        } else {
            return true;
        }
    }
    public boolean checkPhoneNumber(){
        if(!inputPhoneUp.getEditText().getText().toString().trim().matches("^[0-9]{10}$")){
            inputPhoneUp.setError("Số điện thoại có 10 ký tự");
            return false;
        }else{
            inputPhoneUp.setError("");
            return true;
        }

    }
}
