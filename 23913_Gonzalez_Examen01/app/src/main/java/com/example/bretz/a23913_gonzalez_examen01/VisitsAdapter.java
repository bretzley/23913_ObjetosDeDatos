package com.example.bretz.a23913_gonzalez_examen01;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by bretz on 10/6/2017.
 */

public class VisitsAdapter extends ArrayAdapter<Customers>{
    public VisitsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, R.layout.content_customers, R.id.txtCustomer);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View oView = super.getView(position, convertView, parent);

        TextView txtID = (TextView) oView.findViewById(R.id.txtID);
        TextView txtCustomer = (TextView) oView.findViewById(R.id.txtCustomer);
        TextView txtOperations = (TextView) oView.findViewById(R.id.txtOperations);

        final Customers customerVisit = this.getItem(position);
        txtID.setText((position + 1) + " - ");
        txtCustomer.setText(customerVisit.getName());
        txtOperations.setText("Operations left: " + customerVisit.getOperations() + " ");

        return oView;
    }



}
