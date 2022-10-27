package vn.edu.poly.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.R;
import vn.edu.poly.duanmau.ViewHoder.ViewHoder_Category;

public class Adapter_Category extends RecyclerView.Adapter<ViewHoder_Category> {
    DAO_BookCategory dao_bookCategory;
    ArrayList<Category> listCategory;
    Context context;

    public Adapter_Category(DAO_BookCategory dao_bookCategory, ArrayList<Category> listCategory) {
        this.dao_bookCategory = dao_bookCategory;
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public ViewHoder_Category onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_loai_sach, parent, false);
        ViewHoder_Category viewHoder_category = new ViewHoder_Category(row);
        context = parent.getContext();
        dao_bookCategory = new DAO_BookCategory(parent.getContext());
        return viewHoder_category;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder_Category holder, int position) {
        dao_bookCategory.opend();
        listCategory = dao_bookCategory.selectAll();
        final int index = position;
        Category category = listCategory.get(index);
        holder.tvTitleCategory.setText(category.getTitle());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategory(category, index);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategory(category, index);
            }
        });
    }
    public void insertCategory(Context context){
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_loai_sach);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        TextInputLayout input_title = dialog.findViewById(R.id.input_titlecategoryAdd);
        MaterialButton btn_addCategory= dialog.findViewById(R.id.btn_addCategory);
        btn_addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_title.getEditText().getText().toString().trim().isEmpty()){
                    input_title.setError("Không được để trống tiêu đề");
                }else{
                    Category category = new Category();
                    category.setTitle(input_title.getEditText().getText().toString().trim());
                    if(dao_bookCategory.insertCategory(category)>0){
                        listCategory.clear();
                        listCategory.addAll(dao_bookCategory.selectAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thêm mới thành công" , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Thêm mới thành công" , Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();

    }

    public void updateCategory(Category category, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_loai_sach);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        TextInputLayout input_titleUp = dialog.findViewById(R.id.input_titleUp);
        MaterialButton btn_updateCategory = dialog.findViewById(R.id.btn_updateCategory);
        input_titleUp.getEditText().setText(category.getTitle());
        btn_updateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_titleUp.getEditText().getText().toString().trim().isEmpty()) {
                    input_titleUp.setError("Không được để trống tiêu đề");
                } else {
                    input_titleUp.setError("");
                    category.setTitle(input_titleUp.getEditText().getText().toString().trim());
                    if (dao_bookCategory.updateCategory(category) > 0) {
                        listCategory.set(index, category);
                        notifyItemChanged(index);
                        Toast.makeText(context, "Cập hật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();
    }

    public void deleteCategory(Category category, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa thể loại sách");
        builder.setMessage("Bạn có muốn xóa " + category.getTitle());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dao_bookCategory.delete(category)> 0) {
                    listCategory.remove(index);
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

    @Override
    public int getItemCount() {
        return listCategory == null ? 0 : listCategory.size();
    }
}
