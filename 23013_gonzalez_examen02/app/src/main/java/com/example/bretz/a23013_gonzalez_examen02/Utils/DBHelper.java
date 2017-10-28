package com.example.bretz.a23013_gonzalez_examen02.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bretz.a23013_gonzalez_examen02.Objects.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bretz on 10/27/2017.
 */

public class DBHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] SNLD_TABLE_COLUMNS =
            {
                    DBUtils.SNLD_BASEID,
                    DBUtils.SNLD_ID,
                    DBUtils.SNLD_BOARD,
                    DBUtils.SNLD_AUTHOR,
                    DBUtils.SNLD_SNAKE,
                    DBUtils.SNLD_LADDER

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

    public Board addBoard(int idInt, String boardName, String author,  List<String> snakesList, List<String> laddersList) {
        String id = Integer.toString(idInt);
        String snakes = snakesList.toString();
        String ladders = laddersList.toString();
        ContentValues values = new ContentValues();
        values.put(DBUtils.SNLD_ID, id);
        values.put(DBUtils.SNLD_BOARD, boardName);
        values.put(DBUtils.SNLD_AUTHOR, author);
        values.put(DBUtils.SNLD_SNAKE, snakes);
        values.put(DBUtils.SNLD_LADDER, ladders);

        long commentId = database.insert(DBUtils.SNLD_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.SNLD_TABLE, SNLD_TABLE_COLUMNS,
                DBUtils.SNLD_BASEID + " = " + commentId, null, null, null, null);
        cursor.moveToFirst();
        Board board = parseBoard(cursor);
        cursor.close();
        return board;
    }


    public ArrayList<Board> getAllBoards() {
        ArrayList<Board> boards = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.SNLD_TABLE, SNLD_TABLE_COLUMNS, null, null, null, null, null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            boards.add(parseBoard(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return boards;
    }

    public void deleteAllBoards() {
        database.delete(DBUtils.SNLD_TABLE, DBUtils.SNLD_ID + " > 0", null);
    }

    private Board parseBoard(Cursor cursor) {
        Board board = new Board();
        board.setId(cursor.getString(cursor.getColumnIndex(DBUtils.SNLD_ID)) + "");
        board.setBoardName(cursor.getString(cursor.getColumnIndex(DBUtils.SNLD_BOARD)) + "");
        board.setAuthor(cursor.getString(cursor.getColumnIndex(DBUtils.SNLD_AUTHOR)));
        board.setSnakes(cursor.getString(cursor.getColumnIndex(DBUtils.SNLD_SNAKE)));
        board.setLadders(cursor.getString(cursor.getColumnIndex(DBUtils.SNLD_LADDER)));

        return board;
    }
}
