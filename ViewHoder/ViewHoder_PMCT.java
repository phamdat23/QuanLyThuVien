package vn.edu.poly.duanmau.ViewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.duanmau.R;

public class ViewHoder_PMCT extends RecyclerView.ViewHolder {
    public TextView tvIdPMCT;
    public TextView tvTitleBook;
    public ImageView imgUpdatePMCT;
    public ImageView imgDeletePMCT;
    public TextView tvXemThemPMCT;
    public ViewHoder_PMCT(@NonNull View itemView) {
        super(itemView);
        tvIdPMCT = itemView.findViewById(R.id.tv_idPMCT);
        tvTitleBook = itemView.findViewById(R.id.tv_title_book);
        imgUpdatePMCT = itemView.findViewById(R.id.img_updatePMCT);
        imgDeletePMCT = itemView.findViewById(R.id.img_deletePMCT);
        tvXemThemPMCT = itemView.findViewById(R.id.tv_xemThemPMCT);

    }
}
