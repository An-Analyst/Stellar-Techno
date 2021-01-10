package com.example.amol.stellartechno;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = LoginActivity.this;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText name;
    private Button appCompatButtonLogin;
    private TextView textViewLinkRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        textInputEditTextEmail = (EditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (EditText) findViewById(R.id.textInputEditTextPassword);
        name = findViewById(R.id.loginName);
        appCompatButtonLogin = (Button) findViewById(R.id.ButtonLogin);
        textViewLinkRegister = (TextView) findViewById(R.id.textViewLinkRegister);
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(activity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ButtonLogin:
                check();
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
    private void check() {
        if(textInputEditTextEmail==null){
            Toast toast = Toast.makeText(getApplicationContext(),"Enter Email", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(textInputEditTextPassword==null){
            Toast toast = Toast.makeText(getApplicationContext(),"Enter Password", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private void verifyFromSQLite() {

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
            Intent accountsIntent = new Intent(activity, Welcome.class);
            accountsIntent.putExtra("NAME", name.getText().toString().trim());
            String uemail = textInputEditTextEmail.getText().toString();
            String pass = textInputEditTextPassword.getText().toString();

            if (uemail.equals("")||pass.equals("")){
                Toast.makeText(getApplicationContext(),"Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
            startActivity(accountsIntent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.error_valid_email_password), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}