package com.example.amol.stellartechno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private final AppCompatActivity activity = RegisterActivity.this;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button buttonRegister;
    private TextView LoginLink;
    private DatabaseHelper databaseHelper;
    private User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        buttonRegister = (Button) findViewById(R.id.ButtonRegister);
        LoginLink = (TextView) findViewById(R.id.TextViewLoginLink);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = confirmPassword.getText().toString();

                if (uname.equals("")||uemail.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    postDataToSQLite();
                }
            }
        });
        LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void postDataToSQLite() {
        if (!databaseHelper.checkUser(email.getText().toString().trim())) {
            user.setName(name.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());
            databaseHelper.addUser(user);
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.success_message),
                    Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.error_email_exists),
                    Toast.LENGTH_SHORT).show();
        }
    }
}