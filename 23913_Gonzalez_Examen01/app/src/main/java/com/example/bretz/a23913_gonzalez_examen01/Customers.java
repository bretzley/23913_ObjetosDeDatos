package com.example.bretz.a23913_gonzalez_examen01;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by bretz on 9/8/2017.
 */

public class Customers implements Parcelable{
    private String name;
    private int operations;
    private String date;
    private int position;
    private ArrayList<String> visit = new ArrayList<String>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public Customers(){}

    public Customers(String name, int operations, int position, String date){
        this.name = name;
        this.operations = operations;
        this.position = operations;
        this.date = date;
    }

    public Customers(Parcel in){
        name = in.readString();
        operations = in.readInt();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){return name;}

    public int getOperations(){return operations;}

    public void setOperations(int operations) {
        this.operations = operations;
    }

    public ArrayList<String> getVisit(){return visit;}

    public void setVisit(ArrayList<String> visit){this.visit = visit;}

    public void addVisit(String visit){this.visit.add(visit);}

    public String printVisit() {
        String print = "";
        if (this.visit != null) {
            for (String visit : this.visit) {
                if (print.length() > 0) {
                    print += ", " + visit;
                } else {
                    print += visit;
                }
            }
        }
        return print;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(operations);
        dest.writeInt(position);
        dest.writeString(date);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public  Customers createFromParcel(Parcel in){
            return new Customers(in);
        }

        public Customers[] newArray(int size){
            return new Customers[size];
        }
    };




}
