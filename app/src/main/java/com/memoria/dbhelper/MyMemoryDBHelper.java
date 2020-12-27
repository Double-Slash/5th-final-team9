package com.memoria.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.memoria.modeldata.MyMemory;

import java.util.ArrayList;

public class MyMemoryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyMemoryDB.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "MyMemoryTable";
    public static final String COL_ENGLISH_MEMORY = "englishMemory";
//    public static final String COL_KOREAN_MEMORY = "koreanMemory";
    public static final String COL_DATE = "date";

    SQLiteDatabase db;

    public MyMemoryDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME + "("
                + "_id integer primary key autoincrement, "
                + COL_ENGLISH_MEMORY + " text,"
                + COL_DATE + " date);"
        );
    }

    //문장(Memory)추가
    public boolean insertMemory(MyMemory myMemory){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ENGLISH_MEMORY, myMemory.getEnglishMemory());
//        contentValues.put(COL_KOREAN_MEMORY, myWord.getKoreanWord());
        contentValues.put(COL_DATE, myMemory.getDate());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if( result == -1) return false;
        else return true;
    }

    //모든 문장 조회
    public ArrayList<MyMemory> selectAllMemoryList(){
        ArrayList<MyMemory> resultList = new ArrayList<>();

        String sql = "select " + COL_ENGLISH_MEMORY + " from " + TABLE_NAME + ";";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                MyMemory myMemory = new MyMemory();
                myMemory.setEnglishMemory(results.getString(0));
                resultList.add(myMemory);
            }while(results.moveToNext());
        }
        results.close();
        return resultList;
    }

    //날짜별 문장 등록 수 조회(일별 달성량 집계)
    public int selectMemoryCountByDate(String date){
        int result = 0;

        String sql = "select count(*) " + " from " + TABLE_NAME + " where " + COL_DATE + " = '"+ date + "';";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                result = results.getInt(0);
            }while(results.moveToNext());
        }
        results.close();
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyMemoryTable");
        onCreate(db);
    }

}
