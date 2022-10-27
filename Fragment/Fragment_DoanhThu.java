package vn.edu.poly.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.edu.poly.duanmau.DAO.DAO_PMCT;
import vn.edu.poly.duanmau.Model.ThongKe;
import vn.edu.poly.duanmau.R;

public class Fragment_DoanhThu extends Fragment {
    private TextView tvNgayStart;
    private ImageView imgDateStart;
    private TextView tvNgayEnd;
    private ImageView imgDateEnd;
    private MaterialButton btnSearch;
    private TextView tvTk;
    private TextView tvTkMonth;
    private TextView tvSumTk;
    private TextView tvTkDay;
    Context context;
    DAO_PMCT dao_pmct;
    ArrayList<ThongKe> tongDoanhThu;
    ArrayList<ThongKe> doanhThuThang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        context = getContext();
        dao_pmct = new DAO_PMCT(context);
        dao_pmct.opend();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tongDoanhThu = dao_pmct.TongDoanhThu();
        doanhThuThang = dao_pmct.DoanhThuTheoThang();
        tvNgayStart = view.findViewById(R.id.tv_ngayStart);
        imgDateStart = view.findViewById(R.id.img_dateStart);
        tvNgayEnd = view.findViewById(R.id.tv_ngayEnd);
        imgDateEnd = view.findViewById(R.id.img_dateEnd);
        btnSearch = view.findViewById(R.id.btn_search);
        tvTk = view.findViewById(R.id.tv_tk);
        tvTkMonth = view.findViewById(R.id.tv_tkMonth);
        tvSumTk = view.findViewById(R.id.tv_sumTk);
        tvTkDay = view.findViewById(R.id.tv_tkDay);
        imgDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDateS();
            }
        });
        imgDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDateE();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               double dt= dao_pmct.doanhThu(formatDate1(tvNgayStart.getText().toString()),formatDate1(tvNgayEnd.getText().toString()));
                tvTk.setText(dt+"$");
            }
        });
        tvSumTk.setText(tongDoanhThu.get(0).getTongDoanhThu()+"$");
        tvTkMonth.setText(doanhThuThang.get(0).getTongDoanhThu()+"$");
        tvTkDay.setText(dao_pmct.doanhThuNgay()+"$");
    }

    public void dialogDateS() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                tvNgayStart.setText(days + "-" + (months + 1) + "-" + years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void dialogDateE() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int days = dayOfMonth;
                int months = month;
                int years = year;
                tvNgayEnd.setText(days + "-" + (months + 1) + "-" + years);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public String formatDate1(String a) {
        String newDate=null;
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
        String newDate=null;
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
}
