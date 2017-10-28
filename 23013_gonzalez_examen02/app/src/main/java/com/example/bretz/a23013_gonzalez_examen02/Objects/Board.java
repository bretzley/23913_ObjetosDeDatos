package com.example.bretz.a23013_gonzalez_examen02.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bretz on 10/27/2017.
 */

public class Board implements Parcelable{
    private String boardId;
    private String id;
    private String boardName;
    private String author;
    private String snakes;
    private String ladders;


    public Board(Parcel in){
        this.boardId = in.readString();
        this.id  = in.readString();
        this.boardName = in.readString();
        this.author = in.readString();
        this.snakes = in.readString();
        this.ladders = in.readString();
    }

    public Board(){}

    public Board(String boardId, String id, String boardName, String author, String snakes, String ladders){
        this.boardId = boardId;
        this.id = id;
        this.boardName = boardName;
        this.author = author;
        this.snakes = snakes;
        this.ladders = ladders;

    }


    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLadders() {
        return ladders;
    }

    public void setLadders(String ladders) {
        this.ladders = ladders;
    }

    public String getSnakes() {
        return snakes;
    }

    public void setSnakes(String snakes) {
        this.snakes = snakes;
    }

    public String printBoard(){
        String board = "";
        board += "Board Id: " + this.boardId + "\n";
        board += "Id: " + this.id + "\n";
        board += "Board Name: " + this.boardName + "\n";
        board += "Author: " + this.author + "\n";
        board += "Snakes: " + this.snakes + "\n";
        board += "Ladders: " + this.ladders + "\n";
        return board;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("boardId", getBoardId());
            jsonObject.put("boardName", getBoardName());
            jsonObject.put("author", getAuthor());
            jsonObject.put("snakes", getSnakes());
            jsonObject.put("ladders", getLadders());

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
        dest.writeString(boardId);
        dest.writeString(id);
        dest.writeString(boardName);
        dest.writeString(author);
        dest.writeString(snakes);
        dest.writeString(ladders);
    }

    public static final Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };

}
