package com.shrey.task1sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,passfile;
    Button loginbtn,signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBHelper db = new DBHelper(this);
        username = findViewById(R.id.username);
        passfile = findViewById(R.id.passfile);
        loginbtn = findViewById(R.id.loginbutton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = passfile.getText().toString();
                if (db.checkAlreadyExist(name)==true){
                    if(db.checkpassword(name,pass)){
                        Toast.makeText(getApplicationContext(),"Login success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Wrong password", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"User doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signupbtn = findViewById(R.id.signupbutton);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        }
        public void forgor (View v){
            Intent intent = new Intent(LoginActivity.this, findUserActivity.class);
            startActivity(intent);
        }

}