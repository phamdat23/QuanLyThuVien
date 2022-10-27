package vn.edu.poly.duanmau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.poly.duanmau.DAO.DAO_Admin;
import vn.edu.poly.duanmau.DAO.DAO_Book;
import vn.edu.poly.duanmau.DAO.DAO_BookCategory;
import vn.edu.poly.duanmau.DAO.DAO_PMCT;
import vn.edu.poly.duanmau.DAO.DAO_PhieuMuon;
import vn.edu.poly.duanmau.DAO.DAO_User;

public class MyHelper extends SQLiteOpenHelper {
    static final String db_NAME = "THUVIEN.db";
    static final int VERSION = 1;

    public MyHelper(Context context) {
        super(context, db_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DAO_Admin.CREATE_TB_ADMIN);
        db.execSQL(DAO_User.CREATE_TB_USER);
        db.execSQL(DAO_BookCategory.CREATE_TB_BOOKCATEGORY);
        db.execSQL(DAO_Book.CREATE_TB_BOOK);
        db.execSQL(DAO_PhieuMuon.CREATE_TB_PHIEUMUON);
        db.execSQL(DAO_PMCT.CREATE_TB_PMCT);
//        db.execSQL(DAO_SanPham.CREATE_TB_SANPHAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Admin.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_User.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_BookCategory.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_Book.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_PhieuMuon.TB_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+DAO_PMCT.TB_NAME);
//        db.execSQL("DROP TABLE IF EXISTS "+DAO_SanPham.TB_NAME);

    }
}
