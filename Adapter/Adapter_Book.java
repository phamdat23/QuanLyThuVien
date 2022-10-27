package vn.edu.poly.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.R;
import vn.edu.poly.duanmau.ViewHoder.ViewHoderBook;
import vn.edu.poly.duanmau.ViewHoder.ViewHoderBookAll;

public class Adapter_Book extends RecyclerView.Adapter<ViewHoderBookAll> {
    DAO_BookCategory dao_bookCategory;
    AdapterSpinnerCategory adapterSpinnerCategory;
    ArrayList<Category> listCategory;
    ArrayList<Book> listBook;
    DAO_Book dao_book;
    Context context;

    public Adapter_Book(DAO_BookCategory dao_bookCategory, AdapterSpinnerCategory adapterSpinnerCategory, ArrayList<Category> listCategory, ArrayList<Book> listBook, DAO_Book dao_book) {
        this.dao_bookCategory = dao_bookCategory;
        this.adapterSpinnerCategory = adapterSpinnerCategory;
        this.listCategory = listCategory;
        this.listBook = listBook;
        this.dao_book = dao_book;
    }

    @NonNull
    @Override
    public ViewHoderBookAll onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_book2, parent, false);
        ViewHoderBookAll viewHoderBook = new ViewHoderBookAll(row);
        context = parent.getContext();
        dao_book = new DAO_Book(context);
        dao_bookCategory = new DAO_BookCategory(context);
        dao_book.opend();
        dao_bookCategory.opend();
        return viewHoderBook;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderBookAll holder, int position) {
        final int index = position;
        listBook = dao_book.selectAll();
        listCategory = dao_bookCategory.selectAll();
        Book book = listBook.get(index);
        holder.tvTitleBook2.setText(book.getTitleBook());
        holder.tvPriceBook2.setText("Giá mượn: " + book.getGia());
        holder.imgUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(book, index);
            }
        });
        holder.imgDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(book, index);
            }
        });
        holder.tvChiTiet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChiTiet(book);
            }
        });
    }


    private Spinner spinnerCatergoryUp;
    private TextInputLayout inputTitleBookUp;
    private TextInputLayout inputSoLuongUp;
    private TextInputLayout inputGiaUp;
    private TextInputLayout inputTacGiaUp;
    private MaterialButton btnUpBook;

    public void dialogUpdate(Book book, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_book);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinnerCatergoryUp = dialog.findViewById(R.id.spinner_catergoryUp);
        inputTitleBookUp = dialog.findViewById(R.id.input_title_bookUp);
        inputSoLuongUp = dialog.findViewById(R.id.input_soLuongUp);
        inputGiaUp = dialog.findViewById(R.id.input_giaUp);
        inputTacGiaUp = dialog.findViewById(R.id.input_tacGiaUp);
        btnUpBook = dialog.findViewById(R.id.btn_UpBook);

        adapterSpinnerCategory = new AdapterSpinnerCategory(listCategory);
        spinnerCatergoryUp.setAdapter(adapterSpinnerCategory);
        try {
            for (int i = 0; i < listBook.size(); i++) {
                if (book.getIdCategory() == listCategory.get(i).getId()) {
                    spinnerCatergoryUp.setSelection(i);
                    spinnerCatergoryUp.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputTitleBookUp.getEditText().setText(book.getTitleBook());
        inputSoLuongUp.getEditText().setText(book.getSoLuong() + "");
        inputGiaUp.getEditText().setText(book.getGia() + "");
        inputTacGiaUp.getEditText().setText(book.getTacGia());
        btnUpBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkErrorUp() == true) {
                    Category category = (Category) spinnerCatergoryUp.getSelectedItem();
                    book.setTitleCategory(category.getTitle());
                    book.setIdCategory(category.getId());
                    book.setTitleBook(inputTitleBookUp.getEditText().getText().toString().trim());
                    book.setSoLuong(Integer.parseInt(inputSoLuongUp.getEditText().getText().toString().trim()));
                    book.setGia(Double.parseDouble(inputGiaUp.getEditText().getText().toString().trim()));
                    book.setTacGia(inputTacGiaUp.getEditText().getText().toString().trim());
                    if (dao_book.updateBook(book) > 0) {
                        listBook.set(index, book);
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

    public void dialogDelete(Book book, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa " + book.getTitleBook());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_book.deleteBook(book);
                if (res > 0) {
                    listBook.remove(index);
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

    public boolean checkErrorUp() {
        if (inputTitleBookUp.getEditText().getText().toString().trim().isEmpty() ||
                inputGiaUp.getEditText().getText().toString().trim().isEmpty() ||
                inputTacGiaUp.getEditText().getText().toString().trim().isEmpty() ||
                inputSoLuongUp.getEditText().getText().toString().trim().isEmpty()) {
            if (inputTitleBookUp.getEditText().getText().toString().trim().isEmpty()) {
                inputTitleBookUp.setError("Tên sách không được để trống");
            } else {
                inputTitleBookUp.setError("");
            }
            if (inputSoLuongUp.getEditText().getText().toString().trim().isEmpty()) {
                inputSoLuongUp.setError("Số lượng sách không được để trống");
            } else {
                inputSoLuongUp.setError("");
            }
            if (inputGiaUp.getEditText().getText().toString().trim().isEmpty()) {
                inputGiaUp.setError("Giá mượn sách không được để trống");
            } else {
                inputGiaUp.setError("");
            }
            if (inputTacGiaUp.getEditText().getText().toString().trim().isEmpty()) {
                inputTacGiaUp.setError("Tên tác giả không được để trống");
            } else {
                inputTacGiaUp.setError("");
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getItemCount() {
        return listBook == null ? 0 : listBook.size();
    }

    private TextView tvIdSach;
    private TextView tvTitleSach;
    private TextView tvTheLoai;
    private TextView tvTacGia;
    private TextView tvSoLuong;
    private TextView tvGia;


    public void dialogChiTiet(Book obj) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_chi_tiet_sach);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tvIdSach = dialog.findViewById(R.id.tv_idSach);
        tvTitleSach = dialog.findViewById(R.id.tv_titleSach);
        tvTheLoai = dialog.findViewById(R.id.tv_the_loai);
        tvTacGia = dialog.findViewById(R.id.tv_tacGia);
        tvSoLuong = dialog.findViewById(R.id.tv_soLuong);
        tvGia = dialog.findViewById(R.id.tv_gia);
        tvIdSach.setText("Mã sách: " + obj.getIdBook());
        tvTitleSach.setText("Tên sách: " + obj.getTitleBook());
        tvTheLoai.setText("Thể loại: " + obj.getIdCategory() + "|" + obj.getTitleCategory());
        tvTacGia.setText("Tác giả: " + obj.getTacGia());
        tvSoLuong.setText("Số lượng sẵn có: " + obj.getSoLuong());
        tvGia.setText("Giá mượn: " + obj.getGia() + "$");
        dialog.show();
    }

}
