package com.shrey.task1sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PassResetActivity extends AppCompatActivity {
    EditText password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_reset);
        Intent i =getIntent();
        DBHelper db = new DBHelper(this);
        password = findViewById(R.id.getpass);
        button = findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = password.getText().toString();
                String name = i.getExtras().getString("username");
                Log.i("name",name);
                boolean check = validateinfo(name, pass);
                if (check == true) {
                    if (db.updatePassword(name, pass)) {
                        Toast.makeText(getApplicationContext(), "Reset Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PassResetActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Reset Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean validateinfo(String name, String pass) {
        boolean flag = true;

        if (pass.length() == 0){
            password.requestFocus();
            password.setError("Field can not be empty");
            flag = false;
        }
        else if(pass.length()<=5){
            password.requestFocus();
            password.setError("Minimum 6 character required");
            flag = false;
        }
        return flag;
    }
}