package com.example.bretz.bretzmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent intent = getIntent();
        String message = intent.getStringExtra("Mensaje");
        TextView txtShow = (TextView)findViewById(R.id.txtViewTwo);
        txtShow.setText(message);
        Button btn_close = (Button) findViewById(R.id.btnTwo);

        btn_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }
}
