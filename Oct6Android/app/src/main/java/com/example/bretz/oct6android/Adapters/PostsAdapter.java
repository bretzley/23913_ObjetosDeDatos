package com.example.bretz.oct6android.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bretz.oct6android.Objects.Posts;
import com.example.bretz.oct6android.R;

/**
 * Created by bretz on 10/13/2017.
 */

public class PostsAdapter extends ArrayAdapter<Posts> {

    public PostsAdapter(@NonNull Context context) {
        super(context,  R.layout.post_rows, R.id.txtPosts);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);
        TextView txtPosts = (TextView) oView.findViewById(R.id.txtPosts);
        final Posts post = this.getItem(position);
        txtPosts.setText(post.printPost());
        return oView;
    }
}
