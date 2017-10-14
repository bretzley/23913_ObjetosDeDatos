package com.example.bretz.oct6android.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bretz.oct6android.Objects.Comments;
import com.example.bretz.oct6android.Objects.Posts;

import java.util.ArrayList;

/**
 * Created by bretz on 10/12/2017.
 */

public class DBHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] POST_TABLE_COLUMNS =
            {
                    DBUtils.POST_BASEID,
                    DBUtils.POST_ID,
                    DBUtils.POST_USER,
                    DBUtils.POST_TITLE,
                    DBUtils.POST_BODY
            };
    private String[] COMMENT_TABLE_COLUMNS =
            {
                    DBUtils.COMMENT_BASEID,
                    DBUtils.COMMENT_ID,
                    DBUtils.COMMENT_POST,
                    DBUtils.COMMENT_NAME,
                    DBUtils.COMMENT_EMAIL,
                    DBUtils.COMMENT_BODY
            };

    public DBHelper(Context context) {
        dbHelper = new DBUtils(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        database.close();
    }

    public Comments addComment(int id, int post, String name, String email, String body) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.COMMENT_ID, id);
        values.put(DBUtils.COMMENT_POST, post);
        values.put(DBUtils.COMMENT_NAME, name);
        values.put(DBUtils.COMMENT_EMAIL, email);
        values.put(DBUtils.COMMENT_BODY, body);
        long commentId = database.insert(DBUtils.COMMENT_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.COMMENT_TABLE, COMMENT_TABLE_COLUMNS,
                DBUtils.COMMENT_BASEID + " = " + commentId, null, null, null, null);
        cursor.moveToFirst();
        Comments comment = parseComment(cursor);
        cursor.close();
        return comment;
    }

    public Posts addPost(int id, int user, String title, String body) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.POST_ID, id);
        values.put(DBUtils.POST_USER, user);
        values.put(DBUtils.POST_TITLE, title);
        values.put(DBUtils.POST_BODY, body);
        long postId = database.insert(DBUtils.POST_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.POST_TABLE, POST_TABLE_COLUMNS,
                DBUtils.POST_BASEID + " = " + postId, null, null, null, null);
        cursor.moveToFirst();
        Posts post = parsePost(cursor);
        cursor.close();
        return post;
    }

    public void deletePost(int id) {
        database.delete(DBUtils.POST_TABLE, DBUtils.POST_ID+" = " + id, null);
    }

    public void deleteAllPosts() {
        database.delete(DBUtils.POST_TABLE, DBUtils.POST_ID + " > 0", null);
    }

    public void deleteComment(int id) {
        database.delete(DBUtils.COMMENT_TABLE, DBUtils.COMMENT_ID+" = " + id, null);
    }

    public void deleteAllComments() {
        database.delete(DBUtils.COMMENT_TABLE, DBUtils.COMMENT_ID + " > 0", null);
    }

    public ArrayList<Posts> getAllPosts() {
        ArrayList<Posts> posts = new ArrayList<Posts>();
        Cursor cursor = database.query(DBUtils.POST_TABLE, POST_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            posts.add(parsePost(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return posts;
    }

    public ArrayList<Comments> getAllComments() {
        ArrayList<Comments> comments = new ArrayList<Comments>();
        Cursor cursor = database.query(DBUtils.COMMENT_TABLE, COMMENT_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            comments.add(parseComment(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    private Comments parseComment(Cursor cursor) {
        Comments comment = new Comments();
        //if( cursor != null && cursor.moveToFirst() ) {
            comment.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.COMMENT_ID)) + "");
            comment.setPostId(cursor.getInt(cursor.getColumnIndex(DBUtils.COMMENT_POST)) + "");
            comment.setName(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENT_NAME)));
            comment.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENT_EMAIL)));
            comment.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENT_BODY)));
        //}
        return comment;
    }

    private Posts parsePost(Cursor cursor) {
        Posts post = new Posts();
        //if( cursor != null && cursor.moveToFirst() ) {
            post.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.POST_ID)) + "");
            post.setUserId(cursor.getInt(cursor.getColumnIndex(DBUtils.POST_USER)) + "");
            post.setTitle(cursor.getString(cursor.getColumnIndex(DBUtils.POST_TITLE)));
            post.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.POST_BODY)));
        //}
        return post;
    }

}
