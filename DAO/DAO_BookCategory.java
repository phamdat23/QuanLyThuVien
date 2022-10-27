package vn.edu.poly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Database.MyHelper;
import vn.edu.poly.duanmau.Model.Category;

public class DAO_BookCategory {
    public static final String CREATE_TB_BOOKCATEGORY="CREATE TABLE BOOKCATEGORY(IDCATEGORY INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, TITLE TEXT NOT NULL);";
    public static final String TB_NAME="BOOKCATEGORY";
    public static final String ID="IDCATEGORY";
    public static final String TITLE="TITLE";


    SQLiteDatabase database;
    MyHelper myHelper;
    public DAO_BookCategory(Context context){
        myHelper = new MyHelper(context);
    }

    public void opend(){
        database=myHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }
    public ArrayList<Category> selectAll(){
        ArrayList<Category> list = new ArrayList<>();
        String select ="SELECT * FROM "+TB_NAME;
        Cursor cursor = database.rawQuery(select, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Category obj = new Category();
            obj.setId(cursor.getInt(0));
            obj.setTitle(cursor.getString(1));
            list.add(obj);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public long insertCategory(Category category){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,category.getTitle());

        try {
            if(database.insert(TB_NAME, null, contentValues)==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updateCategory(Category category){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,category.getTitle());

        try {
            if(database.update(TB_NAME,contentValues,ID+"=?", new String[]{category.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int delete(Category category){
        try {
            if(database.delete(TB_NAME,ID+"=?",new String[]{category.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
}
