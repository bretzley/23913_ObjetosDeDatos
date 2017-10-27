package com.example.bretz.oct6android.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bretz on 10/12/2017.
 */

public class Posts implements Parcelable{
    private String userId;
    private String id;
    private String title;
    private String body;

    public Posts(Parcel in) {
        this.userId = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.body = in.readString();
    }


    public Posts(){   }

    public Posts(String userId, String id, String title, String body){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Posts (String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            this.userId = jsonObject.getInt("userId") + "";
            this.id = jsonObject.getInt("id") + "";
            this.title = jsonObject.getString("title");
            this.body = jsonObject.getString("body");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public String printPost() {
        String post = "";
        post += "User Id: " + this.userId + "\n";
        post += "Id: " + this.id + "\n";
        post += "Title: " + this.title + "\n";
        post += "Body: " + this.body + "\n";
        return post;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(body);
    }

    public JSONObject toJSON(){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("userId", getUserId());
            jsonObject.put("title", getTitle());
            jsonObject.put("body", getBody());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Posts createFromParcel(Parcel in){
            return new Posts(in);
        }
        @Override
        public Posts[] newArray(int size){
            return new Posts[size];
        }
    };
}
