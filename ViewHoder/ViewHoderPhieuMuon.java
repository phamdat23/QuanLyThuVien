package vn.edu.poly.duanmau.ViewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.duanmau.R;

public class ViewHoderPhieuMuon extends RecyclerView.ViewHolder {
    public TextView tvIdPM;
    public TextView ngayMuon;
    public ImageView imgUpdatePhieuMuon;
    public ImageView imgDeletePhieuMuon;
    public TextView tvXemThemPhieuMuon;
    public ViewHoderPhieuMuon(@NonNull View itemView) {
        super(itemView);
        tvIdPM = itemView.findViewById(R.id.tv_idPM);
        ngayMuon = itemView.findViewById(R.id.ngay_muon);
        imgUpdatePhieuMuon = itemView.findViewById(R.id.img_updatePhieuMuon);
        imgDeletePhieuMuon = itemView.findViewById(R.id.img_deletePhieuMuon);
        tvXemThemPhieuMuon = itemView.findViewById(R.id.tv_xemThemPhieuMuon);

    }
}
