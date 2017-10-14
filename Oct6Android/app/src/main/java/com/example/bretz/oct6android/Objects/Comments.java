package com.example.bretz.oct6android.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 10/12/2017.
 */

public class Comments implements Parcelable{

    private String commentId;
    private String id;
    private String name;
    private String email;
    private String body;

    public Comments(Parcel in) {
        this.commentId = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.body = in.readString();
    }

    public Comments(String commentId, String id, String name, String email, String body){
        this.commentId = commentId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Comments(){}

    public String getPostId() {
        return commentId;
    }

    public void setPostId(String postId) {
        this.commentId = commentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
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
        comment += "Comment Id: " + this.commentId + "\n";
        comment += "Id: " + this.id + "\n";
        comment += "Name: " + this.name + "\n";
        comment += "Email: " + this.email + "\n";
        comment += "Body: " + this.body + "\n";
        return comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(commentId);
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
