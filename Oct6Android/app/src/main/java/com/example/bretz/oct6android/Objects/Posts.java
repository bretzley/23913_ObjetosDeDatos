package com.example.bretz.oct6android.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 10/12/2017.
 */

public class Posts implements Parcelable{
    private String postId;
    private String id;
    private String title;
    private String body;

    public Posts(Parcel in) {
        this.postId = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.body = in.readString();
    }


    public Posts(){   }

    public Posts(String postId, String id, String title, String body){
        this.postId = postId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getUserId() {
        return postId;
    }

    public void setUserId(String userId) {
        this.postId = postId;
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
        post += "Post Id: " + this.postId + "\n";
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
        dest.writeString(postId);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(body);
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
