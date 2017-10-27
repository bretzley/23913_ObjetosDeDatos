package com.example.bretz.oct6android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.bretz.oct6android.Adapters.ExpandingAdapter;
import com.example.bretz.oct6android.Objects.Comments;
import com.example.bretz.oct6android.Objects.ExpandData;
import com.example.bretz.oct6android.Objects.Posts;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ExpandableListActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandingAdapter expandingAdapter;
    List<String> expandableListTitle;
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;
    TreeMap<String, List<String>> expandableListDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        posts = this.getIntent().getParcelableArrayListExtra("Posts");
        comments = this.getIntent().getParcelableArrayListExtra("Comments");
        expandableListView = (ExpandableListView) findViewById(R.id.ExpandableListView);
        expandableListDetail = ExpandData.getData(posts, comments);


        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandingAdapter = new ExpandingAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandingAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    public void onBackPressed() {
        finish();
    }

}
