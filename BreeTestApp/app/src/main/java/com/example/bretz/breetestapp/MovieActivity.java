package com.example.bretz.breetestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    MovieAdapter oMovieAdapter;

    ListView oListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        oListView = (ListView) findViewById(R.id.lstMovies);
        oMovieAdapter = new MovieAdapter(this);
        oListView.setAdapter(oMovieAdapter);

        ArrayList<Movies> movieArray = new ArrayList<Movies>();
        movieArray.add(new Movies ("test1 ",12,"Un tipo 1", "Genero x", 1294));
        movieArray.add(new Movies ("test2",12,"Un tipo 12", "Genero x", 1294));
        movieArray.add(new Movies ("test3",12,"Un tipo 13", "Genero x", 1294));
        movieArray.add(new Movies ("test4",12,"Un tipo 14", "Genero x", 1294));
        movieArray.add(new Movies ("test5",12,"Un tipo 15", "Genero x", 1294));
        movieArray.add(new Movies ("test6",12,"Un tipo 16", "Genero x", 1294));
        movieArray.add(new Movies ("test7",12,"Un tipo 17", "Genero x", 1294));
        movieArray.add(new Movies ("test8",12,"Un tipo 18", "Genero x", 1294));
        movieArray.add(new Movies ("test9",12,"Un tipo 19", "Genero x", 1294));
        movieArray.add(new Movies ("test10",12,"Un tipo 10", "Genero x", 1294));
        movieArray.add(new Movies ("test11",12,"Un tipo 111", "Genero x", 1294));
        movieArray.add(new Movies ("test12",12,"Un tipo 112", "Genero x", 1294));




    }

    private void fillMovieDatabase(ArrayList<Movies> lMovies){
        oMovieAdapter.clear();
        for(Movies oMovie : lMovies){
            oMovieAdapter.add(oMovie);
        }

        oMovieAdapter.notifyDataSetChanged();

    }



}
