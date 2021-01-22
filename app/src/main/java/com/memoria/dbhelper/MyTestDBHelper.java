package com.memoria.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.memoria.modeldata.MyTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MyTestDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyTestDB.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "MyTestTable";
    public static final String COL_DATE = "DATE";
    public static final String COL_TOTAL = "TOTAL";
    public static final String COL_STATUS = "STATUS";
    public static final String COL_CORRECT = "CORRECT";
    public static final String COL_PERCENT = "PERCENT";
    public static final String COL_GROUP = "GROUPNAME";

    SQLiteDatabase db;

    public MyTestDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME + "("
                + "_id integer primary key autoincrement, "
                + COL_TOTAL + " integer,"
                + COL_STATUS + " text,"
                + COL_CORRECT + " integer,"
                + COL_PERCENT + " float,"
                + COL_GROUP + " text,"
                + COL_DATE + " date);"
        );
    }

    //맞은거와 단어 총 개수 추가
    public boolean insertScore(MyTest myTest){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TOTAL, myTest.getTotal());
        contentValues.put(COL_CORRECT, myTest.getCorrect());
        contentValues.put(COL_PERCENT, myTest.getPercent());
        contentValues.put(COL_GROUP, myTest.getGroup());
        contentValues.put(COL_STATUS, myTest.getStatus());
        contentValues.put(COL_DATE, myTest.getDate());

        long result = db.insert(TABLE_NAME, null, contentValues);
        if( result == -1) return false;
        else return true;
    }

    public int selectUnLockMaxPercent() {
        String sql = " SELECT " + COL_PERCENT + " FROM " + TABLE_NAME + " where " + COL_DATE + " = '"+ getNowDate()  +"' and " + COL_STATUS + " = 'unlock'" + " ORDER BY " + COL_PERCENT + " DESC LIMIT 1;";
        Cursor result = db.rawQuery(sql, null);

        if (result.moveToFirst()) return result.getInt(0);

        result.close();
        return 0;
    }

    public int selectLockMaxPercent() {
        String sql = " SELECT " + COL_PERCENT + " FROM " + TABLE_NAME + " where " + COL_DATE + " = '"+ getNowDate()  +"' and " + COL_STATUS + " = 'lock'" + " ORDER BY " + COL_PERCENT + " DESC LIMIT 1;";
        Cursor result = db.rawQuery(sql, null);

        if (result.moveToFirst()) return result.getInt(0);

        result.close();
        return 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyWordTable");
        onCreate(db);
    }

    // 가장 최근에 LOCK 테스트 그룹 등록한 걸 뽑는다.
    public String selectRecentGroup() {
        String sql = " SELECT " + "GROUPNAME" + " FROM " + TABLE_NAME + " where " + COL_DATE + " = '"+ getNowDate()  +"' ORDER BY " + "_id" + " DESC LIMIT 1;";
        Cursor result = db.rawQuery(sql, null);

        if (result.moveToFirst()) return result.getString(0);

        result.close();
        return null;
    }

    public String getNowDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
        return sdfNow.format(date);
    }

    public void DeleteData() {
        db.delete(TABLE_NAME,"TOTAL"+"="+0,null);
    }

    public ArrayList<MyTest> selectAllData(){
        ArrayList<MyTest> resultList = new ArrayList<>();

        String sql = "select *  from " + TABLE_NAME + " where " + COL_DATE + " is not null;";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                MyTest myTest = new MyTest();
                myTest.setStatus(results.getString(1));
                myTest.setTotal(results.getInt(2));
                myTest.setCorrect(results.getInt(3));
                myTest.setPercent(results.getInt(4));
                myTest.setGroup(results.getString(5));
                myTest.setDate(results.getString(6));
                resultList.add(myTest);
            }while(results.moveToNext());
        }
        results.close();
        return resultList;
    }


}
