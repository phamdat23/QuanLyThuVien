package vn.edu.poly.duanmau.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Adapter.Adapter_Category;
import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.R;

public class Fragment_categoryBook extends Fragment {
    private RecyclerView rcvListCategoryBook;
    private FloatingActionButton fabAddCategoryBook;
    Context context;
    DAO_BookCategory dao_bookCategory;
    Adapter_Category adapter_category;
    ArrayList<Category> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        context = getContext();
        dao_bookCategory = new DAO_BookCategory(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListCategoryBook = view.findViewById(R.id.rcv_list_categoryBook);
        fabAddCategoryBook = view.findViewById(R.id.fab_add_categoryBook);
        dao_bookCategory.opend();
        list = dao_bookCategory.selectAll();
        adapter_category = new Adapter_Category(dao_bookCategory, list);
        rcvListCategoryBook.setAdapter(adapter_category);
        fabAddCategoryBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter_category.insertCategory(getContext());
            }
        });

    }

}
