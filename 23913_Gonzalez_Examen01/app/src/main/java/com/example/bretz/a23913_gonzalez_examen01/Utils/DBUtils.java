package com.example.bretz.a23913_gonzalez_examen01.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;

import com.example.bretz.a23913_gonzalez_examen01.CustomersAdapter;
import com.example.bretz.a23913_gonzalez_examen01.R;

/**
 * Created by bretz on 9/22/2017.
 */

public class DBUtils extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Banpatito.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CUSTOMER_TABLE_NAME = "CUSTOMERS";
    public static final String CUSTOMER_ID = "_id";
    public static final String CUSTOMER_NAME = "_name";
    public static final String CUSTOMER_POSITION = "_position";
    public static final String CUSTOMER_OPERATIONS = "_operations";



    /*public static String _cusID = "";
    public static String _cusName = "";
    public static String _cusOper = "";
    public static String _cusPos = "";*/

    public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+
            CUSTOMER_TABLE_NAME+
            " ("+
            CUSTOMER_ID+
            " integer primary key autoincrement, "+
            CUSTOMER_NAME+
            " text not null, "+
            CUSTOMER_OPERATIONS+
            " integer not null, "+
            CUSTOMER_POSITION+
            " integer not null"+
            ")";

   /* public static final String DATABASE_INSERT = "INSERT INTO "+
            CUSTOMER_TABLE_NAME+
            " (CUSTOMER_ID, CUSTOMER_NAME,CUSTOMER_OPERATIONS, CUSTOMER_POSITION) "+
            "VALUES ("+
            _cusID + ", " +
            _cusName + ", " +
            _cusOper + ", " +
            _cusPos +")";*/

    public DBUtils(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(DATABASE_CREATE);
        //sqLiteDatabase.execSQL(DATABASE_INSERT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CUSTOMERS");
        onCreate(sqLiteDatabase);

    }

}
