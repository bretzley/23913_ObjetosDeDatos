package com.example.bretz.a23913_gonzalez_examen01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bretz.a23913_gonzalez_examen01.Utils.CustomerHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    CustomersAdapter customerAdap;
    public ArrayList<Customers> array_customers = new ArrayList<Customers>();
    SQLiteDatabase db;
    CustomerHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = (Button) findViewById(R.id.btnAdd);
        Button btn_queue = (Button) findViewById(R.id.btnQueue);
        final EditText txt_id = (EditText) findViewById(R.id.txtID);
        final EditText txt_name = (EditText) findViewById(R.id.txtCustomer);
        final EditText txt_operations  = (EditText) findViewById(R.id.txtOperations);
        ListView lst_customers = (ListView)findViewById(R.id.lstCustomers);
        customerAdap = new CustomersAdapter(this);

        lst_customers.setAdapter(customerAdap);
        DB = customerAdap.returnDB();

        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String customerName = txt_name.getText().toString();
                String operationsNum = txt_operations.getText().toString();
                int operationsInt = Integer.parseInt(operationsNum);


                if(operationsInt > 0) {
                    DB.open();
                    Customers newCustomer = DB.addCustomers(customerName, operationsInt, 0);
                    DB.close();
                    customerAdap.add(newCustomer);
                    customerAdap.notifyDataSetChanged();
                }
                //int ID = array_customers.size() +1;

                //array_customers.add(newCustomer);
            }
        });

        btn_queue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                //intent.putExtra("Success",array_customers);
                DB.open();
                intent.putExtra("Success", DB.getAllCustomers());
                DB.close();
                startActivity(intent);
            }
        });


    }
}
