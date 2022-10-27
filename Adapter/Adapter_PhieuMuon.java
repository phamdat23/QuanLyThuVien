package vn.edu.poly.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
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

import vn.edu.poly.duanmau.DAO.DAO_PhieuMuon;
import vn.edu.poly.duanmau.DAO.DAO_User;
import vn.edu.poly.duanmau.Model.PhieuMuon;
import vn.edu.poly.duanmau.Model.User;
import vn.edu.poly.duanmau.R;
import vn.edu.poly.duanmau.ViewHoder.ViewHoderPhieuMuon;

public class Adapter_PhieuMuon extends RecyclerView.Adapter<ViewHoderPhieuMuon> {
    DAO_PhieuMuon dao_phieuMuon;
    ArrayList<PhieuMuon> list;
    Context context;
    DAO_User dao_user;
    ArrayList<User> listU;
    User user;
    Adapter_Spinner_User a;
    public Adapter_PhieuMuon(DAO_PhieuMuon dao_phieuMuon, ArrayList<PhieuMuon> list) {
        this.dao_phieuMuon = dao_phieuMuon;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoderPhieuMuon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_phieu_muon, parent, false);
        ViewHoderPhieuMuon viewHoderPhieuMuon = new ViewHoderPhieuMuon(row);
        context = parent.getContext();
        dao_phieuMuon = new DAO_PhieuMuon(context);
        dao_user = new DAO_User(context);
        return viewHoderPhieuMuon;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderPhieuMuon holder, int position) {
        dao_phieuMuon.opend();
        dao_user.opend();
        final int index = position;
        list = dao_phieuMuon.selectAll();
        listU = dao_user.getAll();
        PhieuMuon obj = list.get(index);
        holder.tvIdPM.setText("Mã Phiếu Mượn: " + obj.getId());
        holder.ngayMuon.setText("Ngày: " + formatDate2(obj.getNgayMuon()));
        holder.imgUpdatePhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(obj, index);
            }
        });
        holder.imgDeletePhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(obj, index);
            }
        });
        holder.tvXemThemPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChiTiet(obj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private TextView tvNgayMuonUP, tbError;
    private ImageView imgNgayMuonUP;
    private MaterialButton btnUpdatePhieuMuon;
    private Spinner spinnerUser;
    private TextView tvNgayTraUP;
    private ImageView imgNgayTraUP;






    public void dialogUpdate(PhieuMuon obj, int index) {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_update_phieu_muon);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tvNgayMuonUP = dialog.findViewById(R.id.tv_ngayMuonUP);
        imgNgayMuonUP = dialog.findViewById(R.id.img_ngayMuonUP);
        btnUpdatePhieuMuon = dialog.findViewById(R.id.btn_updatePhieuMuon);
        tbError = dialog.findViewById(R.id.tv_tbError);
        tvNgayTraUP = dialog.findViewById(R.id.tv_ngayTraUP);
        imgNgayTraUP = dialog.findViewById(R.id.img_ngayTraUP);
        spinnerUser = dialog.findViewById(R.id.spinner_User);
        a = new Adapter_Spinner_User(dao_user, listU);
        spinnerUser.setAdapter(a);
        for(int i=0;i<listU.size();i++){
            if(obj.getMaTV().equalsIgnoreCase(listU.get(i).getIdUser())){
                spinnerUser.setSelection(i);
                spinnerUser.setSelected(true);
            }
        }
        Log.e("zzzzzzzzzzz", "dialogUpdate: "+obj.getPhoneUser()+" xxx"+ obj.getNgayTra() );
        Log.e("zzzzzzzzzzz", "dialogUpdate: "+obj.getNameUser());
        Log.e("zzzzzzzzzzz", "dialogUpdate: "+obj.getMaTV());
        tvNgayMuonUP.setText(formatDate2(obj.getNgayMuon()));
        tvNgayTraUP.setText(formatDate2(obj.getNgayTra()));
        imgNgayMuonUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate(tvNgayMuonUP);
            }
        });
        imgNgayTraUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate(tvNgayTraUP);
            }
        });
        btnUpdatePhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvNgayMuonUP.getText().toString().trim().isEmpty()) {
                    tbError.setText("không được để trống mục này");
                } else {
                    User user = (User) spinnerUser.getSelectedItem();
                    obj.setMaTV(user.getIdUser());
                    obj.setNgayMuon(formatDate(tvNgayMuonUP.getText().toString()));
                    obj.setNgayTra(formatDate(tvNgayTraUP.getText().toString()));
                    if (dao_phieuMuon.updatePM(obj) > 0) {
                        list.set(index, obj);
                        notifyItemChanged(index);
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        dialog.show();
    }

    public void dialogDelete(PhieuMuon obj, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa phiếu mượn có mã: " + obj.getId());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_phieuMuon.deletePM(obj);
                if (res > 0) {
                    list.remove(index);
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
    private TextView tvID;
    private TextView tvIDUser;
    private TextView tvNgayMuonCT;
    private TextView tvNgayTraCT;


    public void dialogChiTiet(PhieuMuon obj){
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_chi_tiet_pm);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tvID = dialog.findViewById(R.id.tv_ID);
        tvIDUser = dialog.findViewById(R.id.tv_IDUser);
        tvNgayMuonCT = dialog.findViewById(R.id.tv_ngayMuonCT);
        tvNgayTraCT = dialog.findViewById(R.id.tv_ngayTraCT);
        Log.e("zzzzzzzzzzz", "dialogUpdate: "+obj.getPhoneUser()+" xxx"+ obj.getNgayTra() );
        Log.e("zzzzzzzzzzz", "dialogUpdate: "+obj.getNameUser());
        Log.e("zzzzzzzzzzz", "dialogUpdate: "+obj.getMaTV());
        tvID.setText("Mã phiếu mượn: "+obj.getId());
        tvIDUser.setText("Mã thành viên: "+obj.getMaTV());
        tvNgayMuonCT.setText("Ngày mượn: "+formatDate2(obj.getNgayMuon()));
        tvNgayTraCT.setText("Ngày trả: "+formatDate2(obj.getNgayTra()));
        dialog.show();
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

    public void dialogDate(TextView tv) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                tv.setText(days + "-" + (months + 1) + "-" + years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}
