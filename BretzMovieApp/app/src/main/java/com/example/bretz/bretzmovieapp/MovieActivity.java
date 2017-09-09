package com.example.bretz.bretzmovieapp;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity{

    MovieAdapter oMovieAdapter;
    ListView oListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        oListView = (ListView) findViewById(R.id.lstMovies);
        oMovieAdapter = new MovieAdapter(this);
        oListView.setAdapter(oMovieAdapter);

        Movies oMovie = this.getIntent().getParcelableExtra("MiLlave");
        ArrayList<Movies> movieArray = new ArrayList<>();
        movieArray.add(oMovie);


        //<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");


        /*Intent intent = getIntent();
        String movieName = intent.getStringExtra("Name");
        String movieDuration = intent.getStringExtra("Duration");
        int movieDurationInt = Integer.parseInt(movieDuration);
        String movieDirector = intent.getStringExtra("Director");
        String movieGenre = intent.getStringExtra("Genre");
        String movieYear = intent.getStringExtra("Year");
        int movieYearInt = Integer.parseInt(movieYear);*/



        //ArrayList<Movies> movieSeriesArray = (ArrayList<Movies>) intent.getSerializableExtra("Movie Series");
        //movieSeriesArray.add(new Movies(movieName,movieDurationInt,movieDirector,movieGenre,movieYearInt));

        //Intent intentArray = new Intent(MovieActivity.this, MainActivity.class);
        //intent.putExtra("Movie Series", movieSeriesArray);
        //startActivity(intent);


        //Movies M = new Movies(movieName, movieDurationInt, movieDirector, movieGenre, movieYearInt);
        //movieArray.add(M);

        fillMovieData(movieArray);
        //Button btn_close = (Button) findViewById(R.id.btnTwo);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }




    @Override
    public void onBackPressed() {
        retorno();
    }

    public void retorno(){
        Intent oIntent = new Intent();
        oIntent.putExtra("resultado",1);
        setResult(MainActivity.MI_CODIGO_RETORNO,oIntent);
        finish();
    }

    private void fillMovieData(ArrayList<Movies> lMovies)
    {
        oMovieAdapter.clear();

        for (Movies oMovie : lMovies){
            oMovieAdapter.add(oMovie);
        }
        oMovieAdapter.notifyDataSetChanged();
    }

}
