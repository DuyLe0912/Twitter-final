package com.shrey.task1sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TweetActivity extends AppCompatActivity{
    Button tweetbtn;
    EditText textbox;
    Tweet twt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        textbox =findViewById(R.id.textbox);
        tweetbtn = findViewById(R.id.twtbutton);
        tweetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TweetActivity.this,MainActivity.class);
                intent.putExtra("tweetcontent",textbox.getText().toString());
                startActivity(intent);

            }
        });
    }

}