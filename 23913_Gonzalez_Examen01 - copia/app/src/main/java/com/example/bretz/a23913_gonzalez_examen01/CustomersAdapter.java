package com.example.bretz.a23913_gonzalez_examen01;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bretz.a23913_gonzalez_examen01.Utils.CustomerHelper;
import com.example.bretz.a23913_gonzalez_examen01.Utils.DBUtils;

import java.util.List;

/**
 * Created by bretz on 9/8/2017.
 */

public class CustomersAdapter extends ArrayAdapter<Customers>{
    CustomerHelper DB;
    public CustomersAdapter(Context context) {
        super(context, R.layout.row_content, R.id.txtCustomer);
        DB = new CustomerHelper(context);
        DB.open();
        addAll(DB.getAllCustomers());
        DB.close();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View custView = super.getView(position, convertView, parent);
        TextView txtID = (TextView) custView.findViewById(R.id.txtID);
        TextView txtCustomerAdap = (TextView) custView.findViewById(R.id.txtCustomer);
        TextView txtOperationsAdap = (TextView) custView.findViewById(R.id.txtOperations);

        Button btnDel = (Button) custView.findViewById(R.id.btnDel);
        final Customers customer = this.getItem(position);
        txtID.setText((position + 1) + ".\t\t");
        txtCustomerAdap.setText(customer.getName());
        txtOperationsAdap.setText("\t\t-\t\t" + customer.getOperations());



        /*DBUtils._cusID = txtID.getText().toString();
        DBUtils._cusName = txtCustomerAdap.getText().toString();
        DBUtils._cusOper = txtOperationsAdap.getText().toString();*/

        /*btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.open();
                DB.deleteCustomer(customer.getPosition());
                DB.close();
                remove(customer);
                notifyDataSetChanged();
            } });*/

        /*String helperID =  txtID.getText().toString();
        int helperName = Integer.parseInt(txtOperationsAdap.toString());
        int helperPos = Integer.parseInt(txtOperationsAdap.toString());
        CustomerHelper helperCus = new CustomerHelper();
        helperCus.addCustomers(helperID , helperName, helperPos);*/


        return custView;
    }

    public CustomerHelper returnDB(){return DB;}
}