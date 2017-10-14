package com.example.bretz.oct6android.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bretz.oct6android.Objects.Comments;
import com.example.bretz.oct6android.R;

/**
 * Created by bretz on 10/13/2017.
 */

public class CommentsAdapter extends ArrayAdapter<Comments> {

    public CommentsAdapter(Context context) {
        super(context, R.layout.comment_rows, R.id.txtComments);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);
        TextView txtComments = (TextView) oView.findViewById(R.id.txtComments);
        final Comments comment = this.getItem(position);
        txtComments.setText(comment.printComments());
        return oView;
    }

}
