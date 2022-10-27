package vn.edu.poly.duanmau.ViewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.poly.duanmau.R;

public class ViewHoderUser extends RecyclerView.ViewHolder {
   public TextView tvFullNameUser;
    public TextView tvPhoneNumberUser;
    public ImageView imgUpdateUser;
    public ImageView imgDeleteUser;
    public TextView tv_chiTietUser;
    public ViewHoderUser(@NonNull View itemView) {
        super(itemView);
        tvFullNameUser = itemView.findViewById(R.id.tv_fullNameUser);
        tvPhoneNumberUser = itemView.findViewById(R.id.tv_phoneNumberUser);
        imgUpdateUser = itemView.findViewById(R.id.img_updateUser);
        imgDeleteUser = itemView.findViewById(R.id.img_deleteUser);
        tv_chiTietUser= itemView.findViewById(R.id.tv_chiTietUser);

    }
}
