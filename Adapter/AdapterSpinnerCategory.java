package vn.edu.poly.duanmau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.R;

public class AdapterSpinnerCategory extends BaseAdapter {
    ArrayList<Category> list;

    public AdapterSpinnerCategory(ArrayList<Category> list) {
        this.list = list;
    }


    @Override
    public int getCount() {
        return list==null?0: list.size();
    }

    @Override
    public Object getItem(int position) {
        Category category = list.get(position);
        return category;
    }

    @Override
    public long getItemId(int position) {
        Category category = list.get(position);
        return category.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row =View.inflate(parent.getContext(), R.layout.item_spinner_category, null);
        }else{
            row = convertView;
        }
        Category category = list.get(position);
        TextView tv_id = row.findViewById(R.id.tv_id_category);
        TextView tv_title = row.findViewById(R.id.tv_title_category);
        tv_id.setText(category.getId()+"|");
        tv_title.setText(category.getTitle());
        return row;
    }
}
