package com.example.bretz.oct6android.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bretz on 10/12/2017.
 */

public class Comments implements Parcelable{

    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;

    public Comments(Parcel in) {
        this.postId = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.body = in.readString();
    }

    public Comments(String postId, String id, String name, String email, String body){
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Comments(){}

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String printComments(){
        String comment = "";
        comment += "Post Id: " + this.postId + "\n";
        comment += "Id: " + this.id + "\n";
        comment += "Name: " + this.name + "\n";
        comment += "Email: " + this.email + "\n";
        comment += "Body: " + this.body + "\n";
        return comment;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("postId", getPostId());
            jsonObject.put("name", getName());
            jsonObject.put("email", getEmail());
            jsonObject.put("body", getBody());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postId);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(body);
    }


    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

}
