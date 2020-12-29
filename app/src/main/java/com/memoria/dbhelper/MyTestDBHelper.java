package com.memoria.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.memoria.modeldata.MyTest;


public class MyTestDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyTestDB.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "MyTestTable";
    public static final String COL_DATE = "DATE";
    public static final String COL_GROUP_NAME = "GROUPNAME";
    public static final String COL_CORRECT = "CORRECT";
    public static final String COL_PERCENT = "PERCENT";

    SQLiteDatabase db;

    public MyTestDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME + "("
                + "_id integer primary key autoincrement, "
                + COL_GROUP_NAME + " text not null,"
                + COL_CORRECT + " integer,"
                + COL_PERCENT + " float,"
                + COL_DATE + " date);"
        );
    }

    //맞은거와 단어 총 개수 추가
    public boolean insertScore(MyTest myTest){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GROUP_NAME, myTest.getGroupName());
        contentValues.put(COL_CORRECT, myTest.getCorrect());
        contentValues.put(COL_PERCENT, myTest.getPercent());
        contentValues.put(COL_DATE, myTest.getDate());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if( result == -1) return false;
        else return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyWordTable");
        onCreate(db);
    }

}
