package vn.edu.poly.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_PMCT;
import vn.edu.poly.duanmau.DAO.DAO_PhieuMuon;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.PMCT;
import vn.edu.poly.duanmau.Model.PhieuMuon;
import vn.edu.poly.duanmau.R;
import vn.edu.poly.duanmau.ViewHoder.ViewHoder_PMCT;

public class Adapter_PMCT extends RecyclerView.Adapter<ViewHoder_PMCT> {
    DAO_PMCT dao_pmct;
    ArrayList<PMCT> list;
    DAO_Book dao_book;
    DAO_PhieuMuon dao_phieuMuon;
    ArrayList<Book> listB;
    ArrayList<PhieuMuon> listPM;
    Context context;

    public Adapter_PMCT(DAO_PMCT dao_pmct, ArrayList<PMCT> list) {
        this.dao_pmct = dao_pmct;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder_PMCT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pmct, parent, false);
        ViewHoder_PMCT viewHoder_pmct = new ViewHoder_PMCT(view);
        context = parent.getContext();
        dao_pmct = new DAO_PMCT(context);
        dao_book = new DAO_Book(context);
        dao_phieuMuon = new DAO_PhieuMuon(context);
        return viewHoder_pmct;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder_PMCT holder, int position) {
        dao_pmct.opend();
        dao_phieuMuon.opend();
        dao_book.opend();
        listB = dao_book.selectAll();
        listPM = dao_phieuMuon.selectAll();
        list = dao_pmct.selectAll();
        final int index = position;
        PMCT obj = list.get(index);
        holder.tvIdPMCT.setText("Mã PMCT: " + obj.getIdPMCT());
        holder.tvTitleBook.setText("Tên sách: " + obj.getTitleBook());
        holder.imgUpdatePMCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(obj, index);
            }
        });
        holder.imgDeletePMCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelate(obj, index);
            }
        });
        holder.tvXemThemPMCT.setOnClickListener(new View.OnClickListener() {
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

    private Spinner spinnerPMUp;
    private Spinner spinnerBookUp;
    private TextInputLayout inputSoLuongUp;
    private MaterialButton btnUpPMCT;


    public void dialogUpdate(PMCT obj, int index) {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_update_pmct);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinnerPMUp = dialog.findViewById(R.id.spinner_PMUp);
        spinnerBookUp = dialog.findViewById(R.id.spinner_BookUp);
        inputSoLuongUp = dialog.findViewById(R.id.input_soLuongUp);
        btnUpPMCT = dialog.findViewById(R.id.btn_UpPMCT);
        inputSoLuongUp.getEditText().setText(obj.getSoLuong()+"");
        Adapter_Spinner_PM adapter_spinner_pm = new Adapter_Spinner_PM(dao_phieuMuon, listPM);
        Adapter_SpinnerBook adapter_spinnerBook = new Adapter_SpinnerBook(dao_book, listB);
        spinnerPMUp.setAdapter(adapter_spinner_pm);
        spinnerBookUp.setAdapter(adapter_spinnerBook);
        for (int i = 0; i < listPM.size(); i++) {
            if (obj.getIdPM() == listPM.get(i).getId()) {
                spinnerPMUp.setSelection(i);
                spinnerPMUp.setSelected(true);
            }
        }
        for (int i = 0; i < listB.size(); i++) {
            if (obj.getIdBook() == listB.get(i).getIdBook()) {
                spinnerPMUp.setSelection(i);
                spinnerPMUp.setSelected(true);
            }
        }
        btnUpPMCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputSoLuongUp.getEditText().getText().toString().trim().isEmpty()) {
                    inputSoLuongUp.setError("Số lượng không được để trống");
                } else if (Integer.parseInt(inputSoLuongUp.getEditText().getText().toString().trim()) < 0) {
                    inputSoLuongUp.setError("Số lượng phải lớn hơn 0");
                } else {
                    PhieuMuon phieuMuon = (PhieuMuon) spinnerPMUp.getSelectedItem();
                    Book book = (Book) spinnerBookUp.getSelectedItem();
                    int a = Integer.parseInt(inputSoLuongUp.getEditText().getText().toString().trim());
                    double price = a * book.getGia();
                    obj.setIdPM(phieuMuon.getId());
                    obj.setIdBook(book.getIdBook());
                    obj.setTitleBook(book.getTitleBook());
                    obj.setSoLuong(Integer.parseInt(inputSoLuongUp.getEditText().getText().toString().trim()));
                    obj.setGia(price);
                    if (dao_pmct.updatePMCT(obj) > 0) {
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

    public void dialogDelate(PMCT obj, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa PMCT:" + obj.getIdPMCT());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_pmct.delete(obj);
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
    private TextView tvIdPMCT;
    private TextView tvIdPM;
    private TextView tvNameBook;
    private TextView tvSoLuong;
    private TextView tvGia;



    public void dialogChiTiet(PMCT obj){
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_chi_tiet_pmct);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tvIdPMCT = dialog.findViewById(R.id.tv_idPMCT);
        tvIdPM = dialog.findViewById(R.id.tv_IdPM);
        tvNameBook = dialog.findViewById(R.id.tv_nameBook);
        tvSoLuong = dialog.findViewById(R.id.tv_soLuong);
        tvGia = dialog.findViewById(R.id.tv_gia);
        tvIdPMCT.setText("Mã PMCT: "+obj.getIdPMCT());
        tvIdPM.setText("Mã phiếu mượn: "+obj.getIdPM());
        tvNameBook.setText("Tên sách mượn: "+obj.getTitleBook());
        tvSoLuong.setText("Số lượng sách mượn: "+obj.getSoLuong());
        tvGia.setText("Giá: "+obj.getGia());
        dialog.show();
    }
}
