package com.example.bretz.bretzmovieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by bretz on 8/31/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movies> {
    public MovieAdapter(Context context){
        super(context, R.layout.movie_row, R.id.txtMovieName);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View oView=super.getView(position, convertView, parent);
        TextView txtMovieName = (TextView) oView.findViewById(R.id.txtMovieName);
        TextView txtMovieDuration = (TextView) oView.findViewById(R.id.txtMovieDuration);
        TextView txtMovieDirector =(TextView) oView.findViewById(R.id.txtMovieDirector);
        TextView txtMovieGenre = (TextView) oView.findViewById(R.id.txtMovieGenre);
        TextView txtMovieYear = (TextView) oView.findViewById(R.id.txtMovieYear);

        Movies oMovie = this.getItem(position);
        txtMovieName.setText(oMovie.getName());
        txtMovieDuration.setText(oMovie.getDuration()+"");
        txtMovieDirector.setText(oMovie.getDirector());
        txtMovieGenre.setText(oMovie.getGenre());
        txtMovieYear.setText(oMovie.getYear()+"");



        return oView;
    }
}
