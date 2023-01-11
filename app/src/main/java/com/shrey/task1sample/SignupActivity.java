package com.shrey.task1sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText username,passfile;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        DBHelper db = new DBHelper(this);
        username = findViewById(R.id.username);
        passfile = findViewById(R.id.passfile);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = passfile.getText().toString();
                boolean check = validateinfo(name, pass);
                if (check == true){
                    if(db.insert(name,pass)){
                        Toast.makeText(getApplicationContext(),"Signup success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Can't access to database", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateinfo(String name, String pass) {
        boolean flag = true;
        if (name.length() == 0){
            username.requestFocus();
            username.setError("Field can not be empty");
            flag = false;
        }

        else if(!name.matches("[a-zA-Z]+")){
            username.requestFocus();
            username.setError("Enter Only Alphabetical Character");
            flag = false;
        }
        if (pass.length() == 0){
            passfile.requestFocus();
            passfile.setError("Field can not be empty");
            flag = false;
        }
        else if(pass.length()<=5){
            passfile.requestFocus();
            passfile.setError("Minimum 6 character required");
            flag = false;
        }
        return flag;
    }
}