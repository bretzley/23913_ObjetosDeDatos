package com.example.bretz.a23013_gonzalez_examen02.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bretz.a23013_gonzalez_examen02.Objects.Board;
import com.example.bretz.a23013_gonzalez_examen02.R;

/**
 * Created by bretz on 10/27/2017.
 */

public class BoardAdapter extends ArrayAdapter<Board>{

    public BoardAdapter(Context context) {
        super(context, R.layout.board_rows, R.id.txtBoard);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);
        TextView txtBoard = (TextView) oView.findViewById(R.id.txtBoard);
        final Board board = this.getItem(position);
        txtBoard.setText(board.printBoard());
        return oView;
    }

}
