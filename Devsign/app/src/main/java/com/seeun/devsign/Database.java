package com.seeun.devsign;

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
    private static final String SENSOR_NUMBER = "센서 번호";
    private static final String SENSOR_NAME = "센서 이름";
    private static final String COUNT = "측정값";
    private static final String DATE = "날짜";

  /*  public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("DB 연결 완료");
    }*/

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("DB 연결 완료");
    }

    @Override   //테이블 생성
    public void onCreate(SQLiteDatabase db) {
        println("테이블 생성 완료");
        String CREATE_SQL = "CREATE TABLE IF NOT EXISTS info " + SENSOR_NUMBER + "integer, "
                + SENSOR_NAME + "varchar(30), "
                + COUNT + "integer, "
                + DATE + "varchar(10)"
                + "PRIMARY KEY(" + SENSOR_NUMBER + "))";
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
