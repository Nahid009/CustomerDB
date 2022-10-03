package com.nahidd.customerdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


  private TextView textView;
  private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.customerTextView);
        button = findViewById(R.id.buttonPanel);


        button.setOnClickListener(this);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, CustomerFrom.class));
//            }
//        });


    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, CustomerFrom.class));
    }
}