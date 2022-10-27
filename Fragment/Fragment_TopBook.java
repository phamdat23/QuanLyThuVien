package vn.edu.poly.duanmau.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Adapter.Adapter_TopSach;
import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.Model.TopSach;
import vn.edu.poly.duanmau.R;

public class Fragment_TopBook extends Fragment {
    private RecyclerView rcvListTopBook;

    DAO_Book dao_book;
    ArrayList<TopSach> list;
    Context context;
    Adapter_TopSach adapter_topSach;
    int top=1000;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_book, container, false);
        context = getContext();
        dao_book = new DAO_Book(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListTopBook = view.findViewById(R.id.rcv_list_topBook);
        dao_book.opend();
        list= dao_book.topBook();
        adapter_topSach = new Adapter_TopSach(dao_book, list);
        rcvListTopBook.setAdapter(adapter_topSach);




    }
}
