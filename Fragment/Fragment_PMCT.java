package vn.edu.poly.duanmau.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;

import vn.edu.poly.duanmau.Adapter.Adapter_PMCT;
import vn.edu.poly.duanmau.Adapter.Adapter_SpinnerBook;
import vn.edu.poly.duanmau.Adapter.Adapter_Spinner_PM;
import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_PMCT;
import vn.edu.poly.duanmau.DAO.DAO_PhieuMuon;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.PMCT;
import vn.edu.poly.duanmau.Model.PhieuMuon;
import vn.edu.poly.duanmau.R;

public class Fragment_PMCT extends Fragment {
    private Spinner spinnerPM;
    private Spinner spinnerBook;
    private TextInputLayout inputSoLuong;
    private TextView tvGia;
    private MaterialButton btnAddPMCT;
    DAO_PMCT dao_pmct;
    ArrayList<PMCT> list;
    DAO_Book dao_book;
    DAO_PhieuMuon dao_phieuMuon;
    ArrayList<Book> listB;
    ArrayList<PhieuMuon> listPM;
    Adapter_PMCT adapter_pmct;
    Context context;
    private RecyclerView rcvListPMCT;
    private FloatingActionButton fabAddPMCT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pmct, container, false);
        context = getContext();
        dao_book = new DAO_Book(context);
        dao_pmct = new DAO_PMCT(context);
        dao_phieuMuon = new DAO_PhieuMuon(context);
        dao_book.opend();
        dao_pmct.opend();
        dao_phieuMuon.opend();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listB = dao_book.selectAll();
        listPM = dao_phieuMuon.selectAll();
        rcvListPMCT = view.findViewById(R.id.rcv_list_PMCT);
        fabAddPMCT = view.findViewById(R.id.fab_add_PMCT);
        list= dao_pmct.selectAll();
        adapter_pmct = new Adapter_PMCT(dao_pmct, list);
        rcvListPMCT.setAdapter(adapter_pmct);
        fabAddPMCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddPMCT();
                list= dao_pmct.selectAll();
                adapter_pmct = new Adapter_PMCT(dao_pmct, list);
                rcvListPMCT.setAdapter(adapter_pmct);
            }
        });


    }
    public void dialogAddPMCT(){
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_add_pmct);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinnerPM = dialog.findViewById(R.id.spinner_PM);
        spinnerBook = dialog.findViewById(R.id.spinner_Book);
        inputSoLuong = dialog.findViewById(R.id.input_soLuong);
        tvGia = dialog.findViewById(R.id.tv_gia);
        btnAddPMCT = dialog.findViewById(R.id.btn_addPMCT);
        Adapter_SpinnerBook adapter_spinnerBook = new Adapter_SpinnerBook(dao_book, listB);
        Adapter_Spinner_PM adapter_spinner_pm = new Adapter_Spinner_PM(dao_phieuMuon, listPM);
        spinnerBook.setAdapter(adapter_spinnerBook);
        spinnerPM.setAdapter(adapter_spinner_pm);
        PhieuMuon phieuMuon = (PhieuMuon) spinnerPM.getSelectedItem();
        btnAddPMCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputSoLuong.getEditText().getText().toString().trim().isEmpty()){
                    inputSoLuong.setError("Số lượng không được để trống");
                }else if(Integer.parseInt(inputSoLuong.getEditText().getText().toString().trim())<0){
                    inputSoLuong.setError("ố lượng phải lớn hơn 0");
                }else{
                    PhieuMuon phieuMuon = (PhieuMuon) spinnerPM.getSelectedItem();
                    Book book = (Book) spinnerBook.getSelectedItem();
                    PMCT obj = new PMCT();
                    obj.setIdPM(phieuMuon.getId());
                    obj.setIdBook(book.getIdBook());
                    obj.setTitleBook(book.getTitleBook());
                    obj.setSoLuong(Integer.parseInt(inputSoLuong.getEditText().getText().toString().trim()));
                    double price = Integer.parseInt(inputSoLuong.getEditText().getText().toString().trim())*book.getGia();
                    obj.setGia(price);
                    if(dao_pmct.insertPMCT(obj)>0){
                        list.clear();
                        list.addAll(dao_pmct.selectAll());
                        adapter_pmct.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm mới thành công" , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Lỗi" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
    }

}
