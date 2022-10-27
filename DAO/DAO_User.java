package vn.edu.poly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.net.PortUnreachableException;
import java.util.ArrayList;

import vn.edu.poly.duanmau.Database.MyHelper;
import vn.edu.poly.duanmau.Model.User;

public class DAO_User {
    SQLiteDatabase database;
    MyHelper myHelper;
    public static final String CREATE_TB_USER ="CREATE TABLE USER(IDUSER TEXT NOT NULL PRIMARY KEY, FULLNAME TEXT NOT NULL, PHONE TEXT NOT NULL, DATES DATE);";
    public static final String TB_NAME="USER";
    public static final String IDUSER="IDUSER";
    public static final String FULLNAME="FULLNAME";
    public static final String PHONE="PHONE";
    public static final String DATES="DATES";
    public static final String EMAIL="EMAIL";
    public DAO_User(Context context){
        myHelper = new MyHelper(context);
    }
    public void opend(){
        database=myHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }
    public long insertUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAO_User.IDUSER,user.getIdUser());
        contentValues.put(DAO_User.FULLNAME,user.getFullName());
        contentValues.put(DAO_User.PHONE,user.getPhone());
        contentValues.put(DAO_User.DATES, user.getDates());

        try {
            long res =database.insert(DAO_User.TB_NAME,null, contentValues);
            if(res<0){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public ArrayList<User> getAll(){
        ArrayList<User> list_user= new ArrayList<>();
        String select="SELECT * FROM "+DAO_User.TB_NAME;
        Cursor cursor = database.rawQuery(select, null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                User user = new User();
                user.setIdUser(cursor.getString(0));
                user.setFullName(cursor.getString(1));
                user.setPhone(cursor.getString(2));
                user.setDates(cursor.getString(3));
                list_user.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list_user;
    }
    public int updateUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAO_User.IDUSER,user.getIdUser());
        contentValues.put(DAO_User.FULLNAME,user.getFullName());
        contentValues.put(DAO_User.PHONE,user.getPhone());
        contentValues.put(DAO_User.DATES, user.getDates());

        try {
            if(database.update(DAO_User.TB_NAME, contentValues, DAO_User.IDUSER+"=?", new String[]{DAO_User.IDUSER})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int deleteUser(User user){
        try {
            if(database.delete(DAO_User.TB_NAME,DAO_User.IDUSER+"=?",new String[]{DAO_User.IDUSER})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
}
