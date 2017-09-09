package com.example.bretz.a23913_gonzalez_examen01;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bretz on 9/8/2017.
 */

public class CustomersAdapter extends ArrayAdapter<Customers>{
    public CustomersAdapter(Context context) {super(context, R.layout.row_content, R.id.txtCustomer);}

    @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){


        View custView = super.getView(position, convertView, parent);
        TextView txtID = (TextView) custView.findViewById(R.id.txtID);
        TextView txtCustomerAdap = (TextView) custView.findViewById(R.id.txtCustomer);
        TextView txtOperationsAdap = (TextView) custView.findViewById(R.id.txtOperations);

        final Customers customer = this.getItem(position);
        txtID.setText(position + 1);
        txtCustomerAdap.setText(customer.getName());
        txtOperationsAdap.setText(customer.getOperations());

        return custView;
    }
}
