package vn.edu.poly.duanmau.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_User;
import vn.edu.poly.duanmau.Model.User;
import vn.edu.poly.duanmau.R;

public class Adapter_Spinner_User extends BaseAdapter {
    DAO_User dao_user;
    ArrayList<User> list;

    public Adapter_Spinner_User(DAO_User dao_user, ArrayList<User> list) {
        this.dao_user = dao_user;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        User user= list.get(position);
        return user;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row =View.inflate(parent.getContext(), R.layout.item_spinner, null);
        }else{
            row= convertView;
        }
        dao_user = new DAO_User(parent.getContext());
        dao_user.opend();
        list= dao_user.getAll();
        User user = list.get(position);
        TextView tvId = row.findViewById(R.id.tv_id);
        TextView tv_name = row.findViewById(R.id.tv_name);
        tv_name.setText("|"+user.getFullName());
        tvId.setText("Mã thành viên: "+user.getIdUser());
        return row;
    }
}
