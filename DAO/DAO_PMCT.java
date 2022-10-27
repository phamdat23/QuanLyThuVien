package vn.edu.poly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Database.MyHelper;
import vn.edu.poly.duanmau.Model.PMCT;
import vn.edu.poly.duanmau.Model.ThongKe;

public class DAO_PMCT {
    public static final String CREATE_TB_PMCT = "CREATE TABLE PMCT(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IDPHIEUMUON INTEGER NOT NULL, IDBOOK INTEGER NOT NULL, SOLUONG INTEGER NOT NULL,GIAMUON INTEGER NOT NULL, FOREIGN KEY (IDPHIEUMUON) REFERENCES PHIEUMUON(IDPHIEUMUON) ,FOREIGN KEY (IDBOOK) REFERENCES BOOK(IDBOOK))";
    public static final String TB_NAME = "PMCT";
    public static final String ID = "ID";
    public static final String IDPHIEUMUON = "IDPHIEUMUON";
    public static final String IDBOOK = "IDBOOK";
    public static final String GIAMUON = "GIAMUON";
    public static final String SOLUONG = "SOLUONG";

    SQLiteDatabase database;
    MyHelper myHelper;

    public DAO_PMCT(Context context) {
        myHelper = new MyHelper(context);
    }

    public void opend() {
        database = myHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
    public ArrayList<PMCT> selectAll(){
        ArrayList<PMCT> list = new ArrayList<>();
        String select2="SELECT PMCT.ID, PMCT.IDPHIEUMUON,PMCT.IDBOOK, PMCT.SOLUONG, PMCT.GIAMUON, BOOK.TITLE FROM PMCT JOIN BOOK ON PMCT.IDBOOK = BOOK.IDBOOK JOIN PHIEUMUON ON PMCT.IDPHIEUMUON = PHIEUMUON.IDPHIEUMUON";
        Cursor cursor =database.rawQuery(select2, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            PMCT obj = new PMCT();
            obj.setIdPMCT(cursor.getInt(0));
            obj.setIdPM(cursor.getInt(1));
            obj.setIdBook(cursor.getInt(2));
            obj.setSoLuong(cursor.getInt(3));
            obj.setGia(cursor.getInt(4));
            obj.setTitleBook(cursor.getString(5));
            list.add(obj);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<ThongKe> TongDoanhThu(){
        ArrayList<ThongKe> list = new ArrayList<>();
        String select="SELECT SUM(PMCT.GIAMUON) AS 'DOANHTHU' FROM PMCT JOIN PHIEUMUON ON PMCT.IDPHIEUMUON = PHIEUMUON.IDPHIEUMUON ";
        Cursor cursor = database.rawQuery(select, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ThongKe obj = new ThongKe();
            obj.setTongDoanhThu(cursor.getInt(0));
            list.add(obj);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<ThongKe> DoanhThuTheoThang(){
        ArrayList<ThongKe> list = new ArrayList<>();
        String select="SELECT SUM(PMCT.GIAMUON),DATE('NOW','START OF MONTH'), DATE('NOW', 'START OF MONTH','+1 MONTH','-1 DAY') FROM PMCT JOIN PHIEUMUON ON PMCT.IDPHIEUMUON = PHIEUMUON.IDPHIEUMUON \n" +
                "WHERE PHIEUMUON.NGAYMUON>DATE('NOW','START OF MONTH') AND PHIEUMUON.NGAYMUON<DATE('NOW', 'START OF MONTH','+1 MONTH','-1 DAY')";
        Cursor cursor = database.rawQuery(select, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ThongKe obj = new ThongKe();
            obj.setTongDoanhThu(cursor.getInt(0));
            list.add(obj);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public double doanhThu(String s, String e){
        String select ="SELECT SUM(PMCT.GIAMUON) FROM PMCT JOIN PHIEUMUON ON PMCT.IDPHIEUMUON=PHIEUMUON.IDPHIEUMUON WHERE PHIEUMUON.NGAYMUON > '"+s+"'"+" AND PHIEUMUON.NGAYMUON < '"+e+"'";
        Cursor cursor = database.rawQuery(select, null);
        ThongKe obj = new ThongKe();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            obj.setTongDoanhThu(cursor.getInt(0));
            cursor.moveToNext();
        }
        return obj.getTongDoanhThu();
    }
    public double doanhThuNgay(){
        String select ="SELECT SUM(PMCT.GIAMUON) FROM PMCT JOIN PHIEUMUON ON PMCT.IDPHIEUMUON = PHIEUMUON.IDPHIEUMUON WHERE PHIEUMUON.NGAYMUON=DATE('NOW')";
        Cursor cursor = database.rawQuery(select, null);
        ThongKe obj = new ThongKe();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            obj.setTongDoanhThu(cursor.getInt(0));
            cursor.moveToNext();
        }
        return obj.getTongDoanhThu();
    }
    public long insertPMCT(PMCT obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDPHIEUMUON, obj.getIdPM());
        contentValues.put(IDBOOK, obj.getIdBook());
        contentValues.put(GIAMUON, obj.getGia());
        contentValues.put(SOLUONG, obj.getSoLuong());
        try {
            if(database.insert(TB_NAME, null, contentValues)==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updatePMCT(PMCT obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDPHIEUMUON, obj.getIdPM());
        contentValues.put(IDBOOK, obj.getIdBook());
        contentValues.put(GIAMUON, obj.getGia());
        contentValues.put(SOLUONG, obj.getSoLuong());
        try {

            if(database.update(TB_NAME,contentValues, ID+"=?", new String[]{obj.getIdPMCT()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int delete(PMCT obj){
        try {
            if(database.delete(TB_NAME, ID+"=?",new String[]{obj.getIdPMCT()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
}
