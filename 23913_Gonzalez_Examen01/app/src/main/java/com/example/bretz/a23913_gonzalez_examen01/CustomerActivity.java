package com.example.bretz.a23913_gonzalez_examen01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {

    CustomersAdapter CustomerAdapter;
    ListView CustListView;
    ArrayList<Customers> customerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        CustListView = (ListView) findViewById(R.id.lstQueue);
        CustomerAdapter = new CustomersAdapter(this);
        CustListView.setAdapter(CustomerAdapter);
        customerArray = this.getIntent().getParcelableArrayListExtra("Success");


        //Customers customers = this.getIntent().getParcelableExtra("Success");

        //customerArray.add(customers);
        fillCustomers(customerArray);
    }


    private void fillCustomers(ArrayList<Customers> CustomersList)
    {
        CustomerAdapter.clear();
        int current = CustomersList.size();

        while(current > 0){
            for (Customers customers : CustomersList){
                int operation = customers.getOperations();
                if(operation > 0){
                    CustomerAdapter.add(new Customers(customers.getName(), customers.getOperations()));
                    customers.setOperations(operation-1);
                }
                else {
                    current--;
                }
        }

        }
        CustomerAdapter.notifyDataSetChanged();
    }

}
