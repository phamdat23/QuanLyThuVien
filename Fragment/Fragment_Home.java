package vn.edu.poly.duanmau.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Adapter.AdapterCategoryBook1;
import vn.edu.poly.duanmau.Adapter.AdapterCategoryBook2;
import vn.edu.poly.duanmau.Adapter.AdapterCategoryBook3;
import vn.edu.poly.duanmau.Adapter.AdapterCategoryBook4;
import vn.edu.poly.duanmau.Adapter.Adapter_Book;
import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.MainActivity;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.R;

public class Fragment_Home extends Fragment {
    private TextView tvTenTheLoai1;
    private RecyclerView rcvListBook1;
    private TextView tvTenTheLoai2;
    private RecyclerView rcvListBook2;
    private TextView tvTenTheLoai3;
    private RecyclerView rcvListBook3;
    private RecyclerView rcvListKhac;
    private TextView tvKhac;
    DAO_Book dao_book;
    DAO_BookCategory dao_bookCategory;
    ArrayList<Category> listCategory;
    ArrayList<Book> listBook1 = new ArrayList<>();
    ArrayList<Book> listBook2= new ArrayList<>();
    ArrayList<Book> listBook3= new ArrayList<>();
    ArrayList<Book> listBook4= new ArrayList<>();
    AdapterCategoryBook1 adapterCategoryBook1;
    AdapterCategoryBook2 adapterCategoryBook2;
    AdapterCategoryBook3 adapterCategoryBook3;
    AdapterCategoryBook4 adapterCategoryBook4;
    Context context;
    String TAG = "zzzzzzzzzzzz";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        dao_book = new DAO_Book(context);
        dao_bookCategory = new DAO_BookCategory(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao_book.opend();
        dao_bookCategory.opend();
        listCategory = dao_bookCategory.selectAll();
        anhXa(view);
        rcvListKhac = view.findViewById(R.id.rcv_list_khac);
        if (listCategory.size() >= 1) {
            listBook1 = dao_book.selectCategory1(listCategory);
        }
        if (listCategory.size() >= 2) {
            listBook2 = dao_book.selectCategory2(listCategory);
        }
        if (listCategory.size() >= 3) {
            listBook3 = dao_book.selectCategory3(listCategory);
        }
        if (listCategory.size() >= 4) {
            listBook4 = dao_book.selectCategory4(listCategory);
        }
        if (listBook1.size()>0) {
            tvTenTheLoai1.setText(listCategory.get(0).getTitle() + ":");
            adapterCategoryBook1 = new AdapterCategoryBook1(dao_book, listBook1, listCategory, dao_bookCategory);
            rcvListBook1.setAdapter(adapterCategoryBook1);
            rcvListBook1.setItemAnimator(new DefaultItemAnimator());
        }
        if (listBook2.size()>0) {
            tvTenTheLoai2.setText(listCategory.get(1).getTitle() + ":");
            adapterCategoryBook2 = new AdapterCategoryBook2(dao_book, listBook2, listCategory, dao_bookCategory);
            rcvListBook2.setAdapter(adapterCategoryBook2);
        }
        if (listBook3.size()>0) {
            tvTenTheLoai3.setText(listCategory.get(2).getTitle() + ":");
            adapterCategoryBook3 = new AdapterCategoryBook3(dao_book, listBook3, listCategory, dao_bookCategory);
            rcvListBook3.setAdapter(adapterCategoryBook3);
        }
        if (listBook4.size()>0) {
            tvKhac.setText("Thể Loại khác:");
            adapterCategoryBook4 = new AdapterCategoryBook4(dao_book, listBook4, listCategory, dao_bookCategory);
            rcvListKhac.setAdapter(adapterCategoryBook4);
            rcvListKhac.setItemAnimator(new DefaultItemAnimator());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (listBook1.size()>0) {
            tvTenTheLoai1.setText(listCategory.get(0).getTitle() + ":");
            adapterCategoryBook1 = new AdapterCategoryBook1(dao_book, listBook1, listCategory, dao_bookCategory);
            rcvListBook1.setAdapter(adapterCategoryBook1);
            rcvListBook1.setItemAnimator(new DefaultItemAnimator());
        }
        if (listBook2.size()>0) {
            tvTenTheLoai2.setText(listCategory.get(1).getTitle() + ":");
            adapterCategoryBook2 = new AdapterCategoryBook2(dao_book, listBook2, listCategory, dao_bookCategory);
            rcvListBook2.setAdapter(adapterCategoryBook2);
        }
        if (listBook3.size()>0) {
            tvTenTheLoai3.setText(listCategory.get(2).getTitle() + ":");
            adapterCategoryBook3 = new AdapterCategoryBook3(dao_book, listBook3, listCategory, dao_bookCategory);
            rcvListBook3.setAdapter(adapterCategoryBook3);
        }
        if (listBook4.size()>0) {
            tvKhac.setText("Thể Loại khác:");
            adapterCategoryBook4 = new AdapterCategoryBook4(dao_book, listBook4, listCategory, dao_bookCategory);
            rcvListKhac.setAdapter(adapterCategoryBook4);
            rcvListKhac.setItemAnimator(new DefaultItemAnimator());
        }
    }

    public void anhXa(View view) {
        tvTenTheLoai1 = view.findViewById(R.id.tv_tenTheLoai1);
        rcvListBook1 = view.findViewById(R.id.rcv_listBook1);
        tvTenTheLoai2 = view.findViewById(R.id.tv_tenTheLoai2);
        rcvListBook2 = view.findViewById(R.id.rcv_listBook2);
        tvTenTheLoai3 = view.findViewById(R.id.tv_tenTheLoai3);
        rcvListBook3 = view.findViewById(R.id.rcv_listBook3);
        rcvListKhac = view.findViewById(R.id.rcv_list_khac);
        tvKhac = view.findViewById(R.id.tv_khac);
    }

}
