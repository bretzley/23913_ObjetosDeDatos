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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = (Button) findViewById(R.id.btnAdd);
        Button btn_queue = (Button) findViewById(R.id.btnQueue);
        final EditText txt_id = (EditText) findViewById(R.id.txtID);
        final EditText txt_name = (EditText) findViewById(R.id.txtCustomer);
        final EditText txt_operations  = (EditText) findViewById(R.id.txtOperations);
        final ArrayList<Customers> array_customers = new ArrayList<>();
        final ArrayAdapter<Customers> custAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,array_customers);
        final CustomersAdapter customerAdap = new CustomersAdapter(this);
        final ListView lst_customers = (ListView)findViewById(R.id.lstCustomers);
        lst_customers.setAdapter(custAdapter);
        //lst_customers.setAdapter(customerAdap);


        txt_operations.addTextChangedListener(this);

        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String customerName = txt_name.getText().toString();
                String operationsNum = txt_operations.getText().toString();
                int operationsInt = Integer.parseInt(operationsNum);
                int ID = array_customers.size() +1;



                Customers customer = new Customers(ID,customerName,operationsInt);
                //array_customers.add(customer);

                //customerAdap.add(customer);
                custAdapter.add(customer);


                Toast.makeText(getApplicationContext(),"Customer Added Successfully!",Toast.LENGTH_LONG).show();

            }
        });


        btn_queue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);

                String customerName = txt_name.getText().toString();
                String operationsNum = txt_operations.getText().toString();
                int operationsInt = Integer.parseInt(operationsNum);
                int ID = array_customers.size() +1;

                Customers customer = new Customers();
                customer.setName(customerName);
                customer.setName(operationsNum);


                intent.putExtra("Success",customer);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
