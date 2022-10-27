package vn.edu.poly.duanmau.ViewHoder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.duanmau.R;

public class ViewHoderTopSach extends RecyclerView.ViewHolder {
    public TextView tvTitleBook;
    public TextView tvPriceBook;
    public TextView tvLuotMuon;

    public ViewHoderTopSach(@NonNull View itemView) {
        super(itemView);
        tvTitleBook = itemView.findViewById(R.id.tv_titleBook);
        tvPriceBook = itemView.findViewById(R.id.tv_priceBook);
        tvLuotMuon = itemView.findViewById(R.id.tv_luotMuon);
    }
}
