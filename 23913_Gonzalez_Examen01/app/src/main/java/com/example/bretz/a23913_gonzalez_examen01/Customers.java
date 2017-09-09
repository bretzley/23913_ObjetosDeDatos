package com.example.bretz.a23913_gonzalez_examen01;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

/**
 * Created by bretz on 9/8/2017.
 */

public class Customers implements Parcelable{
    private int numID;
    private String name;
    private int operations;

    public Customers(int numID, String name, int operations){
        this.numID = numID;
        this.name = name;
        this.operations = operations;

    }

    public Customers(){

    }

    public Customers(Parcel in){
        numID = in.readInt();
        name = in.readString();
        operations = in.readInt();
    }

    public String getName(){return name;}

    public void setName(String name) {
        this.name = name;
    }

    public int getOperations(){return operations;}

    public int getNumID() {
        return numID;
    }

    public void setNumID(int numID) {
        this.numID = numID;
    }

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
        @Override
        public  Customers createFromParcel(Parcel in){
            return new Customers(in);
        }

        @Override
        public Customers[] newArray(int size){
            return new Customers[size];
        }
    };




}
