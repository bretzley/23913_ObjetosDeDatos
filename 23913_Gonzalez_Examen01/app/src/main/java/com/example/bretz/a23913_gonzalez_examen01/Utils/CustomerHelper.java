package com.example.bretz.a23913_gonzalez_examen01.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.bretz.a23913_gonzalez_examen01.Customers;
import java.util.ArrayList;


/**
 * Created by bretz on 9/22/2017.
 */

public class CustomerHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] CUSTOMERS_TABLE_COLUMNS = {
            DBUtils.CUSTOMER_ID,
            DBUtils.CUSTOMER_NAME,
            DBUtils.CUSTOMER_OPERATIONS,
            DBUtils.CUSTOMER_POSITION
    };

    public CustomerHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public CustomerHelper() {

    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Customers addCustomers(String name, int operations, int position){
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_NAME, name);
        values.put(DBUtils.CUSTOMER_OPERATIONS, operations);
        values.put(DBUtils.CUSTOMER_POSITION, position);

        long lCustomerID = database.insert(DBUtils.CUSTOMER_TABLE_NAME, null, values);

        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE_NAME, CUSTOMERS_TABLE_COLUMNS,
                DBUtils.CUSTOMER_ID+" = "+lCustomerID, null, null, null, null);
        cursor.moveToFirst();
        Customers oCustomer = parseCustomer(cursor);
        cursor.close();
        return null;
    }

    public int deleteCustomer(int nCustomerID){
        return database.delete(DBUtils.CUSTOMER_TABLE_NAME,DBUtils.CUSTOMER_ID+" = "+nCustomerID,null);
    }


    public ArrayList<Customers> getAllCustomers(){
        ArrayList<Customers> oLCustomers = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE_NAME, CUSTOMERS_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Customers oCustomer  = new Customers();
            oCustomer.setName(cursor.getString(1));
            oLCustomers.add(oCustomer);
            cursor.moveToNext();
        }
        cursor.close();
        return oLCustomers;
    }

    private Customers parseCustomer(Cursor cursor){
        Customers oCustomer = new Customers();
        oCustomer.setName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_NAME)));
        oCustomer.setOperations(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_OPERATIONS)));
        return oCustomer;
    }

}
