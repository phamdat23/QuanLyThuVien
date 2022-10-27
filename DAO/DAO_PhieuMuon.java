package vn.edu.poly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Database.MyHelper;
import vn.edu.poly.duanmau.Model.PhieuMuon;

public class DAO_PhieuMuon {
    public static final String CREATE_TB_PHIEUMUON = "CREATE TABLE PHIEUMUON(IDPHIEUMUON INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,IDUSER TEXT NOT NULL, NGAYMUON DATE, NGAYTRA DATE,  FOREIGN KEY (IDUSER) REFERENCES USER(IDUSER));";
    public static final String TB_NAME = "PHIEUMUON";
    public static final String IDPHIEUMUON = "IDPHIEUMUON";
    public static final String IDUSER = "IDUSER";
    public static final String NGAYMUON = "NGAYMUON";
    public static final String NGAYTRA="NGAYTRA";
    SQLiteDatabase database;
    MyHelper myHelper;
    public DAO_PhieuMuon(Context context) {
        myHelper = new MyHelper(context);
    }
    public void opend() {
        database = myHelper.getWritableDatabase();
    }
    public void close() {
        database.close();
    }
    public ArrayList<PhieuMuon> selectAll(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        String select = "SELECT * FROM PHIEUMUON ";
        Cursor cursor = database.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                PhieuMuon obj = new PhieuMuon();
                obj.setId(cursor.getInt(0));
                obj.setMaTV(cursor.getString(1));
                obj.setNgayMuon(cursor.getString(2));
                obj.setNgayTra(cursor.getString(3));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public long insertPM(PhieuMuon obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDUSER, obj.getMaTV());
        contentValues.put(NGAYMUON, obj.getNgayMuon());
        try {
            if(database.insert(TB_NAME, null, contentValues)==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updatePM(PhieuMuon obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDUSER, obj.getMaTV());
        contentValues.put(NGAYMUON, obj.getNgayMuon());
        contentValues.put(NGAYTRA,obj.getNgayTra());
        try {
            if(database.update(TB_NAME, contentValues, IDPHIEUMUON+"=?", new String[]{obj.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return 1;
    }
    public int deletePM(PhieuMuon obj){
        try {
            if(database.delete(TB_NAME,IDPHIEUMUON+"=?", new String[]{obj.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

}
