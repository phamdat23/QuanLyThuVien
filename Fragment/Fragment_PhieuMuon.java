package vn.edu.poly.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.duanmau.Adapter.Adapter_PhieuMuon;
import vn.edu.poly.duanmau.Adapter.Adapter_Spinner_User;
import vn.edu.poly.duanmau.DAO.DAO_PhieuMuon;
import vn.edu.poly.duanmau.DAO.DAO_User;
import vn.edu.poly.duanmau.Model.PhieuMuon;
import vn.edu.poly.duanmau.Model.User;
import vn.edu.poly.duanmau.R;

public class Fragment_PhieuMuon extends Fragment {
    private RecyclerView rcvListPhieuMuon;
    private FloatingActionButton fabAddPM;
    private TextView tvNgayMuon;
    private ImageView imgNgayMuon;
    private TextView tvTbError;
    private MaterialButton btnAddPhieuMuon;
    private Spinner spinnerUser;





    Context context;
    DAO_PhieuMuon dao_phieuMuon;
    ArrayList<PhieuMuon> list;
    Adapter_PhieuMuon adapter_phieuMuon;
    DAO_User dao_user;
    ArrayList<User> listU;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        context = getContext();
        dao_phieuMuon = new DAO_PhieuMuon(context);
        dao_user = new DAO_User(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao_phieuMuon.opend();
        dao_user.opend();
        listU= dao_user.getAll();
        list = dao_phieuMuon.selectAll();
        adapter_phieuMuon = new Adapter_PhieuMuon(dao_phieuMuon, list);
        rcvListPhieuMuon = view.findViewById(R.id.rcv_list_phieuMuon);
        fabAddPM = view.findViewById(R.id.fab_add_PM);
        rcvListPhieuMuon.setAdapter(adapter_phieuMuon);
        fabAddPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd();
                list = dao_phieuMuon.selectAll();
                adapter_phieuMuon = new Adapter_PhieuMuon(dao_phieuMuon, list);
                rcvListPhieuMuon.setAdapter(adapter_phieuMuon);
            }
        });
    }
    public void dialogAdd(){
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_add_phieu_muon);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tvNgayMuon = dialog.findViewById(R.id.tv_ngayMuon);
        imgNgayMuon = dialog.findViewById(R.id.img_ngayMuon);
        tvTbError = dialog.findViewById(R.id.tv_tbError);
        spinnerUser = dialog.findViewById(R.id.spinner_User);
        btnAddPhieuMuon = dialog.findViewById(R.id.btn_addPhieuMuon);
        Adapter_Spinner_User a = new Adapter_Spinner_User(dao_user,listU);
        spinnerUser.setAdapter(a);
        imgNgayMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDate();
            }
        });
        btnAddPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tvNgayMuon.getText().toString().trim().isEmpty()){
                    User user = (User) spinnerUser.getSelectedItem();
                    tvTbError.setText("");
                    PhieuMuon obj = new PhieuMuon();
                    obj.setMaTV(user.getIdUser());
                    obj.setNgayMuon(formatDate(tvNgayMuon.getText().toString()));
                    if(dao_phieuMuon.insertPM(obj)>0){
                        list.clear();
                        list.addAll(dao_phieuMuon.selectAll());
                        adapter_phieuMuon.notifyDataSetChanged();
                        Toast.makeText(context, "Thêm mới thành công" , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Lỗi" , Toast.LENGTH_SHORT).show();
                    }
                }else{
                    tvTbError.setText("Không được để trống ngày mượn");
                }
            }
        });
        dialog.show();
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
                tvNgayMuon.setText(days + "-" + (months + 1) + "-" + years);
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
}
