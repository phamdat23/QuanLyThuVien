package vn.edu.poly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.duanmau.Database.MyHelper;
import vn.edu.poly.duanmau.Model.Book;
import vn.edu.poly.duanmau.Model.Category;
import vn.edu.poly.duanmau.Model.TopSach;

public class DAO_Book {
    public static final String CREATE_TB_BOOK = "CREATE TABLE BOOK(IDBOOK INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,IDCATEGORY INTEGER NOT NULL, TITLE TEXT NOT NULL, SOLUONG INTEGER NOT NULL,TACGIA TEXT,GIA INTEGER NOT NULL, FOREIGN KEY(IDCATEGORY) REFERENCES BOOKCATEGORY(IDCATEGORY));";
    public static final String TB_NAME = "BOOK";
    public static final String IDBOOK = "IDBOOK";
    public static final String IDCATEGORY = "IDCATEGORY";
    public static final String TITLE = "TITLE";
    public static final String SOLUONG = "SOLUONG";
    public static final String TACGIA = "TACGIA";
    public static final String GIA = "GIA";
    SQLiteDatabase database;
    MyHelper myHelper;

    public DAO_Book(Context context) {
        myHelper = new MyHelper(context);
    }

    public void opend() {
        database = myHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }
    public ArrayList<Book> selectAll(){
        ArrayList<Book> list = new ArrayList<>();
       String select ="SELECT * FROM BOOK JOIN BOOKCATEGORY ON BOOK.IDCATEGORY = BOOKCATEGORY. IDCATEGORY";
       Cursor cursor = database.rawQuery(select, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Book book = new Book();
            book.setIdBook(cursor.getInt(0));
            book.setIdCategory(cursor.getInt(1));
            book.setTitleBook(cursor.getString(2));
            book.setSoLuong(cursor.getInt(3));
            book.setTacGia(cursor.getString(4));
            book.setGia(cursor.getInt(5));
            book.setTitleCategory(cursor.getString(7));
            list.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<Book> selectCategory1(ArrayList<Category> listC){
        ArrayList<Book> listB = new ArrayList<>();
        String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_BookCategory.TB_NAME+" ON "+TB_NAME+"."+IDCATEGORY+"="+DAO_BookCategory.TB_NAME+"."+DAO_BookCategory.ID+" WHERE "+TB_NAME+"."+IDCATEGORY+"="+listC.get(0).getId();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Book book = new Book();
            book.setIdBook(cursor.getInt(0));
            book.setIdCategory(cursor.getInt(1));
            book.setTitleBook(cursor.getString(2));
            book.setSoLuong(cursor.getInt(3));
            book.setTacGia(cursor.getString(4));
            book.setGia(cursor.getInt(5));
            book.setTitleCategory(cursor.getString(7));
            listB.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Book> selectCategory2(ArrayList<Category> listC){
        ArrayList<Book> listB = new ArrayList<>();
        String select ="SELECT * FROM BOOK JOIN BOOKCATEGORY ON BOOK.IDCATEGORY = BOOKCATEGORY. IDCATEGORY WHERE BOOK.IDCATEGORY = "+listC.get(1).getId();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Book book = new Book();
            book.setIdBook(cursor.getInt(0));
            book.setIdCategory(cursor.getInt(1));
            book.setTitleBook(cursor.getString(2));
            book.setSoLuong(cursor.getInt(3));
            book.setTacGia(cursor.getString(4));
            book.setGia(cursor.getInt(5));
            book.setTitleCategory(cursor.getString(7));
            listB.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Book> selectCategory3(ArrayList<Category> listC){
        ArrayList<Book> listB = new ArrayList<>();
        String select ="SELECT * FROM BOOK JOIN BOOKCATEGORY ON BOOK.IDCATEGORY = BOOKCATEGORY. IDCATEGORY WHERE BOOK.IDCATEGORY = "+listC.get(2).getId();
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Book book = new Book();
            book.setIdBook(cursor.getInt(0));
            book.setIdCategory(cursor.getInt(1));
            book.setTitleBook(cursor.getString(2));
            book.setSoLuong(cursor.getInt(3));
            book.setTacGia(cursor.getString(4));
            book.setGia(cursor.getInt(5));
            book.setTitleCategory(cursor.getString(7));
            listB.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<Book> selectCategory4(ArrayList<Category> listC){
        ArrayList<Book> listB = new ArrayList<>();
        String select ="SELECT * FROM "+TB_NAME+" JOIN "+DAO_BookCategory.TB_NAME+" ON "+TB_NAME+"."+IDCATEGORY+"="+DAO_BookCategory.TB_NAME+"."+DAO_BookCategory.ID+" WHERE BOOK."+IDCATEGORY+" NOT IN ( SELECT IDCATEGORY FROM BOOK WHERE IDCATEGORY = "+listC.get(0).getId()+" OR "+" IDCATEGORY = "+listC.get(1).getId()+" OR IDCATEGORY = "+listC.get(2).getId()+" )";
        Cursor cursor =database.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Book book = new Book();
            book.setIdBook(cursor.getInt(0));
            book.setIdCategory(cursor.getInt(1));
            book.setTitleBook(cursor.getString(2));
            book.setSoLuong(cursor.getInt(3));
            book.setTacGia(cursor.getString(4));
            book.setGia(cursor.getInt(5));
            book.setTitleCategory(cursor.getString(7));
            listB.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return listB;
    }
    public ArrayList<TopSach> topBook(){
        ArrayList<TopSach> list = new ArrayList<>();
        String select="SELECT  BOOK.* , COUNT(PMCT.IDBOOK) AS 'LƯỢT MƯỢN' FROM BOOK JOIN PMCT ON BOOK.IDBOOK = PMCT.IDBOOK \n" +
                "GROUP BY PMCT.IDBOOK, BOOK.IDBOOK, BOOK.TITLE, BOOK.SOLUONG, BOOK.IDCATEGORY, BOOK.GIA, BOOK.TACGIA\n" +
                "ORDER BY COUNT(PMCT.IDBOOK) DESC";
        Cursor cursor = database.rawQuery(select, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            TopSach obj = new TopSach();
            obj.setIdBook(cursor.getInt(0));
            obj.setIdCategory(cursor.getInt(1));
            obj.setTitleBook(cursor.getString(2));
            obj.setSoLuong(cursor.getInt(3));
            obj.setTacGia(cursor.getString(4));
            obj.setGia(cursor.getInt(5));
            obj.setLuotMuon(cursor.getInt(6));
            list.add(obj);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public long insertBook(Book book){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDCATEGORY, book.getIdCategory());
        contentValues.put(TITLE, book.getTitleBook());
        contentValues.put(SOLUONG, book.getSoLuong());
        contentValues.put(GIA, book.getGia());
        contentValues.put(TACGIA, book.getTacGia());
        try {
            if(database.insert(TB_NAME, null, contentValues)==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updateBook(Book book){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDCATEGORY, book.getIdCategory());
        contentValues.put(TITLE, book.getTitleBook());
        contentValues.put(SOLUONG, book.getSoLuong());
        contentValues.put(GIA, book.getGia());
        contentValues.put(TACGIA, book.getTacGia());
        try {
            if(database.update(TB_NAME,contentValues,IDBOOK+"=?", new String[]{book.getIdBook()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int deleteBook(Book book){
        try {
            if(database.delete(TB_NAME,IDBOOK+"=?",  new String[]{book.getIdBook()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

}
