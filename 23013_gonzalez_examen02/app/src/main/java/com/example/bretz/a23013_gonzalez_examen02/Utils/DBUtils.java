package com.example.bretz.a23013_gonzalez_examen02.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bretz on 10/27/2017.
 */

public class DBUtils extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="McPatitoSnakeLadder";
    public static final int DATABASE_VERSION = 1;

    public static final String SNLD_TABLE="SnakeLadder";
    public static final String SNLD_BASEID = "baseId";
    public static final String SNLD_ID = "id";
    public static final String SNLD_BOARD = "boardName";
    public static final String SNLD_AUTHOR = "authorName";
    public static final String SNLD_SNAKE ="snakes";
    public static final String SNLD_LADDER ="ladders";


    public static final String CREATE_SNLD =
            "CREATE TABLE "+ SNLD_TABLE + "(" +
                    SNLD_BASEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SNLD_ID + " TEXT, " +
                    SNLD_BOARD + " TEXT, " +
                    SNLD_AUTHOR + " TEXT, " +
                    SNLD_SNAKE + " TEXT," +
                    SNLD_LADDER + " TEXT)";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SNLD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SNLD_TABLE + "");
        onCreate(db);
    }
}
