package com.shrey.task1sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class findUserActivity extends AppCompatActivity {
    EditText username;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        DBHelper db = new DBHelper(this);
        username = findViewById(R.id.getuser);
        button = findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                if (db.checkAlreadyExist(name)==true){

                    Intent intent = new Intent(findUserActivity.this, PassResetActivity.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"User not exist",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}