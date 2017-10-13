package com.example.bretz.a23913_gonzalez_examen01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.bretz.a23913_gonzalez_examen01.Utils.CustomerHelper;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    CustomersAdapter customerAdap;
    public ArrayList<Customers> array_customers = new ArrayList<Customers>();
    CustomerHelper helperDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = (Button) findViewById(R.id.btnAdd);
        Button btn_queue = (Button) findViewById(R.id.btnQueue);
        Button btn_del = (Button) findViewById(R.id.btnDel);
        final EditText txt_id = (EditText) findViewById(R.id.txtID);
        final EditText txt_name = (EditText) findViewById(R.id.txtCustomer);
        final EditText txt_operations  = (EditText) findViewById(R.id.txtOperations);
        ListView lst_customers = (ListView)findViewById(R.id.lstCustomers);
        customerAdap = new CustomersAdapter(this);

        lst_customers.setAdapter(customerAdap);
        helperDB = customerAdap.returnDB();

        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String customerName = txt_name.getText().toString();
                String operationsNum = txt_operations.getText().toString();
                int operationsInt = Integer.parseInt(operationsNum);


                if(operationsInt > 0) {
                    helperDB.open();
                    Customers newCustomer = helperDB.addCustomers(customerName, operationsInt, 0);
                    helperDB.close();
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
                helperDB.open();
                intent.putExtra("Success", helperDB.getAllCustomers());
                helperDB.close();
                startActivity(intent);
            }
        });

        /*btn_del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                helperDB.open();
                helperDB.deleteCustomers();
                helperDB.close();
                customerAdap.clear();
                customerAdap.notifyDataSetChanged();
            }
        });
*/

    }
}
