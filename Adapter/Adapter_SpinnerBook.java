package vn.edu.poly.duanmau.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_PhieuMuon;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.PhieuMuon;
import vn.edu.poly.duanmau.R;

public class Adapter_SpinnerBook extends BaseAdapter {
    DAO_Book dao_book;
    ArrayList<Book> list;

    public Adapter_SpinnerBook(DAO_Book dao_book, ArrayList<Book> list) {
        this.dao_book = dao_book;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Book obj = list.get(position);
        return obj;
    }

    @Override
    public long getItemId(int position) {
        Book obj = list.get(position);
        return obj.getIdBook();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row ;
        if(convertView==null){
            row = View.inflate(parent.getContext(), R.layout.item_spinner, null);
        }else{
            row= convertView;
        }
        dao_book = new DAO_Book(parent.getContext());
        dao_book.opend();
        list = dao_book.selectAll();
        Book obj = list.get(position);
        TextView tvId = row.findViewById(R.id.tv_id);
        TextView tvName = row.findViewById(R.id.tv_name);
        tvId.setText("ID sách: "+ obj.getIdBook());
        tvName.setText("| "+obj.getTitleBook());
        return row;
    }
}
