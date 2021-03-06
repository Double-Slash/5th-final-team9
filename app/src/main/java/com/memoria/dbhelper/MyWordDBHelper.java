package com.memoria.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.memoria.modeldata.MyWord;

import java.security.acl.Group;
import java.util.ArrayList;

public class MyWordDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyWordDB.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "MyWordTable";
    public static final String COL_GROUP_NAME = "groupName";
    public static final String COL_ENGLISH_WORD = "englishWord";
    public static final String COL_KOREAN_WORD = "koreanWord";
    public static final String COL_DATE = "date";

    SQLiteDatabase db;

    public MyWordDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME + "("
                + "_id integer primary key autoincrement, "
                + COL_GROUP_NAME + " text not null,"
                + COL_ENGLISH_WORD + " text,"
                + COL_KOREAN_WORD + " text,"
                + COL_DATE + " date);"
        );
    }

    //삭제
    public void deleteGroub(String groupName){
//        String sql = "delete from " + TABLE_NAME + " where " + COL_GROUP_NAME + " = " + groupName + "';";
        db.delete(TABLE_NAME, COL_GROUP_NAME + " = ? ", new String[]{groupName});
    }

    //단어추가
    public boolean insertWord(MyWord myWord){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GROUP_NAME, myWord.getGroupName());
        contentValues.put(COL_ENGLISH_WORD, myWord.getEnglishWord());
        contentValues.put(COL_KOREAN_WORD, myWord.getKoreanWord());
        contentValues.put(COL_DATE, myWord.getDate());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if( result == -1) return false;
        else return true;
    }

    //그룹추가
    public boolean insertWordGroup(String groupName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GROUP_NAME, groupName);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if( result == -1) return false;
        else return true;
    }

    //날짜별 단어 등록 수 조회(일별 달성량 집계)
    public int selectWordCountByDate(String date){
        int result = 0;

        String sql = "select count(*) " + " from " + TABLE_NAME + " where " + COL_ENGLISH_WORD + " is not null and " + COL_DATE + " = '"+ date + "';";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                result = results.getInt(0);
            }while(results.moveToNext());
        }
        results.close();
        return result;
    }



    //그룹의 단어리스트 조회
    public ArrayList<MyWord> selectWordListByGroup(String groupName){
        ArrayList<MyWord> resultList = new ArrayList<>();

        String sql = "select " + COL_ENGLISH_WORD + " , " + COL_KOREAN_WORD + " from " + TABLE_NAME + " where "+ COL_ENGLISH_WORD + " is not null and " + COL_GROUP_NAME + " = '" + groupName +  "';";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                MyWord myWord = new MyWord();
                myWord.setEnglishWord(results.getString(0));
                myWord.setKoreanWord(results.getString(1));
//                myWord.setDate(results.getString(4));
                resultList.add(myWord);
            }while(results.moveToNext());
        }
        results.close();
        return resultList;
    }

    //그룹리스트 조회
    public ArrayList<MyWord> selectWordGroupList(){
        ArrayList<MyWord> resultList = new ArrayList<>();

        String sql = "select distinct " + COL_GROUP_NAME + " from " + TABLE_NAME + ";";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                MyWord myWord = new MyWord();
                myWord.setGroupName(results.getString(0));
                resultList.add(myWord);
            }while(results.moveToNext());
        }
        results.close();
        return resultList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyWordTable");
        onCreate(db);
    }

    public ArrayList<MyWord> selectAllDataList(){
        ArrayList<MyWord> resultList = new ArrayList<>();

        String sql = "select *  from " + TABLE_NAME + " where " + COL_DATE + " is not null;";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                MyWord myWord = new MyWord();
                myWord.setGroupName(results.getString(1));
                myWord.setEnglishWord(results.getString(2));
                myWord.setKoreanWord(results.getString(3));
                myWord.setDate(results.getString(4));
                resultList.add(myWord);
            }while(results.moveToNext());
        }
        results.close();
        return resultList;
    }

}
