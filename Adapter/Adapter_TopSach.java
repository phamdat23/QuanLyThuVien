package vn.edu.poly.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.Model.TopSach;
import vn.edu.poly.duanmau.R;
import vn.edu.poly.duanmau.ViewHoder.ViewHoderTopSach;

public class Adapter_TopSach extends RecyclerView.Adapter<ViewHoderTopSach> {
    DAO_Book dao_book;
    ArrayList<TopSach> list;

    Context context;

    public Adapter_TopSach(DAO_Book dao_book, ArrayList<TopSach> list) {
        this.dao_book = dao_book;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoderTopSach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_sach, parent, false);
        ViewHoderTopSach viewHoderTopSach = new ViewHoderTopSach(view);
        context= parent.getContext();
        dao_book = new DAO_Book(context);
        return viewHoderTopSach;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderTopSach holder, int position) {
        final int index = position;
        dao_book.opend();
        list = dao_book.topBook();
        TopSach obj = list.get(index);
        holder.tvTitleBook.setText(obj.getTitleBook());
        holder.tvPriceBook.setText("Giá: "+obj.getGia());
        holder.tvLuotMuon.setText("Lượt đã mượn: "+obj.getLuotMuon());
    }

    @Override
    public int getItemCount() {
        return list ==null?0:list.size();
    }
}
