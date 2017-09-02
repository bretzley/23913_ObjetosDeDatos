package com.example.bretz.bretzmovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final String MICONSTANTE = "mensaje";

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //*Button btn_save = (Button) findViewById(R.id.btnSave);
        final EditText txt_name = (EditText) findViewById(R.id.txtName);
        Button btn_movie = (Button) findViewById(R.id.btnListMovie);
        final ListView lst_movie = (ListView)findViewById(R.id.lstMovies);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        /*btn_movie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                EditText editText = (EditText) findViewById(R.id.txtName);
                String movieName = editText.getText().toString();
                intent.putExtra("Name",movieName);

                EditText editTextDur = (EditText) findViewById(R.id.txtDuration);
                String movieDuration = editTextDur.getText().toString();
                intent.putExtra("Duration",movieDuration);


                EditText editTextDir = (EditText) findViewById(R.id.txtDirector);
                String movieDirector = editTextDir.getText().toString();
                intent.putExtra("Director",movieDirector);


                EditText editTextGen = (EditText) findViewById(R.id.txtGenre);
                String movieGenre = editTextGen.getText().toString();

                intent.putExtra("Genre",movieGenre);


                EditText editTextYr = (EditText) findViewById(R.id.txtYear);
                String movieYear = editTextYr.getText().toString();
                intent.putExtra("Year",movieYear);

                startActivity(intent);

                *//*Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
                Snackbar.make(view,txt_name.getText().toString(),Snackbar.LENGTH_LONG).setAction("Action",null).show();*//*
            }
        });*/


       btn_movie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MovieActivity.class);
                EditText editText = (EditText) findViewById(R.id.txtName);
                String movieName = editText.getText().toString();
                intent.putExtra("Name",movieName);
                /*ListView lstMoviesList = (ListView)findViewById(R.id.lstMovies);
                String features = lstMoviesList.toString();*/
                startActivity(intent);


            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
