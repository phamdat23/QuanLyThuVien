package vn.edu.poly.duanmau.ViewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.duanmau.R;

public class ViewHoder_Category extends RecyclerView.ViewHolder {
    public ImageView imgCategory;
    public TextView tvTitleCategory;
    public ImageView imgEdit;
    public ImageView imgDelete;

    public ViewHoder_Category(@NonNull View itemView) {
        super(itemView);
        imgCategory = itemView.findViewById(R.id.img_category);
        tvTitleCategory = itemView.findViewById(R.id.tv_titleCategory);
        imgEdit = itemView.findViewById(R.id.img_edit);
        imgDelete = itemView.findViewById(R.id.img_delete);

    }
}
