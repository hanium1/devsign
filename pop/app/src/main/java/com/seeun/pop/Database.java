package com.seeun.pop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.DriverManager.println;

/**
 * Created by se_02 on 2017-08-06.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "devsign.db";
    private static final int DATABASE_VERSION = 2;
   /* private static final String MOTION_METHOD = "측정 방법";
    private static final String MOTION_NAME = "운동 이름";
    private static final String MOTION_READING = "측정값";
    private static final String DATE = "날짜";*/

  /*  public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("DB 연결 완료");
    }*/

  public Database(Context context){
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
      System.out.println("DB 연결 완료");
  }
    @Override   //테이블 생성
    public void onCreate(SQLiteDatabase db) {
        println("테이블 생성 완료");
        String CREATE_SQL = "CREATE TABLE IF NOT EXISTS info (Date char(10), "
                + "Motion_name varchar(20), "
                //+ "Motion_method integer, "
                + "Motion_reading integer, "
                + "PRIMARY KEY(Date))";
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        println("opend database [" + DATABASE_NAME + "].");
    }

    @Override   //어플 다시 설치시
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
