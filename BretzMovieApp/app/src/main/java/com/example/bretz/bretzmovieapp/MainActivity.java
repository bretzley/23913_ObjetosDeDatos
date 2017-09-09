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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final String MICONSTANTE = "mensaje";
    public static final int MI_CODIGO_RETORNO = 1;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //*Button btn_save = (Button) findViewById(R.id.btnSave);
        Button btn_movie = (Button) findViewById(R.id.btnListMovie);
        Button btn_adding = (Button) findViewById(R.id.btnAddMovie);
        final EditText txt_name = (EditText) findViewById(R.id.txtName);
        final EditText txt_duration = (EditText) findViewById(R.id.txtDuration);
        final EditText txt_director = (EditText) findViewById(R.id.txtDirector);
        final EditText txt_genre = (EditText) findViewById(R.id.txtGenre);
        final EditText txt_year = (EditText) findViewById(R.id.txtYear);
        final ListView lst_movie = (ListView)findViewById(R.id.lstMovies);

        final ArrayList<Movies> array_movie= new ArrayList<>();

        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }



        });


        btn_adding.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intentAdd = new Intent(getApplicationContext(), MovieActivity.class);

                EditText editText = (EditText) findViewById(R.id.txtName);
                String movieName = editText.getText().toString();
                intentAdd.putExtra("Name",movieName);

                EditText editTextDur = (EditText) findViewById(R.id.txtDuration);
                String movieDuration = editTextDur.getText().toString();
                intentAdd.putExtra("Duration",movieDuration);
                int movieDurationInt = Integer.parseInt(movieDuration);


                EditText editTextDir = (EditText) findViewById(R.id.txtDirector);
                String movieDirector = editTextDir.getText().toString();
                intentAdd.putExtra("Director",movieDirector);

                EditText editTextGen = (EditText) findViewById(R.id.txtGenre);
                String movieGenre = editTextGen.getText().toString();
                intentAdd.putExtra("Genre",movieGenre);


                EditText editTextYr = (EditText) findViewById(R.id.txtYear);
                String movieYear = editTextYr.getText().toString();
                intentAdd.putExtra("Year",movieYear);
                int movieYearInt = Integer.parseInt(movieYear);

                Movies oMovie = new Movies();
                oMovie.setName(movieName);
                oMovie.setDuration(movieDurationInt);
                oMovie.setDirector(movieDirector);
                oMovie.setGenre(movieGenre);
                oMovie.setYear(movieYearInt);
                intentAdd.putExtra("MiLlave",oMovie);






                startActivityForResult(intentAdd, MI_CODIGO_RETORNO);
                Toast.makeText(getApplicationContext(),"Movie Added Succesfully!",Toast.LENGTH_LONG).show();
            }
        });

        btn_movie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                Intent intent = new Intent(getApplicationContext(), MovieActivity.class);

                /*EditText editText = (EditText) findViewById(R.id.txtName);
                String movieName = editText.getText().toString();
                intent.putExtra("Name",movieName);

                EditText editTextDur = (EditText) findViewById(R.id.txtDuration);
                String movieDuration = editTextDur.getText().toString();
                intent.putExtra("Duration",movieDuration);
                int movieDurationInt = Integer.parseInt(movieDuration);


                EditText editTextDir = (EditText) findViewById(R.id.txtDirector);
                String movieDirector = editTextDir.getText().toString();
                intent.putExtra("Director",movieDirector);

                EditText editTextGen = (EditText) findViewById(R.id.txtGenre);
                String movieGenre = editTextGen.getText().toString();
                intent.putExtra("Genre",movieGenre);


                EditText editTextYr = (EditText) findViewById(R.id.txtYear);
                String movieYear = editTextYr.getText().toString();
                intent.putExtra("Year",movieYear);
                int movieYearInt = Integer.parseInt(movieYear);


                Movies oMovie = new Movies();
                oMovie.setName(movieName);
                oMovie.setDuration(movieDurationInt);
                oMovie.setDirector(movieDirector);
                oMovie.setGenre(movieGenre);
                oMovie.setYear(movieYearInt);
                intent.putExtra("MiLlave",oMovie);*/

                startActivity(intent);


                /*Snackbar.make(view,txt_name.getText().toString(),Snackbar.LENGTH_LONG).setAction("Action",null).show();*/
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == MI_CODIGO_RETORNO){
            //Mi arratlist bonito reempazar a mi arraylist local
        }
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
