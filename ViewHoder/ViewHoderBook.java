package vn.edu.poly.duanmau.ViewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.duanmau.R;

public class ViewHoderBook extends RecyclerView.ViewHolder {
    public TextView tvTitleBook;
    public TextView tvPriceBook;
    public TextView tvChiTiet;


    public ViewHoderBook(@NonNull View itemView) {
        super(itemView);
        tvTitleBook = itemView.findViewById(R.id.tv_titleBook);
        tvPriceBook = itemView.findViewById(R.id.tv_priceBook);
        tvChiTiet = itemView.findViewById(R.id.tv_chiTiet);
        /// book 1



    }
}
