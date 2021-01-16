package com.memoria.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.memoria.modeldata.Goal;
import com.memoria.modeldata.MyMemory;
import com.memoria.modeldata.MyWord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GoalDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GoalDB.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "GoalDBTable";
    public static final String COL_GOAL_WORD = "goalWord";
    public static final String COL_GOAL_MEMORY = "goalMemory";
    public static final String COL_GOAL_TEST_PERCENT = "goalTest";
    public static final String COL_GOAL_QUIZ_PERCENT = "goalQuiz";
    public static final String COL_ACHIEVE_WORD = "achieveWord";
    public static final String COL_ACHIEVE_MEMORY = "achieveMemory";
    public static final String COL_ACHIEVE_TEST_PERCENT = "achieveTest";
    public static final String COL_ACHIEVE_QUIZ_PERCENT = "achieveQuiz";
    public static final String COL_DATE = "date";

    SQLiteDatabase db;

    public GoalDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME + "("
                + "_id integer, "
                + COL_GOAL_WORD + " integer,"
                + COL_GOAL_MEMORY + " integer,"
                + COL_GOAL_TEST_PERCENT + " integer,"
                + COL_GOAL_QUIZ_PERCENT + " integer,"
                + COL_ACHIEVE_WORD + " integer,"
                + COL_ACHIEVE_MEMORY + " integer,"
                + COL_ACHIEVE_TEST_PERCENT + " integer,"
                + COL_ACHIEVE_QUIZ_PERCENT + " integer,"
                + COL_DATE + " date PRIMARY KEY);"
        );
    }

    //하루 목표량 0으로 설정
    public boolean insertDefault (){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GOAL_WORD, 0);
        contentValues.put(COL_GOAL_MEMORY, 0);
        contentValues.put(COL_GOAL_TEST_PERCENT, 0);
        contentValues.put(COL_GOAL_QUIZ_PERCENT, 0);
        contentValues.put(COL_DATE, getNowDate());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if( result == -1) return false;
        else return true;
    }

    //어제 목표량 그대로
    public boolean insertFollowYesterday (){

        String sql = "select " + COL_GOAL_WORD + ", " + COL_GOAL_MEMORY + ", " + COL_GOAL_TEST_PERCENT + ", " + COL_GOAL_QUIZ_PERCENT
                + " from " + TABLE_NAME + " where " + COL_DATE + " = date('" + getNowDate() + "', '-1 days')" + ";";
        Cursor results = db.rawQuery(sql, null);

        ContentValues contentValues = new ContentValues();
        if(results.moveToFirst()){
            do{
                contentValues.put(COL_GOAL_WORD, results.getInt(0));
                contentValues.put(COL_GOAL_MEMORY, results.getInt(1));
                contentValues.put(COL_GOAL_TEST_PERCENT, results.getInt(2));
                contentValues.put(COL_GOAL_QUIZ_PERCENT, results.getInt(3));
                contentValues.put(COL_DATE, getNowDate());
            }while(results.moveToNext());
        }

        results.close();

        long result = db.insert(TABLE_NAME, null, contentValues);

        if( result == -1) return false;
        else return true;
    }

    public boolean selectExistTodayData(){
        String sql = "select " + COL_DATE
                + " from " + TABLE_NAME + " where " + COL_DATE + " = date('" + getNowDate() + "')" + ";";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            return !TextUtils.isEmpty(results.getString(0));
        }
        return false;
    }

    //날짜로 목표량 달성량 조회하기
    public Goal selectGoalAchieveByDate(String date){
        String sql = "select * from " + TABLE_NAME + " where " + COL_DATE + " = '" + date + "'" + ";";
        Cursor result = db.rawQuery(sql, null);

        Goal goal = new Goal();
        if(result.moveToFirst()) goal = new Goal(
                result.getInt(1),
                result.getInt(2),
                result.getInt(3),
                result.getInt(4),
                result.getInt(5),
                result.getInt(6),
                result.getInt(7),
                result.getInt(8),
                result.getString(9));

        return goal;
    }

    //목표설정 수정(업데이트)
    public boolean updateGoal(Goal goal) {
        ContentValues values = new ContentValues();
        values.put(COL_GOAL_WORD, goal.getGoalWord());
        values.put(COL_GOAL_MEMORY, goal.getGoalMemory());
        values.put(COL_GOAL_TEST_PERCENT, goal.getGoalTest());
        values.put(COL_GOAL_QUIZ_PERCENT, goal.getGoalQuiz());

        long result = db.update(TABLE_NAME, values, COL_DATE + " = " + getNowDate(), null);

        if( result == -1) return false;
        else return true;
    }

    //목표설정 수정(업데이트) - Word
    public boolean updateGoalWord(int goalWord) {
        ContentValues values = new ContentValues();
        values.put(COL_GOAL_WORD, String.valueOf(goalWord));

        long result = db.update(TABLE_NAME, values, COL_DATE + " = '" + getNowDate() + "'", null);

        System.out.println(result);
        if( result == -1) return false;
        else return true;
    }

    //목표설정 수정(업데이트) - Memory
    public boolean updateGoalMemory(int goalMemory) {
        ContentValues values = new ContentValues();
        values.put(COL_GOAL_MEMORY, String.valueOf(goalMemory));

        long result = db.update(TABLE_NAME, values, COL_DATE + " = '" + getNowDate() + "'", null);

        if( result == -1) return false;
        else return true;
    }

    //목표설정 수정(업데이트) - Memory
    public boolean updateGoalTest(int goalTest) {
        ContentValues values = new ContentValues();
        values.put(COL_GOAL_TEST_PERCENT, String.valueOf(goalTest));

        long result = db.update(TABLE_NAME, values, COL_DATE + " = '" + getNowDate() + "'", null);

        if( result == -1) return false;
        else return true;
    }

    //목표설정 수정(업데이트) - Memory
    public boolean updateGoalQuiz(int goalQuiz) {
        ContentValues values = new ContentValues();
        values.put(COL_GOAL_QUIZ_PERCENT, String.valueOf(goalQuiz));

        long result = db.update(TABLE_NAME, values, COL_DATE + " = '" + getNowDate() + "'", null);

        System.out.println(result);
        return result != -1;
    }

    //날짜별로 목표 조회
    public Goal selectGoalByDate(String date){
        String sql = "select * from " + TABLE_NAME + " where " + COL_DATE + " = date('" + getNowDate() + "')" + ";";
        Cursor results = db.rawQuery(sql, null);

        Goal goal = new Goal();
        if(results.moveToFirst()){
            goal.setGoalWord(results.getInt(1));
            goal.setGoalMemory(results.getInt(2));
            goal.setGoalTest(results.getInt(3));
            goal.setGoalQuiz(results.getInt(4));
        }
        return goal;
    }

    //달성률 수정(업데이트) - 오늘날짜만 가능
    public boolean updateAchieve(Goal goal) {
        ContentValues values = new ContentValues();
        values.put(COL_ACHIEVE_WORD, goal.getAchieveWord());
        values.put(COL_ACHIEVE_MEMORY, goal.getAchieveMemory());
        values.put(COL_ACHIEVE_TEST_PERCENT, goal.getAchieveTest());
        values.put(COL_ACHIEVE_QUIZ_PERCENT, goal.getAchieveQuiz());

        long result = db.update(TABLE_NAME, values, COL_DATE + " = '" + getNowDate() + "'", null);

        if( result == -1) return false;
        else return true;
    }

    public ArrayList<Goal> selectAllData(){
        ArrayList<Goal> resultList = new ArrayList<>();

        String sql = "select * from " + TABLE_NAME + ";";
        Cursor results = db.rawQuery(sql, null);

        if(results.moveToFirst()){
            do{
                Goal goal = new Goal(
                        results.getInt(1),
                        results.getInt(2),
                        results.getInt(3),
                        results.getInt(4),
                        results.getInt(5),
                        results.getInt(6),
                        results.getInt(7),
                        results.getInt(8),
                        results.getString(9));
                resultList.add(goal);
            }while(results.moveToNext());
        }
        results.close();
        return resultList;
    }

    public String getNowDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
        return sdfNow.format(date);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS GoalDBTable");
        onCreate(db);
    }

}
