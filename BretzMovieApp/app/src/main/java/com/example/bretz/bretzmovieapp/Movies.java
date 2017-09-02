package com.example.bretz.bretzmovieapp;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by bretz on 8/31/2017.
 */


public class Movies implements Parcelable{
    private String name;
    private int duration;
    private String director;
    private String genre;
    private int year;

    public Movies(){

    }

    public Movies(String name, int duration, String director, String genre, int year) {
        this.name = name;
        this.duration = duration;
        this.director = director;
        this.genre = genre;
        this.year = year;
    }

    public Movies(Parcel in ){
        name = in.readString();
        duration = in.readInt();
        director = in.readString();
        genre = in.readString();
        year = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(duration);
        dest.writeString(director);
        dest.writeString(genre);
        dest.writeInt(year);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public  Movies createFromParcel(Parcel in){
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size){
            return new Movies[size];
        }
    };
}
