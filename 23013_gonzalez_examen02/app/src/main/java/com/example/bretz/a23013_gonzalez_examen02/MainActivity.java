package com.example.bretz.a23013_gonzalez_examen02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.a23013_gonzalez_examen02.Objects.Board;
import com.example.bretz.a23013_gonzalez_examen02.Objects.SnakeLadders;
import com.example.bretz.a23013_gonzalez_examen02.Utils.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSync, btnPost, btnGet;
    TextView txtViewMain;
    DBHelper db;
    ArrayList<Board> boards;
    EditText edtName, edtAuthor, edtSnake, edtLadder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        btnGet =(Button)findViewById(R.id.btnGet);
        btnPost = (Button)findViewById(R.id.btnPost);
        btnSync = (Button)findViewById(R.id.btnSync);
        txtViewMain = (TextView) findViewById(R.id.txtViewMain);
        boards = new ArrayList<>();
        //hashArray = new ArrayList<>();
        edtName = (EditText) findViewById(R.id.edtName);
        edtAuthor = (EditText) findViewById(R.id.edtAuthor);
        edtSnake = (EditText) findViewById(R.id.edtSnake);
        edtLadder = (EditText) findViewById(R.id.edtLadder);
        final String boardURL = "http://107.170.247.123:2403/snakes-ladders";
        final RequestQueue queue = Volley.newRequestQueue(this);


        final JsonArrayRequest boardArrayRequest = new JsonArrayRequest(boardURL,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        boards = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                boards.add(createBoard(response.getString(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtViewMain.setText(error.toString());
            }
        });



        try {
            final JSONObject jsonbody = new JSONObject("{\"title\":\"test\"}");


            final JsonObjectRequest jsonBoardRequest = new JsonObjectRequest
                    (Request.Method.POST, boardURL, jsonbody, new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            txtViewMain.setText(response.toString());
                            try {
                                jsonbody.put("id",null);
                                jsonbody.put("name", edtName.getText().toString());
                                jsonbody.put("author", edtAuthor.getText().toString());
                                jsonbody.put("snakes", edtSnake.getText().toString());
                                jsonbody.put("ladders", edtLadder.getText().toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            txtViewMain.setText(error.toString());
                        }
                    });
            queue.add(jsonBoardRequest);
        } catch (JSONException e) {
            txtViewMain.setText(e.toString());
            e.printStackTrace();
        }

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Boards  are being synchronized...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                db.deleteAllBoards();
                db.close();
                queue.add(boardArrayRequest);
                Snackbar.make(view, "Boards were synchronized successfully!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BoardActivity.class);
                db.open();
                intent.putExtra("Parcel", db.getAllBoards());
                db.close();
                startActivity(intent);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Board board = new Board();
                board.setBoardName(edtName.getText().toString());
                board.setAuthor(edtAuthor.getText().toString());
                board.setSnakes(edtSnake.getText().toString());
                board.setLadders(edtLadder.getText().toString());
                JSONObject json = board.toJSON();
                JsonObjectRequest objectRequest = postBoardJSON(json, boardURL);
                queue.add(objectRequest);
            }
        });

    }





    public Board createBoard(String response) {
        Board board = new Board();
        try {
            JSONObject jsonObject = new JSONObject(response);
            List<String> snakeStrings = new ArrayList<>();
            List<String> ladderStrings = new ArrayList<>();
            SnakeLadders snkld = new SnakeLadders(snakeStrings,ladderStrings);
            List<String> ladder = snkld.getLadders();
            List<String> snakes = snkld.getSnakes();
            snakeStrings = snakes;
            ladderStrings = ladder;
            int Id = jsonObject.getInt("id");
            //String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String author = jsonObject.getString("author");
            snakeStrings = (List<String>) jsonObject.getJSONArray("snakes");
            ladderStrings = (List<String>) jsonObject.getJSONArray("ladders");
            db.open();
            board = db.addBoard(Id, name, author, snakeStrings, ladderStrings);
            db.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return board;
    }


    public JsonObjectRequest postBoardJSON(final JSONObject jsonBody, String url) {
        final JsonObjectRequest jsonPostRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Successfully posted your board!");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error uploading board: " + error.toString());
                    }
                });
        return jsonPostRequest;
    }


}
