package com.example.bretz.oct6android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.bretz.oct6android.Adapters.CommentsAdapter;
import com.example.bretz.oct6android.Objects.Comments;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    ArrayList<Comments> comments;
    CommentsAdapter cmntAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        listView = (ListView) findViewById(R.id.commentListView);
        cmntAdapter = new CommentsAdapter(this);
        listView.setAdapter(cmntAdapter);
        comments = this.getIntent().getParcelableArrayListExtra("Parcel");
        fillCOmments(comments);
    }

    public void fillCOmments(ArrayList<Comments> comments){
        for(Comments comment : comments)
            cmntAdapter.add(comment);
        cmntAdapter.notifyDataSetChanged();
    }

    public void onBackPressed(){
        finish();
    }

}
