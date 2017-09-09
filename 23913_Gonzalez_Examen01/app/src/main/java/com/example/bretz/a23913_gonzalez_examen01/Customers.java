package com.example.bretz.a23913_gonzalez_examen01;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

/**
 * Created by bretz on 9/8/2017.
 */

public class Customers implements Parcelable{
    private String name;
    private int operations;

    public Customers(String name, int operations){
        this.name = name;
        this.operations = operations;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(operations);

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
