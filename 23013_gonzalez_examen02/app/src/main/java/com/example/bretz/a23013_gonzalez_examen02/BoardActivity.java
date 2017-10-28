package com.example.bretz.a23013_gonzalez_examen02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bretz.a23013_gonzalez_examen02.Adapters.BoardAdapter;
import com.example.bretz.a23013_gonzalez_examen02.Objects.Board;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    ArrayList<Board> board;
    BoardAdapter boardAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        listView = (ListView) findViewById(R.id.boardListView);
        boardAdapter = new BoardAdapter(this);
        listView.setAdapter(boardAdapter);
        board = this.getIntent().getParcelableArrayListExtra("Parcel");
        fillBoard(board);
    }

    public void fillBoard(ArrayList<Board> boards){
        for(Board board : boards)
            boardAdapter.add(board);
        boardAdapter.notifyDataSetChanged();
    }

    public void onBackPressed(){
        finish();
    }
}
