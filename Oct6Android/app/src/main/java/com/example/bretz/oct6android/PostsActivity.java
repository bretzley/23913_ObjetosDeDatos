package com.example.bretz.oct6android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bretz.oct6android.Adapters.PostsAdapter;
import com.example.bretz.oct6android.Objects.Posts;

import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {
    PostsAdapter postAdapter;
    ListView listView;
    ArrayList<Posts> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        listView = (ListView) findViewById(R.id.postListView);
        postAdapter = new PostsAdapter(this);
        listView.setAdapter(postAdapter);

        posts = this.getIntent().getParcelableArrayListExtra("Parcel");
        fillPosts(posts);
    }

    public void fillPosts(ArrayList<Posts> posts) {
        for (Posts post : posts)
            postAdapter.add(post);
        postAdapter.notifyDataSetChanged();

    }

        public void onBackPressed() {
            finish();
        }


}
