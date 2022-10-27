package vn.edu.poly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Database.MyHelper;
import vn.edu.poly.duanmau.Model.Admin;

public class DAO_Admin {
    SQLiteDatabase database;
    MyHelper myHelper;
    public static final String CREATE_TB_ADMIN = "CREATE TABLE ADMIN(IDAMIN TEXT NOT NULL PRIMARY KEY, FULLNAME TEXT NOT NULL, PHONE TEXT NOT NULL, PASSWORD TEXT NOT NULL);";
    public static final String TB_NAME = "ADMIN";
    public static final String IDAMIN = "IDAMIN";
    public static final String FULLNAME = "FULLNAME";
    public static final String PHONE = "PHONE";
    public static final String PASSWORD = "PASSWORD";

    public DAO_Admin(Context context) {
        myHelper = new MyHelper(context);
    }

    public void opend() {
        database = myHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
    public ArrayList<Admin> getAll(){
        ArrayList<Admin> list_ac = new ArrayList<>();
        String select_tb_user = "SELECT * FROM " + TB_NAME;
        Cursor cursor = database.rawQuery(select_tb_user, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Admin obj = new Admin();
                obj.setIdAdmin(cursor.getString(0));
                obj.setFullName(cursor.getString(1));
                obj.setPhone(cursor.getString(2));
                obj.setPassword(cursor.getString(3));
                list_ac.add(obj);
                cursor.moveToNext();
            }
        }
        return list_ac;
    }
    public long insertAdmin(Admin admin) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAO_Admin.IDAMIN, admin.getIdAdmin());
        contentValues.put(DAO_Admin.FULLNAME, admin.getFullName());
        contentValues.put(DAO_Admin.PHONE, admin.getPhone());
        contentValues.put(DAO_Admin.PASSWORD, admin.getPassword());
        try {
            if (database.insert(DAO_Admin.TB_NAME, null, contentValues) == -1) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
    public int changePass(Admin admin){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAO_Admin.PASSWORD, admin.getPassword());
        try {
            if (database.update(DAO_Admin.TB_NAME, contentValues,DAO_Admin.IDAMIN+"=?",new String[]{DAO_Admin.IDAMIN}) == -1) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

}
