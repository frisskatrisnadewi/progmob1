package com.friska.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DressListDetailActivity extends AppCompatActivity {

    private Button btn_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_list_detail);

        init();
        buttonBook();
    }

    private void init(){
        btn_book=findViewById(R.id.btn_detail_book_dress);
    }

    private void buttonBook(){
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(getApplicationContext(),BookingActivity.class);
                startActivity(intent);

            }
        });
    }
}
