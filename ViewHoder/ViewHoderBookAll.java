package vn.edu.poly.duanmau.ViewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.duanmau.R;

public class ViewHoderBookAll extends RecyclerView.ViewHolder {
    public TextView tvTitleBook2;
    public TextView tvPriceBook2;
    public TextView tvChiTiet2;
    public ImageView imgUpdateBook;
    public ImageView imgDeleteBook;
    public ViewHoderBookAll(@NonNull View itemView) {
        super(itemView);
        tvTitleBook2 = itemView.findViewById(R.id.tv_titleBook2);
        tvPriceBook2 = itemView.findViewById(R.id.tv_priceBook2);
        tvChiTiet2 = itemView.findViewById(R.id.tv_chiTiet2);
        imgUpdateBook = itemView.findViewById(R.id.img_updateBook);
        imgDeleteBook = itemView.findViewById(R.id.img_deleteBook);
    }
}
