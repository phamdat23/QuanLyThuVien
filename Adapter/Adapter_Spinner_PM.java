package vn.edu.poly.duanmau.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.duanmau.DAO.DAO_PhieuMuon;
import vn.edu.poly.duanmau.Model.PhieuMuon;
import vn.edu.poly.duanmau.R;

public class Adapter_Spinner_PM extends BaseAdapter {
    DAO_PhieuMuon dao_phieuMuon;
    ArrayList<PhieuMuon> list;

    public Adapter_Spinner_PM(DAO_PhieuMuon dao_phieuMuon, ArrayList<PhieuMuon> list) {
        this.dao_phieuMuon = dao_phieuMuon;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        PhieuMuon obj = list.get(position);
        return obj;
    }

    @Override
    public long getItemId(int position) {
        PhieuMuon obj = list.get(position);
        return obj.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row ;
        if(convertView==null){
            row = View.inflate(parent.getContext(), R.layout.item_spinner, null);
        }else{
            row= convertView;
        }
        dao_phieuMuon = new DAO_PhieuMuon(parent.getContext());
        dao_phieuMuon.opend();
        list = dao_phieuMuon.selectAll();
        PhieuMuon obj = list.get(position);
        TextView tvId = row.findViewById(R.id.tv_id);
        TextView tvName = row.findViewById(R.id.tv_name);
        tvId.setText("Mã phiếu mượn: "+ obj.getId());
        tvName.setText("");
        return row;
    }
}
