package vn.edu.poly.duanmau.Fragment;

import static android.widget.LinearLayout.VERTICAL;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Adapter.AdapterSpinnerCategory;
import vn.edu.poly.duanmau.Adapter.Adapter_Book;
import vn.edu.poly.duanmau.Adapter.Adapter_Category;
import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.R;

public class Fragment_Book extends Fragment {

    private RecyclerView rcv_listBook;
    private FloatingActionButton fabAddBook;
    DAO_BookCategory dao_bookCategory;
    AdapterSpinnerCategory adapterSpinnerCategory;
    ArrayList<Category> listCategory;
    ArrayList<Book> listBook;
    DAO_Book dao_book;
    Adapter_Book adapter_book;
    Context context;
    String TAG = "zzzzzzzzzzzz";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        context = getContext();
        dao_book = new DAO_Book(context);
        dao_bookCategory = new DAO_BookCategory(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_listBook = view.findViewById(R.id.rcv_listBook);
        dao_book.opend();
        dao_bookCategory.opend();
        listBook = dao_book.selectAll();
        listCategory = dao_bookCategory.selectAll();
        adapterSpinnerCategory = new AdapterSpinnerCategory(listCategory);
        adapter_book = new Adapter_Book(dao_bookCategory, adapterSpinnerCategory, listCategory, listBook, dao_book);
        rcv_listBook.setAdapter(adapter_book);
        fabAddBook = view.findViewById(R.id.fab_add_book);
        Log.e(TAG, "dialogAddBook: " + listCategory.size());
        fabAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddBook();
                listBook = dao_book.selectAll();
                listCategory = dao_bookCategory.selectAll();
                adapter_book = new Adapter_Book(dao_bookCategory, adapterSpinnerCategory, listCategory, listBook, dao_book);
                rcv_listBook.setAdapter(adapter_book);
            }
        });
    }

    private Spinner spinnerCatergory;
    private TextInputLayout inputTitleBook;
    private TextInputLayout inputSoLuong;
    private TextInputLayout inputGia;
    private TextInputLayout inputTacGia;
    private MaterialButton btnAddBook;

    public void dialogAddBook() {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialoh_add_book);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinnerCatergory = dialog.findViewById(R.id.spinner_catergory);
        inputTitleBook = dialog.findViewById(R.id.input_title_book);
        inputSoLuong = dialog.findViewById(R.id.input_soLuong);
        inputGia = dialog.findViewById(R.id.input_gia);
        inputTacGia = dialog.findViewById(R.id.input_tacGia);
        btnAddBook = dialog.findViewById(R.id.btn_addBook);
        spinnerCatergory.setAdapter(adapterSpinnerCategory);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check() == true) {
                    Book book = new Book();
                    Category category = (Category) spinnerCatergory.getSelectedItem();
                    book.setTitleBook(inputTitleBook.getEditText().getText().toString().trim());
                    book.setGia(Double.parseDouble(inputGia.getEditText().getText().toString().trim()));
                    book.setTacGia(inputTacGia.getEditText().getText().toString().trim());
                    book.setSoLuong(Integer.parseInt(inputSoLuong.getEditText().getText().toString().trim()));
                    book.setIdCategory(category.getId());
                    Log.e(TAG, "onClick: " + category.getId());
                    book.setTitleCategory(category.getTitle());
                    Log.e(TAG, "onClick: " + category.getTitle());
                    if (dao_book.insertBook(book) > 0) {
                        listBook.clear();
                        listBook.addAll(dao_book.selectAll());
                        adapter_book.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm mới thất bại", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();
    }

    public boolean check() {
        if (inputTitleBook.getEditText().getText().toString().trim().isEmpty() ||
                inputGia.getEditText().getText().toString().trim().isEmpty() ||
                inputTacGia.getEditText().getText().toString().trim().isEmpty() ||
                inputSoLuong.getEditText().getText().toString().trim().isEmpty()) {
            if (inputTitleBook.getEditText().getText().toString().trim().isEmpty()) {
                inputTitleBook.setError("Tên sách không được để trống");
            } else {
                inputTitleBook.setError("");
            }
            if (inputSoLuong.getEditText().getText().toString().trim().isEmpty()) {
                inputSoLuong.setError("Số lượng sách không được để trống");
            } else {
                inputSoLuong.setError("");
            }
            if (inputGia.getEditText().getText().toString().trim().isEmpty()) {
                inputGia.setError("Giá mượn sách không được để trống");
            } else {
                inputGia.setError("");
            }
            if (inputTacGia.getEditText().getText().toString().trim().isEmpty()) {
                inputTacGia.setError("Tên tác giả không được để trống");
            } else {
                inputTacGia.setError("");
            }
            return false;
        } else {
            return true;
        }
    }
}
